package com.erikat.practica_busqueda_hoteles.repository;

import com.erikat.practica_busqueda_hoteles.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    @Query("SELECT h from Hotel h where h.localidad = :loc")
    public List<Hotel> getHotelByLocalidad(String loc);
    @Query("SELECT h from Hotel  h where h.categoria = :categoria")
    List<Hotel> getHotelByCategoria(String categoria);
}
