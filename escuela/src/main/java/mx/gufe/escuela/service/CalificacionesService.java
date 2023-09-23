package mx.gufe.escuela.service;

import mx.gufe.escuela.dto.BoletaDTO;
import mx.gufe.escuela.dto.CalificacionDTO;
import mx.gufe.escuela.utils.EscuelaException;
import mx.gufe.escuela.utils.OveralResponse;

public interface CalificacionesService {
	BoletaDTO obtenerBoleta(Integer idAlumno) throws EscuelaException;
	OveralResponse addCalificacion(CalificacionDTO calificacionDto) throws EscuelaException;
	OveralResponse updCalificacion(CalificacionDTO calificacionDto) throws EscuelaException;
	OveralResponse delCalificacion(Integer idCalificacion) throws EscuelaException;
}
