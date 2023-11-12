package g38.tpi.bda2023.Estaciones.application.controllers;

import g38.tpi.bda2023.Estaciones.application.ResponseHandler;
import g38.tpi.bda2023.Estaciones.application.response.EstacionResponse;
import g38.tpi.bda2023.Estaciones.models.Estacion;
import g38.tpi.bda2023.Estaciones.services.DistanciaService;
import g38.tpi.bda2023.Estaciones.services.EstacionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RequestMapping("/api/estaciones")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class EstacionController {
    EstacionService estacionService;
    DistanciaService distanciaService;

    @GetMapping
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

    @GetMapping("/estacion-mas-cercana")
    public ResponseEntity<Object> findEstacionMasCercana(@RequestParam("latitud") double latitud, @RequestParam("longitud") double longitud) {
        try {
            val estaciones = estacionService.findAll()
                    .stream()
                    .map(EstacionResponse::from)
                    .toList();

            EstacionResponse estacionCercana = estaciones.stream()
                    .min(Comparator.comparingDouble(estacion -> distanciaService.calcularDistancia(latitud, longitud, estacion.getLatitud(), estacion.getLongitud())))
                    .orElse(null);

            return ResponseHandler.success(estacionCercana);
        } catch (IllegalArgumentException e) {
            return ResponseHandler.notFound();
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }
}
