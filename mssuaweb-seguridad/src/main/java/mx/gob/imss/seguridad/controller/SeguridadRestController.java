package mx.gob.imss.seguridad.controller;

import mx.gob.imss.seguridad.dto.*;
import mx.gob.imss.seguridad.entity.SwcRole;
import mx.gob.imss.seguridad.entity.SwtUsuario;
import mx.gob.imss.seguridad.repository.SwtUsuarioRepository;
import mx.gob.imss.seguridad.service.JwtUtilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/mssuaweb-seguridad/v1")
public class SeguridadRestController {

    private final static Logger logger = LoggerFactory.getLogger(SeguridadRestController.class);

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtilService jwtUtilService;

    @Autowired
    private SwtUsuarioRepository usuarioRepository;

    /**
     * METODO DE LOGIN: Autenticación basada en existencia de usuario.
     *   NO se valida el password contra la DB.
     */
    @PostMapping("/login")
    public ResponseEntity<?> auth(@RequestBody AuthRequestDto authRequestDto) {
        logger.info("Iniciando Login para usuario: {}", authRequestDto.getUser());

        try {
            // 1. Validación de nulos en la petición
            if (authRequestDto.getUser() == null) {
                logger.warn("Petición de login sin nombre de usuario.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario requerido.");
            }

            // 2. Buscar el usuario en la tabla SWT_USUARIO (DES_USUARIO)
            // Usamos Optional de Java 8 para un manejo de errores más limpio
            Optional<SwtUsuario> usuarioOpt = usuarioRepository.findByUsername(authRequestDto.getUser());

            if (!usuarioOpt.isPresent()) {
                logger.error("El usuario {} no existe en la tabla SWT_USUARIO.", authRequestDto.getUser());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado.");
            }

            SwtUsuario usuarioEntity = usuarioOpt.get();

            // 3. Extraer Roles desde la relación ManyToMany definida en la Entidad
            // Usamos Streams para transformar la lista de objetos Role a una lista de Strings (DES_ROLE)
            List<String> roles = usuarioEntity.getRoles().stream()
                    .map(SwcRole::getNombreRole)
                    .collect(Collectors.toList());

            if (roles.isEmpty()) {
                logger.warn("Usuario {} encontrado pero no tiene roles asociados en SWT_USUARIO_ROLE.", authRequestDto.getUser());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario sin roles asignados.");
            }

            // 4. Preparar DTO de información del usuario para el Token
            UsuarioDto usuarioDto = new UsuarioDto();
            usuarioDto.setRfc(usuarioEntity.getRfc());
            usuarioDto.setNombre(usuarioEntity.getNombre());
            usuarioDto.setPrimerApellido(usuarioEntity.getApellidoPaterno());
            usuarioDto.setSegundoApellido(usuarioEntity.getApellidoMaterno());

            // 5. Cargar UserDetails (Sin validación de password, igual al método original)
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(usuarioEntity.getUsername());

            // 6. Generación de JWT y Refresh Token incluyendo claims personalizados
            logger.info("Generando tokens para el usuario con roles: {}", roles);
            String jwt = this.jwtUtilService.generateToken(userDetails, roles, usuarioDto);
            String refreshToken = this.jwtUtilService.generateRefreshToken(userDetails, roles, usuarioDto);

            // 7. Construir respuesta final
            AuthResponseDto authResponseDto = new AuthResponseDto();
            authResponseDto.setToken(jwt);
            authResponseDto.setRefreshToken(refreshToken);

            return new ResponseEntity<>(authResponseDto, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error crítico en el proceso de login: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno en autenticación: " + e.getMessage());
        }
    }
}