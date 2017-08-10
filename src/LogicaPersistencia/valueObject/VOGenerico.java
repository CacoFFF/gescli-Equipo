package LogicaPersistencia.valueObject;

//
// Value Object generico
//
public class VOGenerico
{
	private String error;
	private String resultado;

	public VOGenerico()
	{
		this.error = "";
		this.resultado = "";
	}

	public String getError() {return error;}
	public String getResultado() {return resultado;}
	public void setError(String error) {this.error = error;}
	public void setResultado(String resultado) {this.resultado = resultado;}
}
