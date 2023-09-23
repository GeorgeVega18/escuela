package mx.gufe.escuela.serviceimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gufe.escuela.model.Alumno;
import mx.gufe.escuela.repositories.AlumnosRepository;
import mx.gufe.escuela.service.AlumnosService;
import mx.gufe.escuela.utils.EscuelaException;

@Service
public class AlumnosServiceImpl implements AlumnosService {
	
	final static Logger logger = Logger.getLogger(AlumnosServiceImpl.class.getName()); 

	@Autowired
	private AlumnosRepository alumnosRepository;
	
	@Override
	public List<Alumno> allAlumnos() throws EscuelaException {
		try {
			List<Alumno> lstResp = alumnosRepository.findAll();
			return lstResp;
		}catch(Exception e) {
			logger.error("Error al consultar alumnos: " + e.getMessage());
			throw new EscuelaException("Error al consultar alumnos.", e.getMessage());
		}
	}

	@Override
	public Alumno alumnos(Integer idAlumno) throws EscuelaException {
		try {
			Alumno resp = alumnosRepository.getReferenceById(idAlumno);
			return resp;
		}catch(Exception e) {
			logger.error("Error al consultar alumnos: " + e.getMessage());
			throw new EscuelaException("Error al consultar alumnos.", e.getMessage());
		}
	}

}
