package mx.gob.imss.catalogos.repository;

import mx.gob.imss.catalogos.entity.SwtPatron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SwtPatronRepository extends JpaRepository<SwtPatron, Long> {

    /**
     * Busca registros patronales por RFC que no tengan fecha de baja.
     * @param rfc Registro Federal de Contribuyentes.
     * @return Lista de entidades SwtPatron.
     */
    List<SwtPatron> findByRefRfcAndFecRegistroBajaIsNull(String rfc);
}