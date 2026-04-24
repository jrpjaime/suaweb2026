package mx.gob.imss.catalogos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistroPatronalDto {
    private String registroPatronal;
    private String razonSocial; // Nombre corregido para coincidir con tu Service
    private String rfc;
    private String domicilio;
}