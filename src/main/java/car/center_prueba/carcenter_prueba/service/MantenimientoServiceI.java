package car.center_prueba.carcenter_prueba.service;

import car.center_prueba.carcenter_prueba.model.MantenimientoRequest;
import car.center_prueba.carcenter_prueba.model.MantenimientoResponse;

public interface MantenimientoServiceI {

    MantenimientoResponse agregarMantenimiento(MantenimientoRequest mantenimientoRequest);
}
