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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mx.gufe.escuela.model.Alumno;
import mx.gufe.escuela.service.AlumnosService;
import mx.gufe.escuela.utils.EscuelaException;

@Controller
@RequestMapping("alumnos")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET})
public class AlumnosController {
	@Autowired
	private AlumnosService alumnosService;
	
	@GetMapping
	public ResponseEntity<List<Alumno>> alumnos () {
		List<Alumno> resp = new ArrayList<>(); 
		try {
			resp = alumnosService.allAlumnos();
			return new ResponseEntity<>(resp, HttpStatus.OK);
		}catch( EscuelaException ee ) {
			return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
		}
	}		
		
		@DeleteMapping("/deleteById/{id}")
		public ResponseEntity<?> eliminarAlumno(@PathVariable("id")Integer id){
			Boolean alumno;
			Map<String, Object> response = new HashMap<>();
			try {
				alumno = alumnosService.eliminarAlumnoPorId(id);
			}catch (DataAccessException e) {
				response.put("Mensaje ", "NO hay acceso a la base de datos ");
				response.put("[ERROR ] ", e.getMostSpecificCause().getCause());
				response.put("mensaje ", "el Alumno con el id ".concat(id.toString().concat(" eliminado ")));
				response.put("mensaje ", "el Alumno con el id ".concat(id.toString().concat(" sin registro en la base datos ")));
				
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			if ( alumno == true ) {
				
				return new ResponseEntity<Map<String, Object>> (response, HttpStatus.BAD_REQUEST);				
				return new ResponseEntity<Map<String, Object>> (response, HttpStatus.BAD_REQUEST);
			}

	}
}


