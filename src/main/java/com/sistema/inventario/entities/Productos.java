package com.sistema.inventario.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class Productos {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idproducto;
    private String nombre;
    private Integer cantidad = 0;
    private Integer estatus = 1;

    public Productos() {}

    public Productos(Integer idproducto, String nombre, Integer cantidad, Integer estatus) {
        this.idproducto = idproducto;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.estatus = estatus;
    }

    public Integer getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }
}
