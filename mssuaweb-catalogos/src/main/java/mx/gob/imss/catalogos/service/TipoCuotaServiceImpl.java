package mx.gob.imss.catalogos.service;

import mx.gob.imss.catalogos.dto.TipoCuotaDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("tipoCuotaService")
public class TipoCuotaServiceImpl implements TipoCuotaService {

    private static final Logger logger = LoggerFactory.getLogger(TipoCuotaServiceImpl.class);

    /**
     * Devuelve los tipos de cuota de forma estática mientras no exista tabla física.
     * Requerimiento: 1 = IMSS, 2 = RCV.
     */
    @Override
    public List<TipoCuotaDto> getTiposCuota() {
        logger.info("Generando lista de tipos de cuota (Valores estáticos)");
        
        List<TipoCuotaDto> listaTiposCuota = new ArrayList<>();

        // 1. Definición del valor IMSS con ID 1
        listaTiposCuota.add(new TipoCuotaDto(1L, "IMSS"));
        
        // 2. Definición del valor RCV con ID 2
        listaTiposCuota.add(new TipoCuotaDto(2L, "RCV"));

        logger.info("Se han generado {} registros de tipos de cuota.", listaTiposCuota.size());
        
        return listaTiposCuota;
    }
}