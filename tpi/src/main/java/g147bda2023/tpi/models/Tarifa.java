package g147bda2023.tpi.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Tarifas")
public class Tarifa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private double montoFijoAlquiler;

    @Column(name = "MONTO_MINUTO_FRACCION")
    private double montoMinutoFraccion;

    @Column(name = "MONTO_HORA")
    private double montoHora;

    @Column(name = "MONTO_KM")
    private double montoKm;

    public Tarifa() {}

    public Tarifa(Long id, int tipoTarifa, char definicion, int diaSemana, int diaMes, int mes, int anio, double montoFijoAlquiler, double montoMinutoFraccion, double montoHora, double montoKm) {
        this.id = id;
        this.tipoTarifa = tipoTarifa;
        this.definicion = definicion;
        this.diaSemana = diaSemana;
        this.diaMes = diaMes;
        this.mes = mes;
        this.anio = anio;
        this.montoFijoAlquiler = montoFijoAlquiler;
        this.montoMinutoFraccion = montoMinutoFraccion;
        this.montoHora = montoHora;
        this.montoKm = montoKm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTipoTarifa() {
        return tipoTarifa;
    }

    public void setTipoTarifa(int tipoTarifa) {
        this.tipoTarifa = tipoTarifa;
    }

    public char getDefinicion() {
        return definicion;
    }

    public void setDefinicion(char definicion) {
        this.definicion = definicion;
    }

    public int getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(int diaSemana) {
        this.diaSemana = diaSemana;
    }

    public int getDiaMes() {
        return diaMes;
    }

    public void setDiaMes(int diaMes) {
        this.diaMes = diaMes;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public double getMontoFijoAlquiler() {
        return montoFijoAlquiler;
    }

    public void setMontoFijoAlquiler(double montoFijoAlquiler) {
        this.montoFijoAlquiler = montoFijoAlquiler;
    }

    public double getMontoMinutoFraccion() {
        return montoMinutoFraccion;
    }

    public void setMontoMinutoFraccion(double montoMinutoFraccion) {
        this.montoMinutoFraccion = montoMinutoFraccion;
    }

    public double getMontoHora() {
        return montoHora;
    }

    public void setMontoHora(double montoHora) {
        this.montoHora = montoHora;
    }

    public double getMontoKm() {
        return montoKm;
    }

    public void setMontoKm(double montoKm) {
        this.montoKm = montoKm;
    }
}
