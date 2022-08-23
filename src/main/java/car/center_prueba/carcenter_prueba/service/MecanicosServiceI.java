package car.center_prueba.carcenter_prueba.service;

import car.center_prueba.carcenter_prueba.model.MecanicoRequest;
import car.center_prueba.carcenter_prueba.model.MecanicoResponse;
import org.springframework.stereotype.Service;


public interface MecanicosServiceI {

    MecanicoResponse agregarMecanicos(MecanicoRequest mecanicoRequest);

}
