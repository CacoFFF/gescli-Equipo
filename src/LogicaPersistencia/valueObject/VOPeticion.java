package LogicaPersistencia.valueObject;

//
// Value Object generico
//
public class VOPeticion
{
	private final String nombreTabla;
	private String[] elementos;
	private String condicionSQL;

	public String[] getElementos() {return elementos;}
	public String getCondicionSQL() {return condicionSQL;}

	public void setElementos(String[] elementos) {this.elementos = elementos;}
	public void setCondicionSQL(String condicionSQL) {this.condicionSQL = condicionSQL;}

	
	public VOPeticion( String nombreTabla)
	{
		this.nombreTabla = nombreTabla;
	}
	

}
