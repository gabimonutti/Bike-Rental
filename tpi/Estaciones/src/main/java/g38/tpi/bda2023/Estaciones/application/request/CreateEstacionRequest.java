package g38.tpi.bda2023.Estaciones.application.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateEstacionRequest {
    @NotBlank(message = "Nombre is required")
    String nombre;
    @NotNull(message = "Latitud is required")
    @Max(value = 90, message = "Latitud value is invalid")
    @Min(value = -90, message = "Latitud value is invalid")
    double latitud;
    @NotNull(message = "Longitud is required")
    @Max(value = 180, message = "Longitud value is invalid")
    @Min(value = -180, message = "Longitud value is invalid")
    double longitud;
}
