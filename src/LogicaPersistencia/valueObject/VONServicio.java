package LogicaPersistencia.valueObject;

public class VONServicio extends VOGenerico
{
	private String sNombre;

	public VONServicio( String sNombre)
	{
		super();
		this.sNombre = sNombre;
	}

	public String getsNombre() {return sNombre;	}
	public void setsNombre(String sNombre) {this.sNombre = sNombre;}
}
