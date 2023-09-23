package mx.gufe.escuela.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

}
