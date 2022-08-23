package car.center_prueba.carcenter_prueba.service;

import car.center_prueba.carcenter_prueba.model.Documento;
import car.center_prueba.carcenter_prueba.model.MecanicoRequest;
import car.center_prueba.carcenter_prueba.model.MecanicoResponse;
import car.center_prueba.carcenter_prueba.model.Mecanicos;
import car.center_prueba.carcenter_prueba.repository.MecanicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MecanicoService implements MecanicosServiceI {

    @Autowired
    private ValidarRequest validarRequest;

    @Autowired
    private MecanicoRepository mecanicoRepository;

    public MecanicoResponse agregarMecanicos(MecanicoRequest mecanicoRequest) {
        MecanicoResponse mecanicoResponse= new MecanicoResponse();
        String mensaje = null;
        String status = null;
        if(mecanicoRequest != null){
            Optional<Mecanicos> mecanicoTemp = getmecanico(mecanicoRequest.getMecanicos().getTipoDocumento(), mecanicoRequest.getMecanicos().getDocumento());
           if(mecanicoTemp.isPresent()){
               mensaje = "Mecanico ya existe";
               status = "NOOK";
           }else{
               mensaje = validarRequest.verificarRequest(mecanicoRequest);
               if(mensaje == null){
                   Mecanicos mecanico = new Mecanicos();
                   if(mecanicoRequest.getMecanicos().getSegundoNombre()!=null){
                       mecanico.setSegundoNombre(validarRequest.capitalize(mecanicoRequest.getMecanicos().getSegundoNombre()));
                   }
                   if(mecanicoRequest.getMecanicos().getSegundoNombre()!=null){
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
                       status ="OK";
                       mensaje ="mecanico insertado correctamente";
                   }catch (Exception e){
                       e.printStackTrace();
                       status = "NOOK";
                   }

               }else{
                   status = "NOOK";
               }
           }

        }else{
            mensaje = "faltan los datos para crear el mecanino";
            status = "NOOK";
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
}
