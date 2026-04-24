package mx.gob.imss.seguridad.repository;

import mx.gob.imss.seguridad.entity.SwtUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SwtUsuarioRepository extends JpaRepository<SwtUsuario, Long> {
    // Buscamos por el campo DES_USUARIO que es el que se usa en el login
     Optional<SwtUsuario> findByUsername(String username);
}