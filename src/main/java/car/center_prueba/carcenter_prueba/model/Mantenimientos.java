package car.center_prueba.carcenter_prueba.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "mantenimientos",schema="carcenter")
public class Mantenimientos implements Serializable {

    @Id
    @Column(name="codigo")
    private Integer codigo;

    @Column(name="estado")
    private Integer estado;

    @Column(name="cod_placa")
    private String codPlaca;

    @Column(name="fecha")
    private LocalDate fecha;

    @Column(name="mec_documento")
    private Integer documento;

    @Column(name="mec_tipo_documento")
    private String tipoDocumento;



}
