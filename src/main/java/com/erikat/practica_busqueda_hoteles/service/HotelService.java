package com.erikat.practica_busqueda_hoteles.service;

import com.erikat.practica_busqueda_hoteles.dto.HabitacionDTO;
import com.erikat.practica_busqueda_hoteles.dto.HotelDTO;
import com.erikat.practica_busqueda_hoteles.model.Habitacion;
import com.erikat.practica_busqueda_hoteles.model.Hotel;
import com.erikat.practica_busqueda_hoteles.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    public void saveHabitacion(int id, HabitacionDTO habitacionDto) {
        Hotel hotel = hotelRepository.getReferenceById(id);
        Habitacion habitacion = new Habitacion();
        habitacion.setOcupada(habitacionDto.ocupada());
        habitacion.setTamanio(habitacionDto.tamanio());
        habitacion.setIncluyeDesayuno(habitacionDto.incluyeDesayuno());
        habitacion.setPrecioPorNoche(habitacionDto.precioPorNoche());
        habitacion.setUnaPersona(habitacionDto.unaPersona());
        habitacion.setHotel(hotel);

        hotel.getHabitaciones().add(habitacion);

        hotelRepository.save(hotel);

    }

    public List<Hotel> getHotelesByLoc(String localidad) {
        return hotelRepository.getHotelByLocalidad(localidad);
    }

    public List<Hotel> getHotelesByCat(String categoria) {
        return hotelRepository.getHotelByCategoria(categoria);
    }

    public void saveHotel(HotelDTO hotelDTO) {
        Hotel hotel = new Hotel();
        hotel.setNombre(hotelDTO.nombre());
        hotel.setCategoria(hotelDTO.categoria());
        hotel.setDescripcion(hotelDTO.descripcion());
        hotel.setLocalidad(hotelDTO.localidad());
        hotel.setTienePiscina(hotelDTO.tienePiscina());

        hotelRepository.save(hotel);
    }

    public List<Hotel> getAllHoteles() {
        return hotelRepository.findAll();
    }

    public Hotel getHotelById(int idHotel) {
        return hotelRepository.findById(idHotel).get();
    }

    public void updateHotel(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    public List<Habitacion> getHabitacionesByTamHotel(int id, int minTam, int maxTam) {
        Hotel hotel = hotelRepository.findById(id).get();
        List<Habitacion> habitacionesFiltradas = new ArrayList<>();
        for (Habitacion h : hotel.getHabitaciones()){
            if (h.getTamanio()>=minTam && h.getTamanio()<=maxTam){
                habitacionesFiltradas.add(h);
            }
        }
        return habitacionesFiltradas;
    }

    public List<Habitacion> getHabitacionesByPriceHotel(int id, int minprice, int maxprice) {
        Hotel hotel = hotelRepository.findById(id).get();
        List<Habitacion> habitacionesFiltradas = new ArrayList<>();
        for (Habitacion h : hotel.getHabitaciones()){
            if (h.getPrecioPorNoche()>=minprice && h.getPrecioPorNoche()<=maxprice){
                habitacionesFiltradas.add(h);
            }
        }
        return habitacionesFiltradas;
    }
}
