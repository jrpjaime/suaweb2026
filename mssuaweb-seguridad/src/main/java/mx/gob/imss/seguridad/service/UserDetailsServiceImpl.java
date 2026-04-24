package mx.gob.imss.seguridad.service;
 
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mx.gob.imss.seguridad.entity.SwtUsuario;
import mx.gob.imss.seguridad.repository.SwtUsuarioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    private SwtUsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Buscar el usuario en la tabla SWT_USUARIO
        SwtUsuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        // 2. Convertir los roles de la base de datos (SWC_ROLE) a autoridades de Spring Security
        List<SimpleGrantedAuthority> autoridades = usuario.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getNombreRole()))
                .collect(Collectors.toList());

        // 3. Retornar el objeto User de Spring Security con los datos reales
        return new User(usuario.getUsername(), usuario.getPassword(), autoridades);
    }
}

