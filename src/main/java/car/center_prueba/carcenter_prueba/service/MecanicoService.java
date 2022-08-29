package car.center_prueba.carcenter_prueba.service;

import car.center_prueba.carcenter_prueba.model.Documento;
import car.center_prueba.carcenter_prueba.model.MecanicoRequest;
import car.center_prueba.carcenter_prueba.model.MecanicoResponse;
import car.center_prueba.carcenter_prueba.model.Mecanicos;
import car.center_prueba.carcenter_prueba.repository.MecanicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MecanicoService implements MecanicosServiceI {

    @Autowired
    private ValidarRequest validarRequest;

    @Autowired
    private MecanicoRepository mecanicoRepository;

    @Value("${app.parameters.notOK}")
    private String appParametersNotOK;
    @Value("${app.parameters.yesOK}")
    private String appParametersYesOK;

    public MecanicoResponse agregarMecanicos(MecanicoRequest mecanicoRequest) {

        MecanicoResponse mecanicoResponse= new MecanicoResponse();
        String mensaje = null;
        String status = null;
        if(mecanicoRequest != null){
            Optional<Mecanicos> mecanicoTemp = getmecanico(mecanicoRequest.getMecanicos().getTipoDocumento(), mecanicoRequest.getMecanicos().getDocumento());
           if(mecanicoTemp.isPresent()){
               mensaje = "Mecanico ya existe";
               status = appParametersNotOK;
           }else{
               mensaje = validarRequest.verificarRequestMecanicos(mecanicoRequest);
               if(mensaje == null){
                   Mecanicos mecanico = new Mecanicos();
                   if(mecanicoRequest.getMecanicos().getSegundoNombre()!=""){
                       mecanico.setSegundoNombre(validarRequest.capitalize(mecanicoRequest.getMecanicos().getSegundoNombre()));
                   }
                   if(mecanicoRequest.getMecanicos().getSegundoApellido()!=""){
                       mecanico.setSegundoApellido(validarRequest.capitalize(mecanicoRequest.getMecanicos().getSegundoApellido()));
                   }
                   mecanico.setTipoDocumento(mecanicoRequest.getMecanicos().getTipoDocumento());
                   mecanico.setDocumento(mecanicoRequest.getMecanicos().getDocumento());
                   mecanico.setPrimerNombre(validarRequest.capitalize(mecanicoRequest.getMecanicos().getPrimerNombre()));
                   mecanico.setPrimerApellido(validarRequest.capitalize(mecanicoRequest.getMecanicos().getPrimerApellido()));
                   mecanico.setCelular(mecanicoRequest.getMecanicos().getCelular());
                   mecanico.setDireccion(mecanicoRequest.getMecanicos().getDireccion());
                   mecanico.setEmail(mecanicoRequest.getMecanicos().getEmail());
                   mecanico.setEstado(mecanicoRequest.getMecanicos().getEstado());
                   try{
                       mecanicoRepository.save(mecanico);
                       status =appParametersYesOK;
                       mensaje ="mecanico insertado correctamente";
                   }catch (Exception e){
                       e.printStackTrace();
                       status = appParametersNotOK;
                   }

               }else{
                   status = appParametersNotOK;
               }
           }

        }else{
            mensaje = "faltan los datos para crear el mecanino";
            status = appParametersNotOK;
        }
        mecanicoResponse.setMensaje(mensaje);
        mecanicoResponse.setStatus(status);

        return mecanicoResponse;
    }

    public Optional<Mecanicos> getmecanico(String tipoDocumento, Integer documento){

        Documento documento1 = new Documento();
        Optional<Mecanicos> mecanico = Optional.of(new Mecanicos());

        documento1.setTipoDocumento(tipoDocumento);
        documento1.setDocumento(documento);
        mecanico = mecanicoRepository.findById(documento1);

        return mecanico;

    }

    public MecanicoResponse getMecanicosDisponibles(){

        String mensaje=null;
        String status = null;
        List<Mecanicos> mecanicosSinAsignacion = new ArrayList<>();
        MecanicoResponse mecanicoResponse = new MecanicoResponse();
        try{
            LocalDate fecha =LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
            mecanicosSinAsignacion = mecanicoRepository.getUMecanicosLibres(fecha);
            mecanicosSinAsignacion.addAll(mecanicoRepository.getUMecanicosHorasEstimadas(fecha)) ;
        }catch (Exception e){
            e.printStackTrace();
        }
        if(mecanicosSinAsignacion.size()==0){
            mensaje ="no es posible obterner el listado de los Mecanicos";
            status = appParametersNotOK;
        }else{
            mecanicoResponse.setMecanicos(mecanicosSinAsignacion);
            status = appParametersYesOK;
        }

        mecanicoResponse.setStatus(status);
        mecanicoResponse.setMensaje(mensaje);

        return mecanicoResponse;


    }
}
