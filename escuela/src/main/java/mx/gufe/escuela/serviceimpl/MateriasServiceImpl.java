package mx.gufe.escuela.serviceimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gufe.escuela.model.Materia;
import mx.gufe.escuela.repositories.MateriaRepository;
import mx.gufe.escuela.service.MateriasService;
import mx.gufe.escuela.utils.EscuelaException;

@Service
public class MateriasServiceImpl implements MateriasService {

	final static Logger logger = Logger.getLogger(MateriasServiceImpl.class.getName());
	
	@Autowired
	private MateriaRepository materiaRepository;
	
	@Override
	public List<Materia> allMaterias() throws EscuelaException{
		try {
			List<Materia> lstResp = materiaRepository.findAll();
			return lstResp;
		}catch(Exception e) {
			logger.error("Error al consultar materias: " + e.getMessage());
			throw new EscuelaException("Error al consultar materias.", e.getMessage());
		}
	}

	@Override
	public Materia materia(Integer idMateria) throws EscuelaException {
		try {
			Materia resp = materiaRepository.getReferenceById(idMateria);
			return resp;
		}catch(Exception e) {
			logger.error("Error al consultar materia: " + e.getMessage());
			throw new EscuelaException("Error al consultar materia.", e.getMessage());
		}
	}

}
