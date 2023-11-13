package g38.tpi.bda2023.Alquileres.models;

import g38.tpi.bda2023.Alquileres.application.response.EstacionResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;

@Entity(name = Estacion.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Estacion {
    public static final String TABLE_NAME = "ESTACIONES";

    @Id
    private Long id;

    private String nombre;

    @Column(name = "FECHA_HORA_CREACION")
    private LocalDateTime fechaHoraCreacion;

    private double latitud;
    private double longitud;

    public static Estacion convertToEstacion(LinkedHashMap<String, Object> map) {
        String timeFromDB = map.get("fechaHoraCreacion").toString().split("\\.")[0];
        LocalDateTime fechaHoraCreacion = LocalDateTime.parse(timeFromDB, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));

        return new Estacion(
                Long.valueOf(map.get("id").toString()),
                (String) map.get("nombre"),
                fechaHoraCreacion,
                (Double) map.get("latitud"),
                (Double) map.get("longitud")
        );
    }
}
