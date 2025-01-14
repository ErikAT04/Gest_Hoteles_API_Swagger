package com.erikat.practica_busqueda_hoteles.service;

import com.erikat.practica_busqueda_hoteles.model.Habitacion;
import com.erikat.practica_busqueda_hoteles.repository.HabitacionRepository;
import com.erikat.practica_busqueda_hoteles.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HabitacionService {
    @Autowired
    private HabitacionRepository habitacionRepository;
    public List<Habitacion> findAll() {
        return habitacionRepository.findAll();
    }

    public void borrarHabitacion(Integer id){
        habitacionRepository.deleteById(id);
    }

    public void ocuparHabitacion(Integer id){
        Habitacion hab = habitacionRepository.findById(id).get();
        hab.setOcupada(true);
        habitacionRepository.save(hab);
    }

    public Optional<Habitacion> findByID(int id) {
        return habitacionRepository.findById(id);
    }
}
