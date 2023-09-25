package mx.gufe.escuela.serviceimpl;

import java.util.List;

import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gufe.escuela.model.Alumno;
import mx.gufe.escuela.model.Profesor;
import mx.gufe.escuela.repositories.ProfesorRepository;
import mx.gufe.escuela.service.ProfesorService;
import mx.gufe.escuela.utils.EscuelaException;

@Service
public class ProfesorServiceImpl implements ProfesorService {
	
	final static Logger logger = Logger.getLogger(ProfesorServiceImpl.class.getName()); 

	@Autowired
	private ProfesorRepository profesorRepository;

	private Category crearProfesor;
	
	public List<Profesor> allProfesor() throws EscuelaException {
		try {
			List<Profesor> lstResp = profesorRepository.findAll();
			return lstResp;
		}catch(Exception e) {
			logger.error("Error al consultar Profesor: " + e.getMessage());
			throw new EscuelaException("Error al consultar profesor.", e.getMessage());
		}
	}
	
	@Override
	public Profesor agregarProfesor(Profesor agregarProfesor) {
		Profesor crear = new Profesor();
		crear.getId();
		crear.setName(crearProfesor.getName());
		crear.setMateria(crearProfesor.getMateria());	
		ProfesorRepository.save(crear);
		
		return crearProfesor;
	}

	public Profesor profesor(Integer idprofesor) throws EscuelaException {
		try {
			Profesor resp = profesorRepository.getReferenceById(idprofesor);
			return resp;
		}catch(Exception e) {
			logger.error("Error al consultar profesores: " + e.getMessage());
			throw new EscuelaException("Error al consultar profesor.", e.getMessage());
		}
	}

	@Override
	public Profesor agregarProfesor(Profesor agregarProfesor) {
		return null;
	}

	@Override
	public Profesor buscaPorId(Integer idProfesor) {
		return null;
	}

	@Override
	public Boolean eliminarProfesorPorId(Integer id) {
		return null;
	}

}
