package g38.tpi.bda2023.Alquileres.repositories;

import g38.tpi.bda2023.Alquileres.models.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {
    @Query(value = "SELECT count(*) FROM ALQUILERES;", nativeQuery = true)
    Long getMaxId();

    List<Alquiler> findAllByEstadoAndMontoGreaterThan(int estado, BigDecimal monto);
    List<Alquiler> findAllByEstado(int estado);
    List<Alquiler> findAllByMontoGreaterThan(BigDecimal monto);
    List<Alquiler> findAll();
}
