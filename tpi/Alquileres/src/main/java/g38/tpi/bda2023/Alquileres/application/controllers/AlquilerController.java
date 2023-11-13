package g38.tpi.bda2023.Alquileres.application.controllers;

import g38.tpi.bda2023.Alquileres.application.ResponseHandler;
import g38.tpi.bda2023.Alquileres.application.requests.CreateAlquilerRequest;
import g38.tpi.bda2023.Alquileres.application.requests.EndAlquilerRequest;
import g38.tpi.bda2023.Alquileres.application.response.AlquilerResponse;
import g38.tpi.bda2023.Alquileres.application.response.InicioAlquilerResponse;
import g38.tpi.bda2023.Alquileres.services.AlquilerApplicationService;
import g38.tpi.bda2023.Alquileres.services.AlquilerService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RequestMapping("/api/alquileres")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AlquilerController {
    AlquilerApplicationService alquilerApplicationService;
    AlquilerService alquilerService;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CreateAlquilerRequest request) {
        try {
            InicioAlquilerResponse alquiler = alquilerApplicationService.start(request.getIdCliente(), request.getIdEstRetiro());
            return ResponseHandler.created(alquiler);
        } catch(IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PatchMapping("/finalizar")
    public ResponseEntity<Object> end(@Valid @RequestBody EndAlquilerRequest request) {
        try {
            AlquilerResponse updatedAlquiler = alquilerApplicationService.end(request.getIdAlquiler(),
                    request.getIdEstacionDevolucion(), request.getMoneda());
            return ResponseHandler.success(updatedAlquiler);
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

//    @GetMapping
//    public ResponseEntity<Object> getAll() {
//        try {
//            val estaciones = alquilerService.findAll()
//                    .stream()
//                    .map(AlquilerResponse::from)
//                    .toList();
//            return ResponseHandler.success(estaciones);
//        } catch (IllegalArgumentException e) {
//            return ResponseHandler.badRequest(e.getMessage());
//        } catch (Exception e) {
//            return ResponseHandler.internalError();
//        }
//    }
@GetMapping
public ResponseEntity<Object> getAll(@RequestParam BigDecimal monto, @RequestParam int estado) {
    try {
        val estaciones = alquilerService.findAll()
                .stream()
                .filter(alquiler -> alquiler.getMonto().compareTo(monto) >= 0 && alquiler.getEstado() == estado)
                .map(AlquilerResponse::from)
                .toList();
        return ResponseHandler.success(estaciones);
    } catch (IllegalArgumentException e) {
        return ResponseHandler.badRequest(e.getMessage());
    } catch (Exception e) {
        return ResponseHandler.internalError();
    }
}
}
