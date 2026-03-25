package ar.edu.unlam.dominio;

public class CursoDisenio extends Curso implements Evaluable {

	private String softwarePrincipal;
	private Double promedioFinalConElQueSeAprueba;

	public CursoDisenio(Integer idCurso, String nombre, Integer capacidad, String softwarePrincipal, Double promedioFinalConElQueSeAprueba) {
		super(idCurso, nombre, capacidad);
		this.softwarePrincipal = softwarePrincipal;
		this.promedioFinalConElQueSeAprueba = promedioFinalConElQueSeAprueba;
	}

	@Override
	public Boolean estaAprobado(Double promedioFinal) {
		return promedioFinal >= this.promedioFinalConElQueSeAprueba;
	}
}