package ar.edu.unlam.dominio;

import java.util.ArrayList;
import java.util.List;

public class Inscripcion {

	private Curso curso;
	private Alumno alumno;
	private List<Integer> notas;

	public Inscripcion(Curso curso, Alumno alumno) {
		super();
		this.curso = curso;
		this.alumno = alumno;
		this.notas = new ArrayList<Integer>();
	}

	public List<Integer> getNotas() {
		return this.notas;
	}

	public Curso getCurso() {
		return this.curso;
	}

	public Alumno getAlumno() {
		return this.alumno;
	}

	public void agregarNota(Integer nota) {
		this.notas.add(nota);
	}

	public Double calcularPromedio() {

		Double suma = 0.0;
		if (this.notas != null && this.notas.isEmpty() != true) {
			for (Integer nota : this.notas) {
				suma += nota;
			}

			return suma / this.notas.size();
		}

		return 0.0;
	}
}