package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "gastos")
public class Gastos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gasto")
    private int id_gasto;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "importe")
    private BigDecimal importe;

    @Column(name = "id_categoria_transaccion")
    private int id_categoria_transaccion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuarios usuario;

    public Gastos() {
    }

    public Gastos(Date fecha, BigDecimal importe, int id_categoria_transaccion, Usuarios usuario) {
        this.fecha = fecha;
        this.importe = importe;
        this.id_categoria_transaccion = id_categoria_transaccion;
        this.usuario = usuario;
    }

    public int getId_gasto() {
        return id_gasto;
    }

    public void setId_gasto(int id_gasto) {
        this.id_gasto = id_gasto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public int getId_categoria_transaccion() {
        return id_categoria_transaccion;
    }

    public void setId_categoria_transaccion(int id_categoria_transaccion) {
        this.id_categoria_transaccion = id_categoria_transaccion;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID de Gasto: ").append(id_gasto).append("\n");
        sb.append("Fecha: ").append(fecha).append("\n");
        sb.append("Importe: ").append(importe).append("\n");
        sb.append("ID de Categoría de Transacción: ").append(id_categoria_transaccion).append("\n");
        sb.append("Usuario: ").append(usuario != null ? usuario.getNombre() : "N/A").append("\n");

        return sb.toString();
    }
}
