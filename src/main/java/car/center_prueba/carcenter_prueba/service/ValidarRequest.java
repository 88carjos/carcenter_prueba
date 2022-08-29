package car.center_prueba.carcenter_prueba.service;

import car.center_prueba.carcenter_prueba.model.MecanicoRequest;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ValidarRequest {

    public String validarEmail(String email){

        String mensaje=null;
        String regx = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(email);
        if (email == null) {
            return   "el correo no puede estar vacio";
        }
        if(!matcher.matches()){
            mensaje="formato del correo no es valido";
        }

        return mensaje;
    }

    public String validarNombres(String nombre, String parte){
        String regex = "^[A-Za-z]\\w{3,29}$";
        String mensaje=null;
        Pattern pattern = Pattern.compile(regex);
        if (nombre == "") {
            if(parte.equals("PRIMER_NOMBRE") || parte.equals("PRIMER_APELLIDO")){
                return "el dato de primer nombre o apellido no puede ser vacio";
            }else{
                return mensaje;
            }

        }
        Matcher matcher = pattern.matcher(nombre);
        if(!matcher.matches()){
            mensaje="formato del "+ parte+" no es valido";
        }
        return mensaje;


    }

    public String validarCelular(String celular){

        String regex = "^ ((d {3})) ? [-]? (d {3}) [-]? (d {4}) $";
        String mensaje=null;
        Pattern pattern = Pattern.compile(regex);
        if (celular == null) {
             return "el numero de celular no puede ser vacio";
        }
        Matcher matcher = pattern.matcher(celular);
        if(!matcher.matches()){
            mensaje="formato del celular no es valido";
        }
        return mensaje;

    }

    public String validarTipoDoc(String tipoDocumento){
        String regex = "^[A-Za-z]\\w{1,1}$";
        String mensaje=null;
        Pattern pattern = Pattern.compile(regex);
        if (tipoDocumento == null) {
                return "el tipo de documento no puede ser vacio";
        }
        Matcher matcher = pattern.matcher(tipoDocumento);
        if(!matcher.matches()){
            mensaje="formato del tipoDocumento no es valido";
        }
        return mensaje;

    }

    public String validarDocumento(Integer documento){
        String mensaje = null;
        if(documento == null){
            mensaje = "el documento no puede ser vacio";
        }
         return  mensaje;
    }

    public String validarDireccion(String direccion) {

        String mensaje= null;
        if (direccion == null) {
            return "la direccion no puede ser vacia";
        }
        return mensaje;
    }

    public String validarestado(String estado){

        String mensaje = null;
        if(estado != null){
            if(estado.equals("A") || estado.equals("I") ){
                return mensaje;
            }else{
                mensaje = "el estado no es valido";
            }
        }else{
            mensaje = "el estado no puede estar vacio";
        }
        return mensaje;

    }

    public String verificarRequestMecanicos(MecanicoRequest mecanicoRequest){

        String mensaje = null;
        mensaje = validarTipoDoc(mecanicoRequest.getMecanicos().getTipoDocumento());

        mensaje = (mensaje != null) ?mensaje+"-" +validarDocumento(mecanicoRequest.getMecanicos().getDocumento()):
                                                    validarDocumento(mecanicoRequest.getMecanicos().getDocumento());

        mensaje = mensaje != null ? mensaje+"-" +validarNombres(mecanicoRequest.getMecanicos().getPrimerNombre(),"PRIMER_NOMBRE"):
                                                    validarNombres(mecanicoRequest.getMecanicos().getPrimerNombre(),"PRIMER_NOMBRE");

        mensaje = mensaje != null ? mensaje+"-" +validarNombres(mecanicoRequest.getMecanicos().getSegundoNombre(),"SEGUNDO_NOMBRE"):
                                                    validarNombres(mecanicoRequest.getMecanicos().getSegundoNombre(),"SEGUNDO_NOMBRE");

        mensaje = mensaje != null ? mensaje+"-" +validarNombres(mecanicoRequest.getMecanicos().getPrimerApellido(),"PRIMER_APELLIDO"):
                                                    validarNombres(mecanicoRequest.getMecanicos().getPrimerApellido(),"PRIMER_APELLIDO");

        mensaje = mensaje != null ? mensaje+"-" +validarNombres(mecanicoRequest.getMecanicos().getSegundoApellido(),"SEGUNDO_APELLIDO"):
                                                    validarNombres(mecanicoRequest.getMecanicos().getSegundoApellido(),"SEGUNDO_APELLIDO");


        mensaje = mensaje != null ? mensaje+"-" +validarEmail(mecanicoRequest.getMecanicos().getEmail()):
                                                    validarEmail(mecanicoRequest.getMecanicos().getEmail());

        mensaje = mensaje != null ? mensaje+"-" +validarDireccion(mecanicoRequest.getMecanicos().getDireccion()):
                                                    validarDireccion(mecanicoRequest.getMecanicos().getDireccion());

        mensaje = mensaje != null ? mensaje+"-" +   validarestado(mecanicoRequest.getMecanicos().getEstado()):
                                                        validarestado(mecanicoRequest.getMecanicos().getEstado());


        return  mensaje;
    }



    public  String capitalize(String str){
        if(str == null) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
