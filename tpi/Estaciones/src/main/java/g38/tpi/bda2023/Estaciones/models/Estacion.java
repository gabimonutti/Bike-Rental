package g38.tpi.bda2023.Estaciones.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity(name = Estacion.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Estacion {
    public static final String TABLE_NAME = "ESTACIONES";
    @Id
    @NotNull
    Long id;
    @NotEmpty
    String nombre;
    @Column(name = "FECHA_HORA_CREACION")
    LocalDateTime fechaHoraCreacion;
    @NotNull(message = "Longitud is required")
    @Max(value = 180, message = "Latitud value is invalid")
    @Min(value = -180, message = "Latitud value is invalid")
    double latitud;
    @NotNull(message = "Longitud is required")
    @Max(value = 180, message = "Longitud value is invalid")
    @Min(value = -180, message = "Longitud value is invalid")
    double longitud;

    public Estacion(Long id, String nombre, double latitud, double longitud) {
        this.id = id;
        this.nombre = nombre;
        this.fechaHoraCreacion = LocalDateTime.now();
        this.latitud = latitud;
        this.longitud = longitud;

    }
}
