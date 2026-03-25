package ar.edu.unlam.dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

public class CursoTest {

	@Test
	public void dadoQueExisteUnCursoQueImplementaLaInterfazEvaluableSiUnAlumnoEntrega2TPsConNotasCuyoPromedioNoEsSuficienteParaAprobarEntoncesElMetodoEstaAprobadoRetornaFalse() {
		CursoDisenio curso = new CursoDisenio(1, "Diseño Gráfico", 10, "Photoshop CC", 7.0);

		Alumno alumno = new Alumno(123, "Nombre", "Apellido");
		Profesor profesor = new Profesor(123, "Nombre", "Apellido");

		Sistema sistema = new Sistema();
		sistema.agregarCurso(curso);
		sistema.anadirProfesor(profesor);
		sistema.anadirAlumno(alumno);
		sistema.anadirProfesorACurso(profesor, curso);
		sistema.anadirAlumnoACurso(alumno, curso);
		sistema.anadirAlumnoACurso(alumno, curso);

		TrabajoPractico trabajoPracticoUno = new TrabajoPractico(LocalDate.of(2025, 10, 20), curso);
		TrabajoPractico trabajoPracticoDos = new TrabajoPractico(LocalDate.of(2025, 10, 20), curso);

		profesor.asignarTrabajoPracticoACurso(trabajoPracticoUno, curso);
		profesor.asignarTrabajoPracticoACurso(trabajoPracticoDos, curso);

		alumno.entregarTrabajoPractico(trabajoPracticoUno, LocalDate.of(2025, 10, 18));
		alumno.entregarTrabajoPractico(trabajoPracticoDos, LocalDate.of(2025, 10, 19));

		profesor.corregirEntregaTrabajoPractico(profesor.getEntregasDeTrabajosPracticos().get(0), 5);
		profesor.corregirEntregaTrabajoPractico(profesor.getEntregasDeTrabajosPracticos().get(1), 6);

		assertFalse(curso.estaAprobado(alumno.obtenerInscripcionPorCurso(curso).calcularPromedio()));
	}

	@Test
	public void dadoQueExisteUnCursoQueImplementaLaInterfazEvaluableSiUnAlumnoEntrega2TPsConNotasCuyoPromedioEsSuficienteParaAprobarEntoncesElMetodoEstaAprobadoRetornaTrue() {
		CursoDisenio curso = new CursoDisenio(2, "Diseño Gráfico", 10, "Photoshop CC", 7.0);

		Alumno alumno = new Alumno(123, "Nombre", "Apellido");
		Profesor profesor = new Profesor(123, "Nombre", "Apellido");

		Sistema sistema = new Sistema();
		sistema.agregarCurso(curso);
		sistema.anadirProfesor(profesor);
		sistema.anadirAlumno(alumno);
		sistema.anadirProfesorACurso(profesor, curso);
		sistema.anadirAlumnoACurso(alumno, curso);
		sistema.anadirAlumnoACurso(alumno, curso);

		TrabajoPractico trabajoPracticoUno = new TrabajoPractico(LocalDate.of(2025, 10, 20), curso);
		TrabajoPractico trabajoPracticoDos = new TrabajoPractico(LocalDate.of(2025, 10, 20), curso);

		profesor.asignarTrabajoPracticoACurso(trabajoPracticoUno, curso);
		profesor.asignarTrabajoPracticoACurso(trabajoPracticoDos, curso);

		alumno.entregarTrabajoPractico(trabajoPracticoUno, LocalDate.of(2025, 10, 18));
		alumno.entregarTrabajoPractico(trabajoPracticoDos, LocalDate.of(2025, 10, 19));

		profesor.corregirEntregaTrabajoPractico(profesor.getEntregasDeTrabajosPracticos().get(0), 8);
		profesor.corregirEntregaTrabajoPractico(profesor.getEntregasDeTrabajosPracticos().get(1), 9);

		Double promedioFinal = alumno.obtenerInscripcionPorCurso(curso).calcularPromedio();

        assertTrue(curso.estaAprobado(promedioFinal));
	}

	@Test
	public void dadoQueExisteUnCursoElMetodoAnadirAlumnoDevuelveTrue() {
		Alumno alumno = new Alumno(123, "Nombre", "Apellido");
		Curso curso = new CursoProgramacion(1, "Programación básica 1", 20, "Java");
		Sistema sistema = new Sistema();
		sistema.agregarCurso(curso);

		assertTrue(curso.anadirAlumno(alumno));
	}

	@Test
	public void dadoQueExisteUnCursoElMetodoAnadirProfesorDevuelveTrue() {
		Profesor profesor = new Profesor(123, "Nombre", "Apellido");
		Curso curso = new CursoProgramacion(1, "Programación básica 1", 20, "Java");
		Sistema sistema = new Sistema();
		sistema.agregarCurso(curso);

		assertTrue(sistema.anadirProfesor(profesor));
	}

	@Test
	public void dadoQueExisteUnCursoConUnaListaDeAlumnosElMetodoAnadirAlumnoAgregaDichoAlumnoASuLista() {
		Curso curso = new CursoProgramacion(1, "Programación básica 1", 20, "Java");

		Sistema sistema = new Sistema();
		sistema.agregarCurso(curso);

		Alumno alumno = new Alumno(123, "Nombre", "Apellido");
		curso.anadirAlumno(alumno);

		Integer tamanioEsperado = 1;
		Integer tamanioObtenido = curso.getAlumnos().size();

		assertEquals(tamanioEsperado, tamanioObtenido);
		assertTrue(curso.getAlumnos().contains(alumno));
	}

	@Test
	public void dadoQueExisteUnCursoConUnAtributoProfesorElMetodoAnadirProfesorActualizaDichoAtributo() {
		Curso curso = new CursoProgramacion(1, "Programación básica 1", 20, "Java");

		Sistema sistema = new Sistema();
		sistema.agregarCurso(curso);

		Profesor profesor = new Profesor(123456, "Nombre", "Apellido");
		curso.anadirProfesor(profesor);

		assertEquals(profesor, curso.getProfesor());
	}

	@Test
	public void dadoQueExisteUnCursoYQueNoQuedanCuposEnElCursoNoSePuedeAnadirAlAlumno() {
		Curso curso = new CursoProgramacion(1, "Programación básica 1", 1, "Java");

		Alumno alumnoUno = new Alumno(123456, "Nombre", "Apellido");

		Sistema sistema = new Sistema();
		sistema.agregarCurso(curso);
		sistema.anadirAlumno(alumnoUno);
		sistema.anadirAlumnoACurso(alumnoUno, curso);

		assertFalse(sistema.anadirAlumnoACurso(alumnoUno, curso));
	}
}