package ar.edu.unlam.dominio;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class Curso {
	
	private Integer identificadorCurso;
	private String nombre;
	private Integer capacidad;
	private Set<Alumno> alumnos;
	private Profesor profesor;
	
	public Curso(Integer idCurso, String nombre, Integer capacidad) {
		this.identificadorCurso = idCurso;
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.alumnos = new HashSet<>();
		this.profesor = null;
	}

	public Set<Alumno> getAlumnos() {
		return alumnos;
	}
	
	public Profesor getProfesor() {
		return profesor;
	}

	public void anadirProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public Boolean anadirAlumno(Alumno alumno) {
		return alumnos.add(alumno);
	}

	public Integer getIdCurso() {
		return identificadorCurso;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.identificadorCurso);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Curso other = (Curso) obj;
		return Objects.equals(this.identificadorCurso, other.identificadorCurso);
	}
}