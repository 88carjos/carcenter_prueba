package car.center_prueba.carcenter_prueba.model;

import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;

@Data
public class Documento implements Serializable {

    private String tipoDocumento;
    private Integer documento;
}
