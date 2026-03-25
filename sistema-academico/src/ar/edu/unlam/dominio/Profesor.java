package ar.edu.unlam.dominio;

import java.util.ArrayList;
import java.util.List;

public class Profesor {

	private Integer documento;
	private String nombre;
	private String apellido;
	private List<Curso> cursosAsignados;
	private List<EntregaTrabajoPractico> entregasDeTrabajosPracticos;

	public Profesor(Integer documento, String nombre, String apellido) {
		this.documento = documento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.cursosAsignados = new ArrayList<>();
		this.entregasDeTrabajosPracticos = new ArrayList<>();
	}

	public List<Curso> getCursosAsignados() {
		return this.cursosAsignados;
	}

	public boolean anadirCurso(Curso curso) {
		return this.cursosAsignados.add(curso);
	}

	public Integer getDni() {
		return this.documento;
	}

	public void recibirEntregaTrabajoPractico(EntregaTrabajoPractico entregaTrabajoPractico) {
		this.entregasDeTrabajosPracticos.add(entregaTrabajoPractico);
	}

	public List<EntregaTrabajoPractico> getEntregasDeTrabajosPracticos() {
		return this.entregasDeTrabajosPracticos;
	}

	public void asignarTrabajoPracticoACurso(TrabajoPractico trabajoPractico, Curso curso) {
		for (Alumno alumno : curso.getAlumnos()) {
			alumno.recibirTrabajoPractico(trabajoPractico);
		}
	}

	public void corregirEntregaTrabajoPractico(EntregaTrabajoPractico entregaTrabajoPractico, Integer nota) {
		for (EntregaTrabajoPractico entregaTp : this.entregasDeTrabajosPracticos) {
			if (entregaTp.equals(entregaTrabajoPractico) && entregaTp.getFueCorregido() == false) {
				entregaTp.setFueCorregido(true);
				entregaTp.setNota(nota);

				Inscripcion inscripcion = entregaTp.getAlumno().obtenerInscripcionPorCurso(entregaTp.getCursoAlQuePertenece());
				if (inscripcion != null) {
					inscripcion.agregarNota(nota);
				}
			}
		}
	}

	public List<EntregaTrabajoPractico> obtenerEntregaDeTrabajosPracticosSinCorregir() {
		List<EntregaTrabajoPractico> listaTrabajosPracticosSinCorregir = new ArrayList<EntregaTrabajoPractico>();

		for (EntregaTrabajoPractico entregaTrabajoPractico : this.entregasDeTrabajosPracticos) {
			if (entregaTrabajoPractico.getFueCorregido() == false) {
				listaTrabajosPracticosSinCorregir.add(entregaTrabajoPractico);
			}
		}

		return listaTrabajosPracticosSinCorregir;
	}
}