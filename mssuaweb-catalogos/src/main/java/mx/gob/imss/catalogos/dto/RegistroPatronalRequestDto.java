package mx.gob.imss.catalogos.dto;

import lombok.Data;

/**
 * Objeto para recibir el RFC en el cuerpo de la petición POST.
 */
@Data
public class RegistroPatronalRequestDto {
    private String rfc;
}