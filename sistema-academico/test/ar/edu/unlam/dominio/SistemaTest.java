package ar.edu.unlam.dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class SistemaTest {
	
	@Test
	public void dadoQueExisteUnSistemaElMetodoAgregarCursoDevuelveTrue() {
		Curso curso = new CursoProgramacion(1, "Nombre", 5, "Lenguaje principal");
		Sistema sistema = new Sistema();

		assertTrue(sistema.agregarCurso(curso));
	}
	
	@Test
	public void dadoQueExisteUnSistemaElMetodoAnadirAlumnoACursoDevuelveTrue() {
		Alumno alumno = new Alumno(123456, "Nombre", "Apellido");
		Curso curso = new CursoDisenio(1, "Nombre", 20, "Software principal", 7.0);

		Sistema sistema = new Sistema();
		sistema.agregarCurso(curso);
		sistema.anadirAlumno(alumno);

		assertTrue(sistema.anadirAlumnoACurso(alumno, curso));
	}
	
	@Test
	public void dadoQueExisteUnSistemaConElMetodoAnadirAlumnoACursoDichoMetodoAnadeAlAlumnoALaListaDeAlumnosDelCurso() {
		Alumno alumno = new Alumno(123456, "Nombre", "Apellido");
		Curso curso = new CursoDisenio(1, "Nombre", 20, "Software principal", 7.0);

		Sistema sistema = new Sistema();
		sistema.agregarCurso(curso);
		sistema.anadirAlumno(alumno);
		sistema.anadirAlumnoACurso(alumno, curso);

		assertTrue(curso.getAlumnos().contains(alumno));
	}
	
	@Test
	public void dadoQueExisteUnSistemaConElMetodoAnadirProfesorACursoDichoMetodoActualizaElAtributoProfesorDelCurso() {
		Profesor profesor = new Profesor(123456, "Nombre", "Apellido");
		Curso curso = new CursoDisenio(1, "Nombre", 20, "Software principal", 7.0);

		Sistema sistema = new Sistema();
		sistema.agregarCurso(curso);
		sistema.anadirProfesor(profesor);
		sistema.anadirProfesorACurso(profesor, curso);

		assertTrue(curso.getProfesor().equals(profesor));
	}
	
	@Test
	public void dadoQueExisteUnSistemaElMetodoAnadirProfesorACursoDevuelveTrue() {
		Profesor profesor = new Profesor(123456, "Nombre", "Apellido");
		Curso curso = new CursoDisenio(1, "Nombre", 20, "Software principal", 7.0);

		Sistema sistema = new Sistema();
		sistema.agregarCurso(curso);
		sistema.anadirProfesor(profesor);

		assertTrue(sistema.anadirProfesorACurso(profesor, curso));
	}
	
	@Test
	public void dadoQueExisteUnSistemaConUnaListaDeInscripcionesElMetodoAnadirAlumnoACursoAnadeUnaInscripcionADichaLista() {
		Alumno alumno = new Alumno(123456, "Nombre", "Apellido");
		Curso curso = new CursoDisenio(1, "Nombre", 20, "Software principal", 7.0);

		Sistema sistema = new Sistema();
		sistema.agregarCurso(curso);
		sistema.anadirAlumno(alumno);
		sistema.anadirAlumnoACurso(alumno, curso);
		sistema.anadirAlumnoACurso(alumno, curso);

		assertEquals(1, sistema.getInscripciones().size(), 0.01);
	}
	
	@Test
	public void dadoQueYaExisteUnProfesorAsignadoAlCursoNoSePermiteAsignarloNuevamente() {
		Profesor profesor = new Profesor(123456, "Nombre", "Apellido");
		Curso curso = new CursoDisenio(1, "Nombre", 20, "Software principal", 7.0);

		Sistema sistema = new Sistema();
		sistema.anadirProfesor(profesor);
		sistema.agregarCurso(curso);

		assertTrue(sistema.anadirProfesorACurso(profesor, curso));
		assertFalse(sistema.anadirProfesorACurso(profesor, curso));
	}
	
	@Test
	public void dadoQueExisteUnSistemaElMetodoObtenerCursosDeAlumnoDevuelveLosCursosCorrectos() {
		Sistema sistema = new Sistema();

		Alumno alumno = new Alumno(123456, "Nombre", "Apellido");
		Curso cursoUno = new CursoProgramacion(1, "Nombre", 5, "Lenguaje principal");
		Curso cursoDos = new CursoDisenio(2, "Nombre", 5, "Software principal", 7.0);

		sistema.agregarCurso(cursoUno);
		sistema.agregarCurso(cursoDos);

		sistema.anadirAlumno(alumno);
		sistema.anadirAlumnoACurso(alumno, cursoUno);
		sistema.anadirAlumnoACurso(alumno, cursoUno);
		sistema.anadirAlumnoACurso(alumno, cursoUno);

		List<Curso> cursosDelAlumno = sistema.obtenerCursosDeUnAlumno(alumno);
		

		assertEquals(2, cursosDelAlumno.size());
	}
	
	@Test
	public void dadoQueExisteUnSistemaElMetodoObtenerAlumnosInscriptosAUnCursoDevuelveLosAlumnosInscriptosADichoCurso() {
		Sistema sistema = new Sistema();

		Alumno alumnoUno = new Alumno(123456, "Nombre1", "Apellido1");
		Alumno alumnoDos = new Alumno(654321, "Nombre2", "Apellido2");

		Curso curso = new CursoProgramacion(1, "Nombre", 5, "Lenguaje principal");

		sistema.agregarCurso(curso);
		sistema.anadirAlumno(alumnoUno);
		sistema.anadirAlumno(alumnoDos);

		sistema.anadirAlumnoACurso(alumnoUno, curso);
		sistema.anadirAlumnoACurso(alumnoUno, curso);
		sistema.anadirAlumnoACurso(alumnoUno, curso);

		List<Alumno> alumnosInscriptos = sistema.obtenerAlumnosInscriptosAUnCurso(curso);

		assertEquals(2, alumnosInscriptos.size());
	}
}