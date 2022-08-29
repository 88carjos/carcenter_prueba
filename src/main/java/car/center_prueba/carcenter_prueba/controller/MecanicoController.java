package car.center_prueba.carcenter_prueba.controller;

import car.center_prueba.carcenter_prueba.model.MecanicoRequest;
import car.center_prueba.carcenter_prueba.model.MecanicoResponse;
import car.center_prueba.carcenter_prueba.model.Mecanicos;
import car.center_prueba.carcenter_prueba.service.MecanicosServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/mecanicos")
public class MecanicoController {

    @Autowired
    private MecanicosServiceI mecanicosServiceI;



    @RequestMapping(value="/agregarMecanicos",method = RequestMethod.POST)
    public @ResponseBody MecanicoResponse agregarMecanicos(@RequestBody MecanicoRequest mecanicoRequest) {

        MecanicoResponse mecanicoResponse = mecanicosServiceI.agregarMecanicos(mecanicoRequest);
        return mecanicoResponse;
    }


    @RequestMapping(value="/getMecanicosDisponibles",method = RequestMethod.GET)
    public @ResponseBody MecanicoResponse getMecanicosDisponibles() {

        MecanicoResponse mecanicos = mecanicosServiceI.getMecanicosDisponibles();
        return mecanicos;
    }
}
