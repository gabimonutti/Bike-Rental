package g38.tpi.bda2023.Alquileres.repositories;

import g38.tpi.bda2023.Alquileres.models.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {
    @Query(value = "SELECT count(*) FROM ALQUILERES;", nativeQuery = true)
    public Long getMaxId();
}
