package ar.edu.unlam.dominio;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class InscripcionTest {

	@Test
	public void dadoQueExisteUnaInscripcionConUnaListaDeNotasElMetodoCalcularPromedioDevuelveCeroSiNoExistenNotas() {
		Alumno alumno = new Alumno(123456, "Nombre", "Apellido");
		Curso curso = new CursoProgramacion(1, "Introducción a la programación", 100, "Java");

		Inscripcion inscripcion = new Inscripcion(curso, alumno);

		assertEquals(0, inscripcion.calcularPromedio(), 0.01);
	}

	@Test
	public void dadoQueExisteUnaInscripcionConUnaListaDeNotasElMetodoCalcularPromedioDevuelveLaDivisionEntreLasNotasYElSizeDeDichaLista() {
		Alumno alumno = new Alumno(123456, "Nombre", "Apellido");
		Curso curso = new CursoProgramacion(1, "Introducción a la programación", 100, "Java");

		Inscripcion inscripcion = new Inscripcion(curso, alumno);

		inscripcion.agregarNota(7);
		inscripcion.agregarNota(8);

		assertEquals(7.5, inscripcion.calcularPromedio(), 0.01);
	}

	@Test
	public void dadoQueExisteUnaInscripcionConUnaListaDeNotasElMetodoAgregarNotaAgregaLaNotaADichaLista() {
		Alumno alumno = new Alumno(123456, "Nombre", "Apellido");
		Curso curso = new CursoProgramacion(1, "Introducción a la programación", 100, "Java");

		Inscripcion inscripcion = new Inscripcion(curso, alumno);
		inscripcion.agregarNota(7);

		assertEquals(1, inscripcion.getNotas().size());
	}

	@Test
	public void dadoQueExisteUnaInscripcionAlInstanciarlaSeGuardanElAlumnoYElCursoAlQuePertenece() {
		Alumno alumno = new Alumno(123456, "Nombre", "Apellido");
		Curso curso = new CursoProgramacion(1, "Introducción a la programación", 100, "Java");

		Inscripcion inscripcion = new Inscripcion(curso, alumno);

		assertEquals(alumno, inscripcion.getAlumno());
		assertEquals(curso, inscripcion.getCurso());
	}
}