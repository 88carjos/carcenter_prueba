package car.center_prueba.carcenter_prueba.service;

import car.center_prueba.carcenter_prueba.model.MantenimientoRequest;
import car.center_prueba.carcenter_prueba.model.MantenimientoResponse;
import car.center_prueba.carcenter_prueba.model.Mantenimientos;
import car.center_prueba.carcenter_prueba.model.Mecanicos;
import car.center_prueba.carcenter_prueba.repository.MantenimientosRespository;
import car.center_prueba.carcenter_prueba.repository.MecanicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MantenimientoService implements  MantenimientoServiceI {

    @Autowired
    private MantenimientosRespository mantenimientosRespository;

    @Autowired
    private MecanicoRepository mecanicoRepository;

    @Value("${app.parameters.notOK}")
    private String appParametersNotOK;
    @Value("${app.parameters.yesOK}")
    private String appParametersYesOK;


    @Override
    public MantenimientoResponse agregarMantenimiento(MantenimientoRequest mantenimientoRequest) {

        Mantenimientos mantenimiento = new Mantenimientos();
        MantenimientoResponse mantenimientoResponse = new MantenimientoResponse();
        String mensaje=null;
        String status;
        Integer codigo= mantenimientosRespository.getCodigoMaximo();
        try{
            Mecanicos mecanico= mecanicoRepository.getMecanicos(mantenimientoRequest.getMantenimientos().getTipoDocumento(),
                    mantenimientoRequest.getMantenimientos().getDocumento());
               if(mecanico != null){
                   mantenimiento.setCodigo(codigo+1);
                   mantenimiento.setCodPlaca(mantenimientoRequest.getMantenimientos().getCodPlaca());
                   mantenimiento.setEstado(mantenimientoRequest.getMantenimientos().getEstado());
                   mantenimiento.setFecha(LocalDate.now());
                   mantenimiento.setDocumento(mantenimientoRequest.getMantenimientos().getDocumento());
                   mantenimiento.setTipoDocumento(mantenimientoRequest.getMantenimientos().getTipoDocumento());
                   mantenimientosRespository.save(mantenimiento);
                   mensaje ="se agrego el manetenimiento";
                   status = appParametersYesOK;
               }else{
                   mensaje="el mecanico no existe no es posible la asignacion";
                   status = appParametersNotOK;
               }
        }catch (Exception e){
            mensaje="el mecanico no existe no es posible la asignacion";
            status = appParametersNotOK;

        }


        mantenimientoResponse.setMensaje(mensaje);
        mantenimientoResponse.setStatus(status);



        return mantenimientoResponse;
    }
}
