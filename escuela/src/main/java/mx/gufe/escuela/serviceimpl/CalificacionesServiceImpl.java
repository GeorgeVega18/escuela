package mx.gufe.escuela.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gufe.escuela.dto.BoletaDTO;
import mx.gufe.escuela.dto.BoletaRegistroDTO;
import mx.gufe.escuela.dto.CalificacionDTO;
import mx.gufe.escuela.dto.PromedioDTO;
import mx.gufe.escuela.model.Alumno;
import mx.gufe.escuela.model.Calificacion;
import mx.gufe.escuela.model.Materia;
import mx.gufe.escuela.repositories.CalificacionesRepository;
import mx.gufe.escuela.service.CalificacionesService;
import mx.gufe.escuela.utils.EscuelaException;
import mx.gufe.escuela.utils.OveralResponse;

@Service
public class CalificacionesServiceImpl implements CalificacionesService {
	
	final static Logger logger = Logger.getLogger(CalificacionesServiceImpl.class.getName());
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Autowired
	private CalificacionesRepository calificacionesRepository;

	@Override
	public BoletaDTO obtenerBoleta(Integer idAlumno) throws EscuelaException {
		
		List<Calificacion> lstCalificaciones = calificacionesOfAlumno(idAlumno);
		List<BoletaRegistroDTO> registros = new ArrayList<>();
		
		if(lstCalificaciones.isEmpty()) {
			return new BoletaDTO();
		}
		
		for(Calificacion cal : lstCalificaciones) {
			BoletaRegistroDTO reg = calificacionToBoletaRegistroDTO(cal);
			if (reg != null) registros.add(reg);
		}
		
		BoletaDTO boleta = new BoletaDTO();
		
		boleta.setCalificaciones(registros);
		boleta.setPromedio(calculaPromedio(lstCalificaciones));
		
		
		return  boleta;
	}

	@Override
	public OveralResponse addCalificacion(CalificacionDTO calificacionDto) throws EscuelaException {
		
		if(calificacionDto.getIdCalificacion() != null) {
			throw new EscuelaException("Ya existe una calificación con ese id","");
		}
		
		Alumno alumno = new Alumno();
		alumno.setId(calificacionDto.getIdAlumno());
		
		Materia materia = new Materia();
		materia.setId(calificacionDto.getIdMateria());
		
		Calificacion calificacion = new Calificacion();
		calificacion.setAlumno(alumno);
		calificacion.setMateria(materia);
		calificacion.setFechaRegistro(new Date());
		calificacion.setCalificacion(calificacionDto.getCalificacion());
		try {
			calificacionesRepository.save(calificacion);
			return new OveralResponse("Ok","calificacion registrada");
		}catch(Exception e) {
			throw new EscuelaException("calificacion no registrada",e.getMessage());
		}
	}

	@Override
	public OveralResponse updCalificacion(CalificacionDTO calificacionDto) throws EscuelaException {
		if(calificacionDto.getIdCalificacion() == null) {
			throw new EscuelaException("El id es requerido","");
		}
		
		Calificacion calificacionPrevia = calificacionesRepository.getReferenceById(calificacionDto.getIdCalificacion());
		if(calificacionPrevia == null){
			throw new EscuelaException("El id no existe","");
		}
		
		Alumno alumno = new Alumno();
		alumno.setId(calificacionDto.getIdAlumno());
		
		Materia materia = new Materia();
		materia.setId(calificacionDto.getIdMateria());
		
		Calificacion calificacion = new Calificacion(
				calificacionDto.getIdCalificacion(),
				materia,
				alumno,
				calificacionDto.getCalificacion(),
				new Date()
				);
		try {
			calificacionesRepository.save(calificacion);
			return new OveralResponse("Ok","calificacion actualizada");
		}catch(Exception e) {
			throw new EscuelaException("calificacion no actualizada",e.getMessage());
		}
	}

	@Override
	public OveralResponse delCalificacion(Integer idCalificacion) throws EscuelaException {
		try {
			calificacionesRepository.deleteById(idCalificacion);;
			return new OveralResponse("Ok","calificacion eliminada");
		}catch(Exception e) {
			throw new EscuelaException("calificacion no eliminada",e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	private List<Calificacion> calificacionesOfAlumno(Integer idAlumno) {
		List<Calificacion> lstResp = new ArrayList<>();
		Query query = entityManager.createQuery("SELECT c FROM Calificacion c WHERE c.alumno.id = :idAlumno ");
		query.setParameter("idAlumno", idAlumno);
		try {
			lstResp = query.getResultList();
		}catch(Exception e) {
			logger.error("Error al consultar calificaicones del alumno");
		}
		return lstResp;
	}
	


	private BoletaRegistroDTO calificacionToBoletaRegistroDTO(Calificacion cal) {
		Alumno alumno = cal.getAlumno();
		Materia materia = cal.getMateria();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String fechaRegistro = dateFormat.format(cal.getFechaRegistro());
		
		try {
		BoletaRegistroDTO registro = new BoletaRegistroDTO(
				alumno.getId(),
				cal.getId(),
				alumno.getName(),
				alumno.getApPaterno(),
				materia.getName(),
				cal.getCalificacion(),
				fechaRegistro);
		return registro;
		}catch(Exception e) {
			logger.error("Error al convertir calificación: " + e.getMessage());
			return null;
		}
	}
	

	private PromedioDTO calculaPromedio(List<Calificacion> lstCalificaciones) {
		
		Double suma = 0d;
		
		for(Calificacion cal : lstCalificaciones) {
			suma += cal.getCalificacion();
		}
		Double promedio = 0d;
		if(!lstCalificaciones.isEmpty())
			promedio = suma/lstCalificaciones.size();
		
		return new PromedioDTO(promedio);
	}
}
