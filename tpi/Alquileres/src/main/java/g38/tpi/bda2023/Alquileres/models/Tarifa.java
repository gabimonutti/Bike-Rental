package g38.tpi.bda2023.Alquileres.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity(name = "TARIFAS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tarifa {
    @Id
    private Long id;

    @Column(name = "TIPO_TARIFA")
    private int tipoTarifa;

    @Column(name = "DEFINICIÓN")
    private char definicion;

    @Column(name = "DIA_SEMANA")
    private int diaSemana;

    @Column(name = "DIA_MES")
    private int diaMes;

    private int mes;

    private int anio;

    @Column(name = "MONTO_FIJO_ALQUILER")
    private BigDecimal montoFijoAlquiler;

    @Column(name = "MONTO_MINUTO_FRACCION")
    private BigDecimal montoMinutoFraccion;

    @Column(name = "MONTO_HORA")
    private BigDecimal montoHora;

    @Column(name = "MONTO_KM")
    private BigDecimal montoKm;
}
