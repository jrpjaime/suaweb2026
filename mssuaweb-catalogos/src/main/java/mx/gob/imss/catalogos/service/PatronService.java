package mx.gob.imss.catalogos.service;

import mx.gob.imss.catalogos.dto.RegistroPatronalDto;
import mx.gob.imss.catalogos.dto.RegistroPatronalRequestDto;
import java.util.List;

public interface PatronService {
    /**
     * Obtiene la lista de registros patronales asociados a un RFC.
     * @param request DTO con el RFC del patrón.
     * @return Lista de DTOs con la información de registros patronales.
     */
    List<RegistroPatronalDto> obtenerRegistrosPatronalesPorRfc(RegistroPatronalRequestDto request);
}