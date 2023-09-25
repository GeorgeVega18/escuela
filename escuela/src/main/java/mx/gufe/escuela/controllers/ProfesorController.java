package mx.gufe.escuela.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.gufe.escuela.model.Profesor;
import mx.gufe.escuela.service.ProfesorService;
import mx.gufe.escuela.utils.EscuelaException;

@Controller
@RequestMapping("profesores")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET})
@RestController

public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    // Endpoint para agregar un profesor
    @PostMapping
    public void agregarProfesor(@RequestBody Profesor profesor) {
        profesorService.agregarProfesor(profesor);
    }

    // Endpoint para obtener todos los profesores
    @GetMapping
    public ResponseEntity<List<Profesor>> profesor () {
    		List<Profesor> resp = new ArrayList<>(); 
    		try {
    			resp = ProfesorService.allProfesores();
    			return new ResponseEntity<>(resp, HttpStatus.OK);
    		}catch( EscuelaException ee ) {
    			return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
         }
    }
    // Endpoint para obtener un profesor por su ID
    @GetMapping("/{id}")
    public Profesor obtenerProfesorPorId(@PathVariable int id) {
        return profesorService.obtenerProfesorPorId(id);
    }

    // Endpoint para actualizar la información de un profesor por su ID
    @PutMapping("/{id}")
    public void actualizarProfesor(@PathVariable int id, @RequestBody Profesor profesorActualizado) {
        profesorService.actualizarProfesor(id, profesorActualizado);
    }

    // Endpoint para eliminar un profesor por su ID
    @DeleteMapping("/{id}")
    public void eliminarProfesor(@PathVariable int id) {
        profesorService.eliminarProfesor(id);
    }
}