package ar.edu.unlam.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Alumno {

	private Integer documento;
	private String nombre;
	private String apellido;
	private List<TrabajoPractico> trabajosPracticos;
	private Set<Inscripcion> inscripciones;

	public Alumno(Integer documento, String nombre, String apellido) {
		this.documento = documento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.trabajosPracticos = new ArrayList<>();
		this.inscripciones = new HashSet<>();
	}

	public Boolean anadirInscripcion(Inscripcion inscripcion) {
		return this.inscripciones.add(inscripcion);
	}

	public List<TrabajoPractico> getTrabajosPracticos() {
		return this.trabajosPracticos;
	}

	public void recibirTrabajoPractico(TrabajoPractico trabajoPractico) {
		this.trabajosPracticos.add(trabajoPractico);
	}

	public Integer getDni() {
		return this.documento;
	}

	public Inscripcion obtenerInscripcionPorCurso(Curso curso) {
		for (Inscripcion inscripcion : inscripciones) {
			if (inscripcion.getCurso().equals(curso)) {
				return inscripcion;
			}
		}

		return null;
	}

	public boolean entregarTrabajoPractico(TrabajoPractico trabajoPractico, LocalDate fechaEntrega) {
		if (this.trabajosPracticos.contains(trabajoPractico) && trabajoPractico.puedeSerEntregado(fechaEntrega)) {
			EntregaTrabajoPractico entregaTrabajoPractico = new EntregaTrabajoPractico(this, fechaEntrega, trabajoPractico.getCursoAlQuePertenece());

			trabajoPractico.getCursoAlQuePertenece().getProfesor().recibirEntregaTrabajoPractico(entregaTrabajoPractico);
			this.trabajosPracticos.remove(trabajoPractico);
			return true;
		}

		return false;
	}
}