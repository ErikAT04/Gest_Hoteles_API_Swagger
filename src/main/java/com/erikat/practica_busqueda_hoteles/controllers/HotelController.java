package com.erikat.practica_busqueda_hoteles.controllers;

import com.erikat.practica_busqueda_hoteles.dto.HabitacionDTO;
import com.erikat.practica_busqueda_hoteles.dto.HotelDTO;
import com.erikat.practica_busqueda_hoteles.model.Habitacion;
import com.erikat.practica_busqueda_hoteles.model.Hotel;
import com.erikat.practica_busqueda_hoteles.service.HabitacionService;
import com.erikat.practica_busqueda_hoteles.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api/hoteles")
@Tag(name = "Hoteles", description = "Controles de hoteles")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    @Autowired
    private HabitacionService habitacionService;

    @GetMapping("/")
    @Operation(summary = "Obtiene una lista de hoteles")
    public ResponseEntity<?> getHoteles(){
        try {
            return new ResponseEntity<>(this.hotelService.getAllHoteles(), HttpStatus.ACCEPTED);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error de búsqueda", e);
        }
    }

    ///a. Buscar un hotel por localidad
    @GetMapping("hotel/localidad/{loc}")
    @Operation(summary = "Obtiene una lista de hoteles con la localidad puesta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de hoteles con la localidad dada")
    })
    public ResponseEntity<?> getHotelesInLoc(@PathVariable("loc") String localizacion) {
        return new ResponseEntity<>(this.hotelService.getHotelesByLoc(localizacion), HttpStatus.OK);
    }

    ///a. Buscar un hotel por categoría
    @GetMapping("hotel/categoria/{cat}")
    @Operation(summary = "Obtiene una lista de hoteles con la categoría puesta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de hoteles con la categoría dada")
    })
    public ResponseEntity<?> getHotelesByCat(@PathVariable("cat") String categoria) {
        try {
            return new ResponseEntity<>(this.hotelService.getHotelesByCat(categoria), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error de búsqueda", e);
        }
    }

    ///b. Búsqueda de habitaciones de un hotel por tamaño (En un rango descrito por el usuario)
    @GetMapping("hotel/{id}/habitaciones/tamEntre/{mintam}:{maxtam}")
    @Operation(summary = "Obtiene una lista de habitaciones de un hotel cuyo tamaño se encuentre entre dos valores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de habitaciones de un hotel dado cuyo tamaño se encuentra entre dos valores"),
            @ApiResponse(responseCode = "403", description = "El campo de tamaño mínimo es mayor que el del tamaño máximo"),
            @ApiResponse(responseCode = "400", description = "El id del hotel no es correcto")
    })
    public ResponseEntity<?> getHabitacionesByTamHotel(@PathVariable("id") int id, @PathVariable("mintam") int minTam, @PathVariable("maxtam") int maxTam){
        try{
            if (minTam<=maxTam) {
                return new ResponseEntity<>(hotelService.getHabitacionesByTamHotel(id, minTam, maxTam), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("El tamaño minimo debe ser menor o igual al maximo", HttpStatus.FORBIDDEN);
            }
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error de obtención de datos", e);
        }
    }

    ///b. Búsqueda de habitaciones de n hotel por precio (En un rango descrito por el usuario)
    @GetMapping("hotel/{id}/habitaciones/precioEntre/{minprice}:{maxprice}")
    @Operation(summary = "Obtiene una lista de habitaciones de un hotel cuyo precio se encuentre entre dos valores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de habitaciones en el hotel deseado cuyo precio se encuentra entre los propuestos"),
            @ApiResponse(responseCode = "403", description = "El campo de precio mínimo es mayor que el del precio máximo"),
            @ApiResponse(responseCode = "404", description = "El id del hotel no es correcto")
    })
    public ResponseEntity<?> getHabitacionesByPriceHotel(@PathVariable("id") int id, @PathVariable("minprice") int minprice, @PathVariable("maxprice") int maxprice){
        try{
            if(minprice<=maxprice) {
                return new ResponseEntity<>(hotelService.getHabitacionesByPriceHotel(id, minprice, maxprice), HttpStatus.OK);
            }else {
                return new ResponseEntity<>("El precio minimo debe ser menor o igual que el máximo", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error de obtención de datos", e);
        }
    }

    ///c. Registrar un nuevo hotel
    @PostMapping("registrarHotel")
    @Operation(summary = "Registra un hotel pasado por el usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de hoteles con la categoría dada"),
            @ApiResponse(responseCode = "403", description = "El campo de tamaño mínimo es mayor que el del tamaño máximo"),
            @ApiResponse(responseCode = "404", description = "El id del hotel no es correcto")
    })
    public ResponseEntity<?> saveHotel(@RequestBody HotelDTO hotelDTO) {
        try {
            hotelService.saveHotel(hotelDTO);
            return new ResponseEntity<>(hotelDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error de inserción", e);
        }
    }

    ///d. Registrar una nueva habitación a un hotel
    @PostMapping("hotel/{id}/guardarHabitacion")
    @Operation(summary = "Registra una habitación dentro de un hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Habitación guardada"),
            @ApiResponse(responseCode = "400", description = "Sintaxis de la habitación o id del hotel incorrectos")

    })
    public ResponseEntity<?> saveHabitacion(@PathVariable("id") int id, @RequestBody HabitacionDTO habitacionDto) {
        try {
            this.hotelService.saveHabitacion(id, habitacionDto);
            return new ResponseEntity<>(habitacionDto, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error de inserción", e);
        }
    }

    ///e. Eliminar una habitación determinada de un hotel
    @DeleteMapping("hotel/{idHotel}/borrarHabitacion/{idHab}")
    @Operation(summary = "Borra una habitación de un hotel específico con el identificador de ambas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Habitación borrada"),
            @ApiResponse(responseCode = "400", description = "Id de la habitación o id del hotel incorrectos"),

    })
    public ResponseEntity<?> borrarHabitacion(@PathVariable("idHotel") int idHotel, @PathVariable("idHab") int idHabitacion){
        try{
            boolean encontrado = false;
            Hotel hotel = this.hotelService.getHotelById(idHotel);
            Iterator<Habitacion> iterator = hotel.getHabitaciones().iterator();
            while(iterator.hasNext()) {
                Habitacion h = iterator.next();
                if (h.getId() == idHabitacion) {
                    encontrado = true;
                    iterator.remove();
                    habitacionService.borrarHabitacion(h.getId());
                }
            }
            if(!encontrado){
                return new ResponseEntity<String>("No se ha encontrado la habitación en el hotel", HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error de borrado", e);
        }
    }
}
