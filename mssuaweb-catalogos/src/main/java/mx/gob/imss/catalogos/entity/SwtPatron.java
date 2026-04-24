package mx.gob.imss.catalogos.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "SWT_PATRON")
public class SwtPatron implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID_PATRON")
    private Long idPatron;

    @Column(name = "REF_RFC", length = 13)
    private String refRfc;

    @Column(name = "NOM_RAZON_SOCIAL", length = 300)
    private String nomRazonSocial;

    @Column(name = "CVE_REGISTRO_PATRONAL", length = 11)
    private String cveRegistroPatronal;

    @Column(name = "REF_DOMICILIO", length = 300)
    private String refDomicilio;

    @Column(name = "FEC_REGISTRO_ALTA")
    private LocalDate fecRegistroAlta;

    @Column(name = "FEC_REGISTRO_BAJA")
    private LocalDate fecRegistroBaja;
 
}