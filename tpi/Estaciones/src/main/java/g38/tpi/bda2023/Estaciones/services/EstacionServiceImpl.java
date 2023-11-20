package g38.tpi.bda2023.Estaciones.services;

import g38.tpi.bda2023.Estaciones.models.Estacion;
import g38.tpi.bda2023.Estaciones.repositories.EstacionRespository;
import g38.tpi.bda2023.Estaciones.repositories.IdentifierRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.stereotype.Service;

import java.lang.instrument.IllegalClassFormatException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class EstacionServiceImpl implements EstacionService{
    EstacionRespository estacionRespository;
    IdentifierRepository identifierRepository;
    DistanciaService distanciaService;

    @Override
    public List<Estacion> findAll() {
        return estacionRespository.findAll();
    }

    @Override
    public Estacion findEstacionMasCercana(double latitud, double longitud) {
        // Max distance which someone can make this query (in kilometers)
        double maxDistanceKm = 50;

        if(latitud > 90 | latitud < -90) { throw new IllegalStateException("Invalid latitud value"); }
        if(longitud > 90 | longitud < -90) { throw new IllegalStateException("Invalid longitud value"); }

        List<Estacion> estaciones = findAll();
        Estacion resultado = estaciones.stream()
                .min(Comparator.comparingDouble(estacion -> distanciaService.calcularDistancia(latitud, longitud, estacion.getLatitud(), estacion.getLongitud())))
                .orElse(null);
        if(distanciaService.calcularDistancia(latitud, longitud, resultado.getLatitud(), resultado.getLongitud())/1000 > maxDistanceKm) {
            throw new IllegalStateException("Location is too far from stations");
        }
        return resultado;
    }

    @Override
    public Optional<Estacion> findById(Long id) {
        return estacionRespository.findById(id);
    }

    @Override
    public Estacion create(String nombre, double latitud, double longitud) {
        val estacionId = identifierRepository.nextValue(Estacion.TABLE_NAME);
        val newEstacion = new Estacion((long)estacionId, nombre, latitud, longitud);
        return estacionRespository.save(newEstacion);
    }
}
