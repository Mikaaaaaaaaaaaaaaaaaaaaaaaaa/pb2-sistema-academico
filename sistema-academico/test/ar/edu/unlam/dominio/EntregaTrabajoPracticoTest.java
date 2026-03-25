package ar.edu.unlam.dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

public class EntregaTrabajoPracticoTest {

	@Test
	public void dadoQueExisteUnaEntregaTrabajoPracticoConElAtributoBooleanFueCorregidoCuandoSeInstanciaElObjetoDichoAtributoEsFalse() {
		Alumno alumno = new Alumno(123456, "Nombre", "Apellido");
		CursoMusica curso = new CursoMusica(2, "Música", 10, "Guitarra", 6.0);
		LocalDate fechaEntrega = LocalDate.of(2025, 10, 20);

		EntregaTrabajoPractico entrega = new EntregaTrabajoPractico(alumno, fechaEntrega, curso);

		assertFalse(entrega.getFueCorregido());
	}

	@Test
	public void dadoQueExisteUnaEntregaTrabajoPracticoConElAtributoIntegerNotaCuandoSeInstanciaElObjetoDichoAtributoEsNull() {
		Alumno alumno = new Alumno(123456, "Nombre", "Apellido");
		CursoMusica curso = new CursoMusica(2, "Música", 10, "Guitarra", 6.0);
		LocalDate fechaEntrega = LocalDate.of(2025, 10, 20);

		EntregaTrabajoPractico entrega = new EntregaTrabajoPractico(alumno, fechaEntrega, curso);

		assertNull(entrega.getNota());
	}

	@Test
	public void dadoQueExisteUnaEntregaTrabajoPracticoConElAtributoIntegerNotaElMetodoSetNotaActualizaDichoAtributo() {
		Alumno alumno = new Alumno(123456, "Nombre", "Apellido");
		CursoMusica curso = new CursoMusica(2, "Música", 10, "Guitarra", 6.0);
		LocalDate fechaEntrega = LocalDate.of(2025, 10, 20);

		EntregaTrabajoPractico entrega = new EntregaTrabajoPractico(alumno, fechaEntrega, curso);

		entrega.setNota(7);

		assertEquals(7, entrega.getNota(), 0.01);
	}

	@Test
	public void dadoQueExisteUnaEntregaTrabajoPracticoConElAtributoBooleanFueCorregidoElMetodoSetCorregidoActualizaDichoAtributo() {
		Alumno alumno = new Alumno(123456, "Nombre", "Apellido");
		CursoMusica curso = new CursoMusica(2, "Música", 10, "Guitarra", 6.0);
		LocalDate fechaEntrega = LocalDate.of(2025, 10, 20);

		EntregaTrabajoPractico entrega = new EntregaTrabajoPractico(alumno, fechaEntrega, curso);

		entrega.setFueCorregido(true);

		assertTrue(entrega.getFueCorregido().equals(true));
	}
}