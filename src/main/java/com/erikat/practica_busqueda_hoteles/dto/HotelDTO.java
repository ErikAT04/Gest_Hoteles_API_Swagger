package com.erikat.practica_busqueda_hoteles.dto;

import jakarta.persistence.Column;

public record HotelDTO(String nombre, String descripcion, String categoria, Boolean tienePiscina, String localidad){
}
