package car.center_prueba.carcenter_prueba.repository;

import car.center_prueba.carcenter_prueba.model.Documento;
import car.center_prueba.carcenter_prueba.model.Mantenimientos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface MantenimientosRespository extends JpaRepository<Mantenimientos, Integer> {

    @Query("select max(m.codigo) from Mantenimientos m")
    Integer getCodigoMaximo();
}
