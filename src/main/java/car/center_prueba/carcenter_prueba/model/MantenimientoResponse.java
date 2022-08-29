package car.center_prueba.carcenter_prueba.model;

import lombok.Data;

import java.util.List;

@Data
public class MantenimientoResponse extends Response {

    List<Mantenimientos> mantenimientos;
}
