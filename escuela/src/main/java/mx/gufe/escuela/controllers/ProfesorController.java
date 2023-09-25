package mx.gufe.escuela.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	public ResponseEntity<?> agregarProfesor(@RequestBody Profesor agregarProfesor) {
		Map<String, Object> response = new HashMap<>();
		try {
			profesorService.agregarProfesor(agregarProfesor);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al agregar al Profesor");
			response.put("[EER]  ", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("Mensaje", "El Profesor se agrego exitosamente ");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
    
    // Endpoint para obtener todos los profesores
    @GetMapping
    public ResponseEntity<List<Profesor>> profesor () {
    		List<Profesor> resp = new ArrayList<>(); 
    		try {
    			resp = ProfesorService.allProfesor();
    			return new ResponseEntity<>(resp, HttpStatus.OK);
    		}catch( EscuelaException ee ) {
    			return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
         }
    }
    // Endpoint para obtener un profesor por su ID
    @GetMapping("/findById/{id}")        
        public ResponseEntity<?> buscarProfesorPorId(@PathVariable("id") Integer id) {
    		Profesor profesor = null;
    		Map<String, Object> response = new HashMap<>();
    		try {
    			profesor = profesorService.buscarprofesorPorId(id);
    		} catch (DataAccessException e) {
    			response.put("Mensaje  ", " Error al buscar a la base de datos");
    			response.put("[ERR]  ", e.getMostSpecificCause().getMessage());
    			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    		}
    		if (profesor == null) {
    			response.put("Mensaje  ", " Error al buscar al profesor en la base de datos");
    			response.put("[ERR]  ",
    					" El Profesor con id ".concat(id.toString().concat(" No existe el profesor en la base de datos")));
    			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    		}
    		return new ResponseEntity<Profesor>(profesor, HttpStatus.OK);
    }
    // Endpoint para eliminar un profesor por su ID
    @DeleteMapping("/deleteById/{id}")
        public ResponseEntity<?> eliminarProfesor(@PathVariable("id")Integer id){
			Boolean profesor;
			Map<String, Object> response = new HashMap<>();
			try {
				profesor = profesorService.eliminarProfesorPorId(id);
			}catch (DataAccessException e) {
				response.put("Mensaje ", "NO hay acceso a la base de datos ");
				response.put("[ERROR ] ", e.getMostSpecificCause().getCause());
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}if ( profesor == true ) {
				response.put("mensaje ", "el Profesor con el id ".concat(id.toString().concat(" Profesor eliminado ")));
				return new ResponseEntity<Map<String, Object>> (response, HttpStatus.BAD_REQUEST);
			}else {
				response.put("mensaje ", "el Profesor con el id ".concat(id.toString().concat(" No hay Profesor con ese id en la base datos ")));
				return new ResponseEntity<Map<String, Object>> (response, HttpStatus.BAD_REQUEST);
			}
    }
}