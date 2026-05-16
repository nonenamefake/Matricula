package Modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Pago {

    private Integer idPago;
    private Matricula matricula;
    private BigDecimal monto;
    private LocalDateTime fechaPago;
    private String numeroOperacion;

    public Pago() {
    }

    public Pago(Matricula matricula, BigDecimal monto, String numeroOperacion) {
        this.matricula = matricula;
        this.monto = monto;
        this.numeroOperacion = numeroOperacion;
        this.fechaPago = LocalDateTime.now();
    }

    public Pago(Integer idPago, Matricula matricula, BigDecimal monto, LocalDateTime fechaPago, String numeroOperacion) {
        this.idPago = idPago;
        this.matricula = matricula;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.numeroOperacion = numeroOperacion;
    }

    public Integer getIdPago() {
        return idPago;
    }

    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getNumeroOperacion() {
        return numeroOperacion;
    }

    public void setNumeroOperacion(String numeroOperacion) {
        this.numeroOperacion = numeroOperacion;
    }

    @Override
    public String toString() {
        return "Pago #" + idPago + " - S/" + monto + " (" + matricula.getAlumno().getNombre() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pago)) return false;
        Pago pago = (Pago) o;
        return Objects.equals(idPago, pago.idPago);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPago);
    }
}
