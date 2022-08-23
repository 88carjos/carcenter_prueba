package car.center_prueba.carcenter_prueba.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class MecanicoRequest implements Serializable {

    private String token;
    private Mecanicos mecanicos;
}
