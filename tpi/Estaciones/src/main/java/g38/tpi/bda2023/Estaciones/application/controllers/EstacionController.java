package g38.tpi.bda2023.Estaciones.application.controllers;

import g38.tpi.bda2023.Estaciones.application.ResponseHandler;
import g38.tpi.bda2023.Estaciones.application.response.EstacionResponse;
import g38.tpi.bda2023.Estaciones.models.Estacion;
import g38.tpi.bda2023.Estaciones.services.EstacionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class EstacionController {
    EstacionService estacionService;

//    @GetMapping(params = { "latitud", "longitud" })
//    public ResponseEntity<Object> findEstacionMasCercana(@RequestParam double latitud, @RequestParam double longitud) {
//        try {
//            val estaciones = estacionService.findAll()
//                    .stream()
//                    .map(EstacionResponse::from)
//                    .toList();
//            return ResponseHandler.success();
//        } catch (IllegalArgumentException e) {
//            return ResponseHandler.notFound();
//        } catch (Exception e) {
//            return ResponseHandler.internalError();
//        }
//    }
public double calcularDistancia(double latitud1, double longitud1, double latitud2, double longitud2) {
    double radioTierra = 6371.0; // radio de la tierra en kilómetros
    double dLat = Math.toRadians(latitud2 - latitud1);
    double dLon = Math.toRadians(longitud2 - longitud1);
    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
            Math.cos(Math.toRadians(latitud1)) * Math.cos(Math.toRadians(latitud2)) *
                    Math.sin(dLon / 2) * Math.sin(dLon / 2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    return radioTierra * c; // distancia en kilómetros
}
@GetMapping("/api/estacion-cercana/{latitud} {longitud}")
public ResponseEntity<Object> findEstacionMasCercana(@PathVariable double latitud, @PathVariable double longitud) {
    try {
        List<Estacion> estaciones = estacionService.findAll();
        Estacion estacionCercana = null;
        double distanciaMinima = Double.MAX_VALUE;

        for (Estacion estacion : estaciones) {
            double distancia = calcularDistancia(latitud, longitud, estacion.getLatitud(), estacion.getLongitud());
            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                estacionCercana = estacion;
            }
        }

        EstacionResponse estacionResponse = EstacionResponse.from(estacionCercana);
        return ResponseHandler.success(estacionResponse);
    } catch (IllegalArgumentException e) {
        return ResponseHandler.notFound();
    } catch (Exception e) {
        return ResponseHandler.internalError();
    }
}
    @GetMapping("/api/estaciones-disponibles")
    public ResponseEntity<Object> getAll() {
        try {
            val estaciones = estacionService.findAll()
                    .stream()
                    .map(EstacionResponse::from)
                    .toList();
            return ResponseHandler.success(estaciones);
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }
}
