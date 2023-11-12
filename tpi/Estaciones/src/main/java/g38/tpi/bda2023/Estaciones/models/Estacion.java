package g38.tpi.bda2023.Estaciones.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    Long id;
    String nombre;
    double latitud;
    double longitud;
}
