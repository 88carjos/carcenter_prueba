package car.center_prueba.carcenter_prueba.controller;

import car.center_prueba.carcenter_prueba.model.MantenimientoRequest;
import car.center_prueba.carcenter_prueba.model.MantenimientoResponse;
import car.center_prueba.carcenter_prueba.model.MecanicoRequest;
import car.center_prueba.carcenter_prueba.model.MecanicoResponse;
import car.center_prueba.carcenter_prueba.service.MantenimientoServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mantenimientos")
public class MantenimientoController {

    @Autowired
    private MantenimientoServiceI mantenimientoServiceI;

    @RequestMapping(value="/agregarMantenimiento",method = RequestMethod.POST)
    public @ResponseBody
    MantenimientoResponse agregarMantenimiento(@RequestBody MantenimientoRequest mantenimientoRequest) {

        MantenimientoResponse mantenimientoResponse = mantenimientoServiceI.agregarMantenimiento(mantenimientoRequest);
        return mantenimientoResponse;
    }

}
