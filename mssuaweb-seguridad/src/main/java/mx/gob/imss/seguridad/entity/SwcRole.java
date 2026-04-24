package mx.gob.imss.seguridad.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "SWC_ROLE")
@Data
public class SwcRole {
    @Id
    @Column(name = "ID_ROLE")
    private Long idRole;

    @Column(name = "DES_ROLE")
    private String nombreRole;
}