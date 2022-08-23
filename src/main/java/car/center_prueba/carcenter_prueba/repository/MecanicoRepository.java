package car.center_prueba.carcenter_prueba.repository;

import car.center_prueba.carcenter_prueba.model.Documento;
import car.center_prueba.carcenter_prueba.model.Mecanicos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MecanicoRepository extends JpaRepository<Mecanicos, Documento> {


}
