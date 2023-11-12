package g38.tpi.bda2023.Alquileres.application.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateAlquilerRequest {
    @NotNull
    long idCliente;
    @NotNull
    long idEstRetiro;
}
