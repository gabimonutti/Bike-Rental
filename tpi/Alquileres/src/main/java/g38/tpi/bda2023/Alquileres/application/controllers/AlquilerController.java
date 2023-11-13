package g38.tpi.bda2023.Alquileres.application.controllers;

import g38.tpi.bda2023.Alquileres.application.ResponseHandler;
import g38.tpi.bda2023.Alquileres.application.requests.CreateAlquilerRequest;
import g38.tpi.bda2023.Alquileres.application.requests.EndAlquilerRequest;
import g38.tpi.bda2023.Alquileres.application.response.AlquilerResponse;
import g38.tpi.bda2023.Alquileres.services.AlquilerApplicationService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/alquileres")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AlquilerController {
    AlquilerApplicationService alquilerApplicationService;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CreateAlquilerRequest request) {
        try {
            AlquilerResponse alquilerResponse = alquilerApplicationService.start(request.getIdCliente(), request.getIdEstRetiro());
            return ResponseHandler.created(alquilerResponse);
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
}
