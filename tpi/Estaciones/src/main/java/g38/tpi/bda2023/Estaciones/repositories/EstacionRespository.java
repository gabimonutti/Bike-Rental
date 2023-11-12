package g38.tpi.bda2023.Estaciones.repositories;

import g38.tpi.bda2023.Estaciones.models.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EstacionRespository extends JpaRepository<Estacion, Long> {
    @Query("SELECT coalesce(max(id),0) from Estacion")
    Long getMaxId();
}
