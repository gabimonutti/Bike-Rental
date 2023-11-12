package g38.tpi.bda2023.Estaciones.application.controllers;

import g38.tpi.bda2023.Estaciones.application.ResponseHandler;
import g38.tpi.bda2023.Estaciones.application.response.EstacionResponse;
import g38.tpi.bda2023.Estaciones.services.EstacionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/estaciones")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class EstacionController {
    EstacionService estacionService;

    @GetMapping(params = { "latitud", "longitud" })
    public ResponseEntity<Object> findEstacionMasCercana(@RequestParam double latitud, @RequestParam double longitud) {
        try {
            val estaciones = estacionService.findAll()
                    .stream()
                    .map(EstacionResponse::from)
                    .toList();
            return ResponseHandler.success();
        } catch (IllegalArgumentException e) {
            return ResponseHandler.notFound();
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }
}
