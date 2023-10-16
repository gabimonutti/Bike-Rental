package g147bda2023.tpi.repositories;

import g147bda2023.tpi.models.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstacionRepository extends JpaRepository<Estacion, Long> {
    Optional<Estacion> findById(Long id);
}
