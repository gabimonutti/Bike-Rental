package g38.tpi.bda2023.Alquileres.application.controllers;

import g38.tpi.bda2023.Alquileres.application.ResponseHandler;
import g38.tpi.bda2023.Alquileres.application.requests.CreateAlquilerRequest;
import g38.tpi.bda2023.Alquileres.application.requests.EndAlquilerRequest;
import g38.tpi.bda2023.Alquileres.application.response.AlquilerResponse;
import g38.tpi.bda2023.Alquileres.application.response.InicioAlquilerResponse;
import g38.tpi.bda2023.Alquileres.services.AlquilerApplicationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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

    @GetMapping()
    public ResponseEntity<Object> getAll(){
        try{
           List<AlquilerResponse> alquileres = alquilerApplicationService.findAllAlquileres();
           return ResponseHandler.success(alquileres);
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } /*catch (Exception e) {
            return ResponseHandler.internalError();
        }*/
    }

    @GetMapping(params = { "idCliente" })
    public ResponseEntity<Object> getAllByEstado(@Valid @Positive @RequestParam int idCliente){
        try{
            List<AlquilerResponse> alquileres = alquilerApplicationService.findByIdCliente(idCliente);
            return ResponseHandler.success(alquileres);
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @GetMapping(params = { "monto" })
    public ResponseEntity<Object> getAllByMonto(@RequestParam @Valid @PositiveOrZero BigDecimal monto){
        try{
            List<AlquilerResponse> alquileres = alquilerApplicationService.findByMontoGtThan(monto);
            return ResponseHandler.success(alquileres);
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @GetMapping(params = { "idCliente", "monto" })
    public ResponseEntity<Object> getAllByIdClienteAndMontoGtThan(@Valid @Positive @RequestParam int idCliente,
                                                                  @Valid @PositiveOrZero @RequestParam BigDecimal monto){
        try{
            List<AlquilerResponse> alquileres = alquilerApplicationService.getAllByIdClienteAndMontoGreaterThan(idCliente, monto);
            return ResponseHandler.success(alquileres);
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

}
