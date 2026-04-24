package mx.gob.imss.catalogos.service;

import mx.gob.imss.catalogos.dto.TipoCuotaDto;
import java.util.List;

public interface TipoCuotaService {
    /**
     * Obtiene el listado de tipos de cuota.
     */
    List<TipoCuotaDto> getTiposCuota();
}