package mx.gufe.escuela.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.gufe.escuela.dto.BoletaDTO;
import mx.gufe.escuela.dto.CalificacionDTO;
import mx.gufe.escuela.service.CalificacionesService;
import mx.gufe.escuela.utils.EscuelaException;
import mx.gufe.escuela.utils.OveralResponse;

@RestController
@RequestMapping("calificaciones")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CalificacionesController {
	
	@Autowired
	private CalificacionesService calificacionesService;

	@GetMapping
	public ResponseEntity<BoletaDTO> getBoleta(@RequestParam(value = "idAlumno") Integer idAlumno){
		try {
			BoletaDTO resp = calificacionesService.obtenerBoleta(idAlumno);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		}catch( EscuelaException ee ) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping
	public ResponseEntity<OveralResponse> insert(@RequestBody CalificacionDTO calificacion){
		try {
			OveralResponse resp = calificacionesService.addCalificacion(calificacion);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		}catch( EscuelaException ee ) {
			OveralResponse respError = new OveralResponse("Error", ee.getMessage());
			return new ResponseEntity<>(respError, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping
	public ResponseEntity<OveralResponse> update(@RequestBody CalificacionDTO calificacionDto){
		try {
			OveralResponse resp = calificacionesService.updCalificacion(calificacionDto);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		}catch( EscuelaException ee ) {
			OveralResponse respError = new OveralResponse("Error", ee.getMessage());
			return new ResponseEntity<>(respError, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping
	public ResponseEntity<OveralResponse> delete(@RequestParam(value = "idCalificacion") Integer idCalificacion){
		try {
			OveralResponse resp = calificacionesService.delCalificacion(idCalificacion);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		}catch( EscuelaException ee ) {
			OveralResponse respError = new OveralResponse("Error", ee.getMessage());
			return new ResponseEntity<>(respError, HttpStatus.BAD_REQUEST);
		}
	}
 
}
