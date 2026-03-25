package ar.edu.unlam.dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AlumnoTest {

	@Test
	public void dadoQueExisteUnAlumnoConElMetodoEntregarTrabajoPracticoDevuelveFalseSiDichoTrabajoNoSeEncuentraEnSuListaDeTrabajosPracticos() {
		Profesor profesor = new Profesor(123, "Nombre", "Apellido");
		Alumno alumnoUno = new Alumno(123, "Nombre", "Apellido");
		Alumno alumnoDos = new Alumno(123, "Nombre", "Apellido");
		Curso cursoProgramacion = new CursoProgramacion(1, "Programación básica 1", 100, "Java");

		Sistema sistema = new Sistema();
		sistema.anadirProfesor(profesor);
		sistema.anadirProfesorACurso(profesor, cursoProgramacion);
		sistema.anadirAlumnoACurso(alumnoUno, cursoProgramacion);
		sistema.anadirAlumnoACurso(alumnoDos, cursoProgramacion);
		sistema.agregarCurso(cursoProgramacion);

		LocalDate fechaEntrega = LocalDate.of(2025, 10, 20);
		TrabajoPractico trabajoPractico = new TrabajoPractico(fechaEntrega, cursoProgramacion);

		LocalDate fechaQueSeEntrego = LocalDate.of(2025, 10, 10);
		assertFalse(alumnoUno.entregarTrabajoPractico(trabajoPractico, fechaQueSeEntrego));
	}

	@Test
	public void dadoQueExisteUnAlumnoConElMetodoEntregarTrabajoPracticoSiEsExitosoDichoTrabajoEsRemovidoDeSuListaDeTrabajosPracticos() {
		Profesor profesor = new Profesor(123, "Nombre", "Apellido");
		Alumno alumnoUno = new Alumno(123, "Nombre", "Apellido");
		Curso cursoProgramacion = new CursoProgramacion(1, "Programación básica 1", 100, "Java");

		Sistema sistema = new Sistema();
		sistema.anadirProfesor(profesor);
		sistema.anadirProfesorACurso(profesor, cursoProgramacion);
		sistema.anadirAlumnoACurso(alumnoUno, cursoProgramacion);
		sistema.agregarCurso(cursoProgramacion);

		LocalDate fechaEntrega = LocalDate.of(2025, 10, 20);
		TrabajoPractico trabajoPractico = new TrabajoPractico(fechaEntrega, cursoProgramacion);

		profesor.asignarTrabajoPracticoACurso(trabajoPractico, cursoProgramacion);

		LocalDate fechaQueSeEntrego = LocalDate.of(2025, 10, 10);
		alumnoUno.entregarTrabajoPractico(trabajoPractico, fechaQueSeEntrego);

		assertFalse(alumnoUno.getTrabajosPracticos().contains(trabajoPractico));
	}

	@Test
	public void dadoQueExisteUnAlumnoConElMetodoEntregarTrabajoPracticoSeGeneraUnaEntregaTrabajoPracticoYSeAgregaALaListaDeEntregasTrabajosPracticosDelProfesorDelCurso() {
		Profesor profesor = new Profesor(123, "Nombre", "Apellido");
		Alumno alumnoUno = new Alumno(123, "Nombre", "Apellido");

		Curso cursoProgramacion = new CursoProgramacion(1, "Programación básica 1", 100, "Java");

		Sistema sistema = new Sistema();
		sistema.anadirProfesor(profesor);
		sistema.agregarCurso(cursoProgramacion);
		sistema.anadirProfesorACurso(profesor, cursoProgramacion);
		sistema.anadirAlumnoACurso(alumnoUno, cursoProgramacion);

		LocalDate fechaEntrega = LocalDate.of(2025, 10, 20);
		TrabajoPractico trabajoPractico = new TrabajoPractico(fechaEntrega, cursoProgramacion);

		profesor.asignarTrabajoPracticoACurso(trabajoPractico, cursoProgramacion);

		LocalDate fechaQueSeEntrego = LocalDate.of(2025, 10, 10);
		alumnoUno.entregarTrabajoPractico(trabajoPractico, fechaQueSeEntrego);

		assertEquals(1, profesor.getEntregasDeTrabajosPracticos().size());
	}

	@Test
	public void dadoQueExisteUnAlumnoConElMetodoEntregarTrabajoPracticoSeGeneraUnaEntregaTrabajoPracticoQueSeAgregaALaListaDeEntregasTrabajosPracticosDelProfesorDelCursoYContieneAlAlumnoQueRealizoLaEntrega() {
		Profesor profesor = new Profesor(123, "Nombre", "Apellido");
		Alumno alumnoUno = new Alumno(123, "Nombre", "Apellido");
		Curso cursoProgramacion = new CursoProgramacion(1, "Programación básica 1", 100, "Java");

		Sistema sistema = new Sistema();
		sistema.anadirProfesor(profesor);
		sistema.agregarCurso(cursoProgramacion);
		sistema.anadirProfesorACurso(profesor, cursoProgramacion);
		sistema.anadirAlumnoACurso(alumnoUno, cursoProgramacion);

		LocalDate fechaEntrega = LocalDate.of(2025, 10, 20);
		TrabajoPractico trabajoPractico = new TrabajoPractico(fechaEntrega, cursoProgramacion);

		profesor.asignarTrabajoPracticoACurso(trabajoPractico, cursoProgramacion);

		LocalDate fechaQueSeEntrego = LocalDate.of(2025, 10, 10);
		alumnoUno.entregarTrabajoPractico(trabajoPractico, fechaQueSeEntrego);

		List<EntregaTrabajoPractico> entregasTrabajosPracticos = new ArrayList<>(profesor.getEntregasDeTrabajosPracticos());

		assertTrue(entregasTrabajosPracticos.get(0).getAlumno().equals(alumnoUno));
	}

	@Test
	public void dadoQueExisteUnAlumnoConElMetodoEntregarTrabajoPracticoSeGeneraUnaEntregaTrabajoPracticoQueSeAgregaALaListaDeEntregasTrabajosPracticosDelProfesorDelCursoYContieneLaFechaDeEntrega() {
		Profesor profesor = new Profesor(123, "Nombre", "Apellido");
		Alumno alumnoUno = new Alumno(123, "Nombre", "Apellido");
		Curso cursoProgramacion = new CursoProgramacion(1, "Programación básica 1", 100, "Java");

		Sistema sistema = new Sistema();
		sistema.anadirProfesor(profesor);
		sistema.agregarCurso(cursoProgramacion);
		sistema.anadirProfesorACurso(profesor, cursoProgramacion);
		sistema.anadirAlumnoACurso(alumnoUno, cursoProgramacion);

		LocalDate fechaEntrega = LocalDate.of(2025, 10, 20);
		TrabajoPractico trabajoPractico = new TrabajoPractico(fechaEntrega, cursoProgramacion);

		profesor.asignarTrabajoPracticoACurso(trabajoPractico, cursoProgramacion);

		LocalDate fechaQueSeEntrego = LocalDate.of(2025, 10, 10);
		alumnoUno.entregarTrabajoPractico(trabajoPractico, fechaQueSeEntrego);

		List<EntregaTrabajoPractico> entregasTrabajosPracticos = new ArrayList<>(profesor.getEntregasDeTrabajosPracticos());

		assertTrue(entregasTrabajosPracticos.get(0).getFechaLimite().equals(fechaQueSeEntrego));
	}

	@Test
	public void dadoQueExisteUnAlumnoConElMetodoEntregarTrabajoPracticoDevuelveFalseSiSeIntentaEntregarDichoTrabajoFueraDeLaFechaEstipuladaEnTrabajoPractico() {
		Profesor profesor = new Profesor(123, "Nombre", "Apellido");
		Alumno alumnoUno = new Alumno(123, "Nombre", "Apellido");
		Curso cursoProgramacion = new CursoProgramacion(1, "Programación básica 1", 100, "Java");

		Sistema sistema = new Sistema();
		sistema.anadirProfesor(profesor);
		sistema.agregarCurso(cursoProgramacion);
		sistema.anadirProfesorACurso(profesor, cursoProgramacion);
		sistema.anadirAlumnoACurso(alumnoUno, cursoProgramacion);

		LocalDate fechaEntrega = LocalDate.of(2025, 10, 20);
		TrabajoPractico trabajoPractico = new TrabajoPractico(fechaEntrega, cursoProgramacion);

		profesor.asignarTrabajoPracticoACurso(trabajoPractico, cursoProgramacion);

		LocalDate fechaQueSeEntrego = LocalDate.of(2025, 10, 21);
		assertFalse(alumnoUno.entregarTrabajoPractico(trabajoPractico, fechaQueSeEntrego));
	}
}