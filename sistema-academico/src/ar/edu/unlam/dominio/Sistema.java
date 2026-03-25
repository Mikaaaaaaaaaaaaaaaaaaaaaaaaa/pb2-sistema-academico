package ar.edu.unlam.dominio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Sistema {

	private Set<Curso> cursos;
	private Set<Profesor> profesores;
	private Set<Alumno> alumnos;
	private Set<Inscripcion> inscripciones;

	public Sistema() {
		this.cursos = new HashSet<Curso>();
		this.profesores = new HashSet<Profesor>();
		this.alumnos = new HashSet<Alumno>();
		this.inscripciones = new HashSet<Inscripcion>();
	}

	public Boolean agregarCurso(Curso curso) {
		return this.cursos.add(curso);
	}

	public Boolean anadirProfesor(Profesor profesor) {
		return this.profesores.add(profesor);
	}

	public Boolean anadirAlumno(Alumno alumno) {
		return this.alumnos.add(alumno);
	}

	public Set<Curso> getCursos() {
		return cursos;
	}

	public Set<Profesor> getProfesores() {
		return profesores;
	}

	public Set<Alumno> getAlumnos() {
		return alumnos;
	}

	public Set<Inscripcion> getInscripciones() {
		return inscripciones;
	}

	public List<Alumno> obtenerAlumnosInscriptosAUnCurso(Curso curso) {

		List<Alumno> alumnosInscriptos = new ArrayList<>();
		for (Inscripcion inscripcion : this.inscripciones) {
			if (inscripcion.getCurso().equals(curso)) {
				alumnosInscriptos.add(inscripcion.getAlumno());
			}
		}

		return alumnosInscriptos;
	}

	public List<Curso> obtenerCursosDeUnAlumno(Alumno alumno) {

		List<Curso> cursosAlumno = new ArrayList<>();
		for (Inscripcion inscripcion : this.inscripciones) {
			if (inscripcion.getAlumno().equals(alumno)) {
				cursosAlumno.add(inscripcion.getCurso());
			}
		}

		return cursosAlumno;
	}

	public Boolean anadirAlumnoACurso(Alumno alumno, Curso curso) {
		for (Curso evaluarCurso : this.cursos) {
			if (evaluarCurso.getIdCurso().equals(curso.getIdCurso()) && evaluarCurso.getCapacidad() > evaluarCurso.getAlumnos().size()) {
				if (evaluarCurso.getAlumnos().contains(alumno) == false) {
					evaluarCurso.anadirAlumno(alumno);
					return true;
				}

				Inscripcion inscripcion = new Inscripcion(evaluarCurso, alumno);
				this.inscripciones.add(inscripcion);
				return alumno.anadirInscripcion(inscripcion);
			}
		}

		return false;
	}

	public Boolean anadirProfesorACurso(Profesor profesor, Curso curso) {
		for (Profesor evaluarProfesores : this.profesores) {
			if (evaluarProfesores.getDni().equals(profesor.getDni())) {
				for (Curso evaluarCurso : this.cursos) {
					if (evaluarCurso.equals(curso)) {
						if (evaluarProfesores.getCursosAsignados().contains(evaluarCurso) == true) {
							return false;
						}

						evaluarProfesores.anadirCurso(evaluarCurso);
						evaluarCurso.anadirProfesor(evaluarProfesores);
						return true;
					}
				}
			}
		}

		return false;
	}
}