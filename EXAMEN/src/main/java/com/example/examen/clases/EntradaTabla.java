package com.example.examen.clases;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class EntradaTabla {
    private String matricula;
    private String modeloCoche;
    private Cliente cliente;
    private String tipoTarifa;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private double costeTotal;

    public EntradaTabla(String matricula, String modeloCoche, Cliente cliente, String tipoTarifa,
                        LocalDate fechaEntrada, LocalDate fechaSalida, double costeTotal) {
        this.matricula = matricula;
        this.modeloCoche = modeloCoche;
        this.cliente = cliente;
        this.tipoTarifa = tipoTarifa;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.costeTotal = costeTotal;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getModeloCoche() {
        return modeloCoche;
    }

    public void setModeloCoche(String modeloCoche) {
        this.modeloCoche = modeloCoche;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getTipoTarifa() {
        return tipoTarifa;
    }

    public void setTipoTarifa(String tipoTarifa) {
        this.tipoTarifa = tipoTarifa;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public double getCosteTotal() {
        return costeTotal;
    }

    public void setCosteTotal(double costeTotal) {
        this.costeTotal = costeTotal;
    }


    public void calcularCosteTotal() {
        double precioDiario;

        switch (tipoTarifa) {
            case "Oferta":
                precioDiario = 6.0;
                break;
            case "Larga duración":
                precioDiario = 2.0;
                break;
            default:
                precioDiario = 8.0; // Tarifa estándar
        }

        // Calcular la diferencia en días
        long dias = ChronoUnit.DAYS.between(fechaEntrada, fechaSalida) + 1; // Sumar 1 para incluir el día de entrada

        costeTotal = precioDiario * dias;
    }
}
