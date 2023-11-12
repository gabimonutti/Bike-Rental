package g38.tpi.bda2023.Estaciones.services;

import g38.tpi.bda2023.Estaciones.models.Estacion;
import g38.tpi.bda2023.Estaciones.repositories.EstacionRespository;
import g38.tpi.bda2023.Estaciones.repositories.IdentifierRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class EstacionServiceImpl implements EstacionService{
    EstacionRespository estacionRespository;
    IdentifierRepository identifierRepository;

    @Override
    public List<Estacion> findAll() {
        return estacionRespository.findAll();
    }

    @Override
    public Optional<Estacion> findById(Long id) {
        return estacionRespository.findById(id);
    }

    @Override
    public Estacion create(String nombre, double latitud, double longitud) {
        val estacionId = identifierRepository.nextValue(Estacion.TABLE_NAME);

        // TODO: Cambiar tipo de dato de tiempo

        val fechaHoraCreacion = LocalDateTime.now();
        val newEstacion = new Estacion((long)estacionId, nombre, fechaHoraCreacion, latitud, longitud);
        return estacionRespository.save(newEstacion);
    }
}
