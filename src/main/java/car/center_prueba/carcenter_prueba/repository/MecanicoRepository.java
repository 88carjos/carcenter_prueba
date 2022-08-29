package car.center_prueba.carcenter_prueba.repository;

import car.center_prueba.carcenter_prueba.model.Documento;
import car.center_prueba.carcenter_prueba.model.Mecanicos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public interface MecanicoRepository extends JpaRepository<Mecanicos, Documento> {

    @Query(value ="select * from carcenter.mecanicos me where me.documento not in (select mec_documento from carcenter.mantenimientos ma where ma.fecha >= :date) and me.estado ='A'",nativeQuery=true)
    List<Mecanicos> getUMecanicosLibres(@Param("date") LocalDate date);

    @Query(value = "select me.tipo_documento,me.documento,me.primer_nombre,me.segundo_nombre,me.primer_apellido,me.segundo_apellido,me.celular," +
                " me.direccion,me.email, me.estado " +
            " from carcenter.mecanicos me " +
            "inner join carcenter.mantenimientos ma " +
            "on me.documento = ma.mec_documento " +
            "and me.tipo_documento = ma.mec_tipo_documento " +
            "inner join carcenter.serivicios_x_mantenimientos sm " +
            "on sm.cod_mantenimiento = ma.codigo  where me.estado='A'" +
            "and ma.fecha >= :date group by me.documento, me.tipo_documento " +
            "order by  sum (sm.tiempo_estimado) asc",nativeQuery=true)
    List<Mecanicos> getUMecanicosHorasEstimadas(@Param("date") LocalDate date);

    @Query("SELECT m FROM Mecanicos m where m.tipoDocumento = :tipoDocumento and m.documento = :documento")
    Mecanicos getMecanicos(@Param("tipoDocumento") String tipoDocumento,@Param("documento") Integer documento );

}
