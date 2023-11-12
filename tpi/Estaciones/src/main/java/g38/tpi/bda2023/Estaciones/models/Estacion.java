package g38.tpi.bda2023.Estaciones.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity(name = "ESTACIONES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Estacion {
    @Id
    Long id;

    String nombre;

    @Column(name = "FECHA_HORA_CREACION")
    LocalDateTime fechaHoraCreacion;

    double latitud;
    double longitud;
}
