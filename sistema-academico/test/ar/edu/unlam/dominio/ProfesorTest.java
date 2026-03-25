package ar.edu.unlam.dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ProfesorTest {

	@Test
	public void dadoQueExisteUnProfesorConElMetodoObtenerEntregasDeTrabajosPracticosSinCorregirDevuelveUnaListaDeEntregaTrabajoPracticoCuyoAtributoCorregidoSeaFalse() {
		Profesor profesor = new Profesor(123456, "Nombre", "Apellido");
		Alumno alumno = new Alumno(123456, "Nombre", "Apellido");
		Curso curso = new CursoProgramacion(1, "Nombre ", 5, "Lenguaje principal");

		Sistema sistema = new Sistema();
		sistema.anadirProfesor(profesor);
		sistema.anadirAlumno(alumno);
		sistema.agregarCurso(curso);
		sistema.anadirProfesorACurso(profesor, curso);
		sistema.anadirAlumnoACurso(alumno, curso);

		LocalDate fechaEntrega = LocalDate.of(2025, 10, 20);
		EntregaTrabajoPractico entregaTrabajoPracticoUno = new EntregaTrabajoPractico(alumno, fechaEntrega, curso);
		EntregaTrabajoPractico entregaTrabajoPracticoDos = new EntregaTrabajoPractico(alumno, fechaEntrega, curso);
		EntregaTrabajoPractico entregaTrabajoPracticoTres = new EntregaTrabajoPractico(alumno, fechaEntrega, curso);

		profesor.recibirEntregaTrabajoPractico(entregaTrabajoPracticoUno);
		profesor.recibirEntregaTrabajoPractico(entregaTrabajoPracticoDos);
		profesor.recibirEntregaTrabajoPractico(entregaTrabajoPracticoTres);

		profesor.corregirEntregaTrabajoPractico(entregaTrabajoPracticoUno, 7);

		List<EntregaTrabajoPractico> entregasDetrabajosPracticosSinCorregirObtenidos = profesor.obtenerEntregaDeTrabajosPracticosSinCorregir();

		assertTrue(entregasDetrabajosPracticosSinCorregirObtenidos.contains(entregaTrabajoPracticoDos));
		assertTrue(entregasDetrabajosPracticosSinCorregirObtenidos.contains(entregaTrabajoPracticoTres));
		assertEquals(2, entregasDetrabajosPracticosSinCorregirObtenidos.size());
	}

	@Test
	public void dadoQueExisteUnProfesorConElMetodoCorregirEntregaTrabajoPracticoSeActualizaElEstadoCorregidoDeEntregaTrabajoPracticoATrue() {
		Profesor profesor = new Profesor(123456, "Nombre", "Apellido");
		Alumno alumno = new Alumno(123456, "Nombre", "Apellido");
		Curso curso = new CursoProgramacion(1, "Nombre ", 5, "Lenguaje principal");

		Sistema sistema = new Sistema();
		sistema.anadirProfesor(profesor);
		sistema.anadirAlumno(alumno);
		sistema.agregarCurso(curso);
		sistema.anadirProfesorACurso(profesor, curso);
		sistema.anadirAlumnoACurso(alumno, curso);

		LocalDate fechaLimite = LocalDate.of(2025, 10, 20);
		TrabajoPractico trabajoPractico = new TrabajoPractico(fechaLimite, curso);

		profesor.asignarTrabajoPracticoACurso(trabajoPractico, curso);

		LocalDate fechaEntrega = LocalDate.of(2025, 10, 15);
		alumno.entregarTrabajoPractico(trabajoPractico, fechaEntrega);

		EntregaTrabajoPractico entregaTrabajoPractico = profesor.getEntregasDeTrabajosPracticos().get(0);

		profesor.corregirEntregaTrabajoPractico(entregaTrabajoPractico, 7);

		assertTrue(profesor.getEntregasDeTrabajosPracticos().get(0).getFueCorregido());
	}

	@Test
	public void dadoQueExisteUnProfesorConElMetodoRecibirEntregaTrabajoPracticoDichaEntregaTrabajoPracticoSeAgregaASuListaDeEntregasDeTrabajosPracticos() {
		Profesor profesor = new Profesor(123456, "Nombre", "Apellido");
		Alumno alumno = new Alumno(123456, "Nombre", "Apellido");
		Curso curso = new CursoProgramacion(1, "Nombre ", 5, "Lenguaje principal");

		Sistema sistema = new Sistema();
		sistema.anadirProfesor(profesor);
		sistema.anadirAlumno(alumno);
		sistema.agregarCurso(curso);
		sistema.anadirProfesorACurso(profesor, curso);
		sistema.anadirAlumnoACurso(alumno, curso);

		LocalDate fechaLimite = LocalDate.of(2025, 10, 20);
		TrabajoPractico trabajoPractico = new TrabajoPractico(fechaLimite, curso);

		profesor.asignarTrabajoPracticoACurso(trabajoPractico, curso);

		LocalDate fechaEntrega = LocalDate.of(2025, 10, 15);
		alumno.entregarTrabajoPractico(trabajoPractico, fechaEntrega);

		EntregaTrabajoPractico entregaTrabajoPractico = profesor.getEntregasDeTrabajosPracticos().get(0);

		assertTrue(profesor.getEntregasDeTrabajosPracticos().contains(entregaTrabajoPractico));
	}

	@Test
	public void dadoQueExisteUnProfesorConElMetodoCorregirEntregaTrabajoPracticoSeActualizaLaNotaDeLaEntregaTrabajoPractico() {
		Profesor profesor = new Profesor(123456, "Nombre", "Apellido");
		Alumno alumno = new Alumno(123456, "Nombre", "Apellido");
		Curso curso = new CursoProgramacion(1, "Nombre ", 5, "Lenguaje principal");

		Sistema sistema = new Sistema();
		sistema.anadirProfesor(profesor);
		sistema.anadirAlumno(alumno);
		sistema.agregarCurso(curso);
		sistema.anadirProfesorACurso(profesor, curso);
		sistema.anadirAlumnoACurso(alumno, curso);

		LocalDate fechaLimite = LocalDate.of(2025, 10, 20);
		TrabajoPractico trabajoPractico = new TrabajoPractico(fechaLimite, curso);

		profesor.asignarTrabajoPracticoACurso(trabajoPractico, curso);

		LocalDate fechaEntrega = LocalDate.of(2025, 10, 15);
		alumno.entregarTrabajoPractico(trabajoPractico, fechaEntrega);

		EntregaTrabajoPractico entregaTrabajoPractico = profesor.getEntregasDeTrabajosPracticos().get(0);
		profesor.corregirEntregaTrabajoPractico(entregaTrabajoPractico, 7);

		assertEquals(7, entregaTrabajoPractico.getNota(), 0.01);
	}

	@Test
	public void dadoQueExisteUnProfesorConElMetodoAsignarTrabajoPracticoACursoDichoTrabajoPracticoEsAnadidoALaListaDeTrabajosPracticosDeTodosLosAlumnosInscriptosEnElCurso() {
		Profesor profesor = new Profesor(123456, "Nombre", "Apellido");
		Alumno alumnoUno = new Alumno(123456, "Nombre1", "Apellido2");
		Alumno alumnoDos = new Alumno(654321, "Nombre1", "Apellido2");

		Curso curso = new CursoProgramacion(1, "Nombre ", 5, "Lenguaje principal");

		Sistema sistema = new Sistema();
		sistema.anadirProfesor(profesor);
		sistema.anadirAlumno(alumnoUno);
		sistema.anadirAlumno(alumnoDos);
		sistema.agregarCurso(curso);
		sistema.anadirProfesorACurso(profesor, curso);
		sistema.anadirAlumnoACurso(alumnoUno, curso);
		sistema.anadirAlumnoACurso(alumnoDos, curso);

		LocalDate fechaEntrega = LocalDate.of(2025, 10, 20);
		TrabajoPractico trabajoPractico = new TrabajoPractico(fechaEntrega, curso);

		profesor.asignarTrabajoPracticoACurso(trabajoPractico, curso);

		assertTrue(alumnoUno.getTrabajosPracticos().contains(trabajoPractico));
		assertTrue(alumnoDos.getTrabajosPracticos().contains(trabajoPractico));
	}

	@Test
	public void dadoQueExisteUnProfesorConElMetodoCorregirEntregaTrabajoPracticoSeAgregaLaNotaObtenidaALaListaDeNotasCorrespondienteALaInscripcionQueRelacionaAlAlumnoConElCurso() {
		Profesor profesor = new Profesor(123456, "Nombre", "Apellido");
		Alumno alumno1 = new Alumno(123456, "Nombre", "Apellido");
		Curso curso = new CursoProgramacion(1, "Nombre ", 2, "Lenguaje principal");

		Sistema sistema = new Sistema();
		sistema.anadirProfesor(profesor);
		sistema.anadirAlumno(alumno1);
		sistema.agregarCurso(curso);
		sistema.anadirProfesorACurso(profesor, curso);
		sistema.anadirAlumnoACurso(alumno1, curso);
		sistema.anadirAlumnoACurso(alumno1, curso);

		LocalDate fechaLimite = LocalDate.of(2025, 10, 20);

		TrabajoPractico trabajoPractico = new TrabajoPractico(fechaLimite, curso);
		profesor.asignarTrabajoPracticoACurso(trabajoPractico, curso);

		LocalDate fechaEntrega = LocalDate.of(2025, 10, 15);
		alumno1.entregarTrabajoPractico(trabajoPractico, fechaEntrega);

		EntregaTrabajoPractico entregaTrabajoPractico = profesor.getEntregasDeTrabajosPracticos().get(0);
		profesor.corregirEntregaTrabajoPractico(entregaTrabajoPractico, 7);

		List<Inscripcion> inscripciones = new ArrayList<>(sistema.getInscripciones());
		assertTrue(inscripciones.get(0).getNotas().contains(7));
	}
}