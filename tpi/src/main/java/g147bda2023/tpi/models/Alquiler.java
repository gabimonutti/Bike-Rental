package g147bda2023.tpi.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Alquileres")
public class Alquiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ID_CLIENTE")
    private Long idCliente;

    private int estado;

    @ManyToOne
    @JoinColumn(name = "ESTACION_RETIRO")
    private Estacion estacionRetiro;

    @ManyToOne
    @JoinColumn(name = "ESTACION_DEVOLUCION")
    private Estacion estacionDevolucion;

    @Column(name = "FECHA_HORA_RETIRO")
    private LocalDateTime fechaHoraRetiro;

    @Column(name = "FECHA_HORA_DEVOLUCION")
    private LocalDateTime fechaHoraDevolucion;

    private double monto;

    @ManyToOne
    @JoinColumn(name = "ID_TARIFA")
    private Tarifa tarifa;

    public Alquiler() {}

    public Alquiler(Long id, Long idCliente, int estado, Estacion estacionRetiro, Estacion estacionDevolucion, LocalDateTime fechaHoraRetiro, LocalDateTime fechaHoraDevolucion, double monto, Tarifa tarifa) {
        this.id = id;
        this.idCliente = idCliente;
        this.estado = estado;
        this.estacionRetiro = estacionRetiro;
        this.estacionDevolucion = estacionDevolucion;
        this.fechaHoraRetiro = fechaHoraRetiro;
        this.fechaHoraDevolucion = fechaHoraDevolucion;
        this.monto = monto;
        this.tarifa = tarifa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Estacion getEstacionRetiro() {
        return estacionRetiro;
    }

    public void setEstacionRetiro(Estacion estacionRetiro) {
        this.estacionRetiro = estacionRetiro;
    }

    public Estacion getEstacionDevolucion() {
        return estacionDevolucion;
    }

    public void setEstacionDevolucion(Estacion estacionDevolucion) {
        this.estacionDevolucion = estacionDevolucion;
    }

    public LocalDateTime getFechaHoraRetiro() {
        return fechaHoraRetiro;
    }

    public void setFechaHoraRetiro(LocalDateTime fechaHoraRetiro) {
        this.fechaHoraRetiro = fechaHoraRetiro;
    }

    public LocalDateTime getFechaHoraDevolucion() {
        return fechaHoraDevolucion;
    }

    public void setFechaHoraDevolucion(LocalDateTime fechaHoraDevolucion) {
        this.fechaHoraDevolucion = fechaHoraDevolucion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
    }
}
