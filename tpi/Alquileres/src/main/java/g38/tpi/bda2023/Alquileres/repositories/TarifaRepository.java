package g38.tpi.bda2023.Alquileres.repositories;

import g38.tpi.bda2023.Alquileres.models.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TarifaRepository extends JpaRepository<Tarifa, Long> {
    Optional<Tarifa> findByDefinicionAndDiaMesAndMesAndAnio(char definicion, int diaMes, int mes, int anio);

    Optional<Tarifa> findByDiaSemana(int diaSemana);
}
