package com.erikat.practica_busqueda_hoteles.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "habitacion")
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "tamanio")
    private int tamanio;

    @Column(name = "una_persona")
    private Boolean unaPersona;

    @Column(name = "precio_noche")
    private double precioPorNoche;

    @Column(name = "incluye_desayuno")
    private Boolean incluyeDesayuno;

    @Column(name = "ocupada")
    private Boolean ocupada;

    @ManyToOne
    @JoinColumn(name = "hotel", referencedColumnName = "id")
    @JsonBackReference
    private Hotel hotel;

    public Habitacion() {
    }

    public Habitacion(int id, int tamanio, Boolean unaPersona, double precioPorNoche, Boolean incluyeDesayuno, Boolean ocupada, Hotel hotel) {
        this.id = id;
        this.tamanio = tamanio;
        this.unaPersona = unaPersona;
        this.precioPorNoche = precioPorNoche;
        this.incluyeDesayuno = incluyeDesayuno;
        this.ocupada = ocupada;
        this.hotel = hotel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public Boolean getUnaPersona() {
        return unaPersona;
    }

    public void setUnaPersona(Boolean unaPersona) {
        this.unaPersona = unaPersona;
    }

    public double getPrecioPorNoche() {
        return precioPorNoche;
    }

    public void setPrecioPorNoche(double precioPorNoche) {
        this.precioPorNoche = precioPorNoche;
    }

    public Boolean getIncluyeDesayuno() {
        return incluyeDesayuno;
    }

    public void setIncluyeDesayuno(Boolean incluyeDesayuno) {
        this.incluyeDesayuno = incluyeDesayuno;
    }

    public Boolean getOcupada() {
        return ocupada;
    }

    public void setOcupada(Boolean ocupada) {
        this.ocupada = ocupada;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "id=" + id +
                ", tamanio=" + tamanio +
                ", unaPersona=" + unaPersona +
                ", precioPorNoche=" + precioPorNoche +
                ", incluyeDesayuno=" + incluyeDesayuno +
                ", ocupada=" + ocupada +
                ", hotel=" + hotel +
                '}';
    }
}
