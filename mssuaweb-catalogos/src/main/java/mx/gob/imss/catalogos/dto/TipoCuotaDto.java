package mx.gob.imss.catalogos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoCuotaDto {
    
    private Long cveIdTipoCuota;
    private String desTipoCuota;
}