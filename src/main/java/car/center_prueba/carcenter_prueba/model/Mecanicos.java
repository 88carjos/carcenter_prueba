package car.center_prueba.carcenter_prueba.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "mecanicos",schema="carcenter")
@IdClass(value = Documento.class)
public class Mecanicos implements Serializable {

    @Id
    @Column(name="tipo_documento")
    private String tipoDocumento;

    @Id
    @Column(name="documento")
    private Integer documento;

    @Column(name="primer_nombre")
    private String primerNombre;

    @Column(name="segundo_nombre")
    private String segundoNombre;

    @Column(name="primer_apellido")
    private String primerApellido;

    @Column(name="segundo_apellido")
    private String segundoApellido;

    @Column(name="celular")
    private String celular;

    @Column(name="direccion")
    private String direccion;

    @Column(name="email")
    private String email;

    @Column(name="estado")
    private String estado;



}
