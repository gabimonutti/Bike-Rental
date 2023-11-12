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
    private Long id;

    private String nombre;

    @Column(name = "FECHA_HORA_CREACION")
    private LocalDateTime fechaHoraCreacion;

    private double latitud;
    private double longitud;

    // Duda: Si yo quiero crear una clase coordenadas, como mapeo lat y long de la db con el objeto?
}
