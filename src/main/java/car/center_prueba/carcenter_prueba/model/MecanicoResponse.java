package car.center_prueba.carcenter_prueba.model;

import lombok.Data;

import java.util.List;

@Data
public class MecanicoResponse  extends Response{

    List<Mecanicos> mecanicos;
}
