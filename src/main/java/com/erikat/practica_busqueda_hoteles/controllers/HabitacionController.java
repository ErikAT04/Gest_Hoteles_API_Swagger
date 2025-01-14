package com.erikat.practica_busqueda_hoteles.controllers;

import com.erikat.practica_busqueda_hoteles.model.Habitacion;
import com.erikat.practica_busqueda_hoteles.model.Hotel;
import com.erikat.practica_busqueda_hoteles.service.HabitacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@Tag(name = "Habitaciones", description = "Controles de habitaciones")
@RequestMapping("/api/habitaciones")
public class HabitacionController {
    @Autowired
    private HabitacionService habitacionService;

    ///Obtención de todas las habitaciones
    @GetMapping("/")
    @Operation(summary = "Obtiene la lista de todas las habitaciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista todas las habitaciones"),
    })
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(habitacionService.findAll(), HttpStatus.OK);
    }

    ///f. Modificar una habitación para indicar que está ocupada
    @PutMapping("habitacion/{id}/ocupar")
    @Operation(summary = "Permite marcar como ocupada una habitación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Habitación ocupada"),
            @ApiResponse(responseCode = "400", description = "Id de la habitación incorrecta"),
            @ApiResponse(responseCode = "304", description = "La habitación ya estaba ocupada")
    })
    public ResponseEntity<?> ocuparHabitacion(@PathVariable("id") int idHabitacion){
        try{
            Habitacion habitacion = habitacionService.findByID(idHabitacion).get();
            if(!habitacion.getOcupada()){
                habitacionService.ocuparHabitacion(idHabitacion);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>("La habitación ya aparecía como ocupada",HttpStatus.NOT_MODIFIED);
            }

        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error de cambio de estado", e);
        }
    }
}
