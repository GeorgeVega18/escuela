package mx.gufe.escuela.serviceimpl;

import java.util.List;
import java.util.Optional;

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

	private Integer idAlumno;
	
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

	@Override
	public Alumno crearAlumno(Alumno crearAlumno) {
		Alumno crear = new Alumno();
		crear.getId();
		crear.setName(crearAlumno.getName());
		crear.setApPaterno(crearAlumno.getApPaterno());
		crear.setApMaterno(crearAlumno.getApMaterno());
		crear.setActivo(crearAlumno.getActivo());
		crear.setEstado(crearAlumno.getEstado());		

		alumnosRepository.save(crear);
		
		return crearAlumno;
	}


	@Override
	public Alumno buscaPorId(Integer idAlumno) {
		return alumnosRepository.findById(idAlumno).orElse(null);
	}

	@Override
	public Alumno buscarAlumnoPorNombre(String nombre) {
		Optional<Alumno> alumnoOptional = alumnosRepository.buscarPorNombre(nombre);
		if (alumnoOptional.isPresent()) {
			return alumnoOptional.get();
		}else {
			return null;
		}
	}

	@Override
	public Alumno eliminadoLogico(Integer id) {
		Alumno alumno = buscaPorId(id);
		if(alumno != null ) {
			alumno.setEstado(2);
			return alumnosRepository.save(alumno);
		}else {
		return null;
	  }
	}

	@Override
	public Boolean eliminarAlumnoPorId(Integer id) {
		if(buscaPorId(idAlumno) != null) {
			alumnosRepository.deleteById(idAlumno);
			return true;
		}else {
			return false;
		}

	}

}
