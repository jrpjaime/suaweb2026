package mx.gob.imss.seguridad.service;

import mx.gob.imss.seguridad.dto.UsuarioDto;
import mx.gob.imss.seguridad.entity.SwtUsuario;
import mx.gob.imss.seguridad.repository.SwtUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private SwtUsuarioRepository swtUsuarioRepository;

    @Override
    public Optional<UsuarioDto> getUsuarioInfoByRfc(String rfc) {
        // Buscamos en la nueva tabla SWT_USUARIO
        return swtUsuarioRepository.findByUsername(rfc)
                .map(this::mapEntityToDto); // Usamos referencia a método de Java 8
    }

    /**
     * Mapea los datos de la tabla SWT_USUARIO al DTO de transporte.
     */
    private UsuarioDto mapEntityToDto(SwtUsuario entity) {
        UsuarioDto dto = new UsuarioDto();
        dto.setRfc(entity.getRfc());
        dto.setNombre(entity.getNombre());
        dto.setPrimerApellido(entity.getApellidoPaterno());
        dto.setSegundoApellido(entity.getApellidoMaterno());
        // El campo DES_USUARIO se usa como identificador único
        dto.setCurp(entity.getUsername()); 
        dto.setIndBaja(false); // Lógica personalizada de negocio
        return dto;
    }
}