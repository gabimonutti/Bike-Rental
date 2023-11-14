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
import java.util.List;

@RequestMapping("/api/alquileres")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AlquilerController {
    AlquilerApplicationService alquilerApplicationService;

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

    @GetMapping("/all")
    public ResponseEntity<Object> getAll(){
        try{
           List<AlquilerResponse> alquileres = alquilerApplicationService.findAllAlquileres();
           return ResponseHandler.success(alquileres);
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @GetMapping("/{estado}")
    public ResponseEntity<Object> getAllByEstado(@PathVariable int estado){
        try{
            List<AlquilerResponse> alquileres = alquilerApplicationService.findByEstado(estado);
            return ResponseHandler.success(alquileres);
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @GetMapping("/monto/{monto}")
    public ResponseEntity<Object> getAllByEstado(@PathVariable BigDecimal monto){
        try{
            List<AlquilerResponse> alquileres = alquilerApplicationService.findByMontoGtThan(monto);
            return ResponseHandler.success(alquileres);
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @GetMapping("/{estado}/{monto}")
    public ResponseEntity<Object> getAllByEstadoAndMontoGtThan(@PathVariable int estado, @PathVariable BigDecimal monto){
        try{
            List<AlquilerResponse> alquileres = alquilerApplicationService.findByEstadoAndMontoGtThan(estado, monto);
            return ResponseHandler.success(alquileres);
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

}
