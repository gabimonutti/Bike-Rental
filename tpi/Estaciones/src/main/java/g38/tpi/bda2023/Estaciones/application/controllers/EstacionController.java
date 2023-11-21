package g38.tpi.bda2023.Estaciones.application.controllers;

import g38.tpi.bda2023.Estaciones.application.ResponseHandler;
import g38.tpi.bda2023.Estaciones.application.request.CreateEstacionRequest;
import g38.tpi.bda2023.Estaciones.application.response.EstacionResponse;
import g38.tpi.bda2023.Estaciones.models.Estacion;
import g38.tpi.bda2023.Estaciones.services.DistanciaService;
import g38.tpi.bda2023.Estaciones.services.EstacionService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
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

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable Long id) {
        try {
            return estacionService.findById(id)
                    .map(EstacionResponse::from)
                    .map(ResponseHandler::success)
                    .orElseGet(ResponseHandler::notFound);
        } catch (IllegalArgumentException e) {
            return ResponseHandler.notFound();
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @GetMapping("/estacion-mas-cercana")
    public ResponseEntity<Object> findEstacionMasCercana(@Valid @RequestParam("latitud") double latitud, @Valid @RequestParam("longitud") double longitud) {
        try {
            Estacion estacionCercana = estacionService.findEstacionMasCercana(latitud, longitud);
            return ResponseHandler.success(EstacionResponse.from(estacionCercana));
        } catch (IllegalStateException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseHandler.notFound();
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CreateEstacionRequest estacionRequest) {
        try {
            val estacion = estacionService.create(
                    estacionRequest.getNombre(),
                    estacionRequest.getLatitud(),
                    estacionRequest.getLongitud());
            return ResponseHandler.success(EstacionResponse.from(estacion));
        } catch (IllegalArgumentException | IllegalStateException | ConstraintViolationException e) {
            // por algun motivo las validaciones hechas con annotations no le lee el mensaje
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }
}
