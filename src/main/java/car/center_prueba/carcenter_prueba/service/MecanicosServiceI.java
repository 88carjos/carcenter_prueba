package car.center_prueba.carcenter_prueba.service;

import car.center_prueba.carcenter_prueba.model.MecanicoRequest;
import car.center_prueba.carcenter_prueba.model.MecanicoResponse;
import car.center_prueba.carcenter_prueba.model.Mecanicos;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MecanicosServiceI {

    MecanicoResponse agregarMecanicos(MecanicoRequest mecanicoRequest);

    MecanicoResponse getMecanicosDisponibles();


}
