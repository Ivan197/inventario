package com.sistema.inventario.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "historico")
public class Historico {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Long idproducto;
    private String movimiento;
    private Long idusuario;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha", nullable = false)
    private Date fecha;

    public Historico() {}

    public Historico(Long id, Long idproducto, String movimiento, Long idusuario, Date fecha) {
        this.id = id;
        this.idproducto = idproducto;
        this.movimiento = movimiento;
        this.idusuario = idusuario;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Long idproducto) {
        this.idproducto = idproducto;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public Long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Long idusuario) {
        this.idusuario = idusuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
