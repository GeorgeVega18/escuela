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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mx.gufe.escuela.model.Materia;
import mx.gufe.escuela.service.MateriasService;
import mx.gufe.escuela.utils.EscuelaException;

@Controller
@RequestMapping("materias")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET})
public class MateriasController {
	
	@Autowired
	private MateriasService materiasService;
	
	@GetMapping
	public ResponseEntity<List<Materia>> calificaciones () {
		List<Materia> resp = new ArrayList<>();
		try {
			resp = materiasService.allMaterias();
			return new ResponseEntity<>(resp, HttpStatus.OK);
		}catch( EscuelaException ee ) {
			return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
		}
	}
	@PostMapping("/create")
	public ResponseEntity<?> crearMateria(@RequestBody Materia crearMateria) {
		Map<String, Object> response = new HashMap<>();
		try {
			materiasService.crearMateria(crearMateria);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al crear la materia");
			response.put("[EER]  ", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("Mensaje", "La materia creada de manera Exitosa");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable("id") Integer id) {
		Materia materia = null;
		Map<String, Object> response = new HashMap<>();
		try {
			materia = materiasService.buscarMateriaPorId(id);
		} catch (DataAccessException e) {
			response.put("Mensaje  ", " Error al acceder a la base de datos");
			response.put("[ERR]  ", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (materia == null) {
			response.put("Mensaje  ", " Error al encontrar la materia en la base de datos");
			response.put("[ERR]  ",
					" La materia con id ".concat(id.toString().concat(" No existe la materia en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Materia>(materia, HttpStatus.OK);
	}
	@GetMapping("/findByName/{name}")
	public ResponseEntity<?> buscarPorNombre(@PathVariable("name") String name) {
		Materia materia = null;
		Map<String, Object> response = new HashMap<>();
		try {
			materia = materiasService.buscarMateriaPorNombre(name);
		} catch (DataAccessException e) {
			response.put("Mensaje  ", " Error al acceder a la base de datos");
			response.put("[ERR]  ", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (materia == null) {
			response.put("Mensaje  ", " Error al Encontrar la materia ");
			response.put("[ERR]  ",
					" La materia con nombre ".concat(name.toString().concat(" no existe la materia")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Materia>(materia, HttpStatus.OK);
	}



}
