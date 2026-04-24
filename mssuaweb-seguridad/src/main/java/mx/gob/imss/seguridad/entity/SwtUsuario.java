package mx.gob.imss.seguridad.entity;
 

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Entity
@Table(name = "SWT_USUARIO")
@Data
public class SwtUsuario {
    @Id
    @Column(name = "ID_USUARIO")
    private Long idUsuario;

    @Column(name = "NOM_NOMBRE")
    private String nombre;

    @Column(name = "NOM_APELLIDO_PATERNO")
    private String apellidoPaterno;

    @Column(name = "NOM_APELLIDO_MATERNO")
    private String apellidoMaterno;

    @Column(name = "DES_USUARIO") // Este será nuestro 'username' para login
    private String username;

    @Column(name = "DES_PASSWORD")
    private String password;

    @Column(name = "REF_RFC")
    private String rfc;

    // Relación Muchos a Muchos con Roles a través de la tabla intermedia
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "SWT_USUARIO_ROLE",
        joinColumns = @JoinColumn(name = "ID_USUARIO"),
        inverseJoinColumns = @JoinColumn(name = "ID_ROLE")
    )
    private Set<SwcRole> roles;
}