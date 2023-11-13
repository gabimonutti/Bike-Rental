package g38.tpi.bda2023.Alquileres.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class DistanciaService {
    public double calcularDistancia(double latitud1, double longitud1, double latitud2, double longitud2) {
        double metersPerDegree = 110000;

        double deltaX = (longitud2 - longitud1) * metersPerDegree;
        double deltaY = (latitud2 - latitud1) * metersPerDegree;

        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}
