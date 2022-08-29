package car.center_prueba.carcenter_prueba.model;

import lombok.Data;

@Data
public class MantenimientoRequest {

    private String token;
    private Mantenimientos mantenimientos;
}
