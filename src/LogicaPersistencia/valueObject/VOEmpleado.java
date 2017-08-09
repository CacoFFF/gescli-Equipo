package LogicaPersistencia.valueObject;

public class VOEmpleado extends VOPeticion {
	private String nombre, apellido, ci, fechaNac, cel, horasDia;
	private boolean baja;
	
	public String getNombre() {return nombre;}
	public String getApellido() {return apellido;}
	public String getCi() {return ci;}
	public String getFechaNac() {return fechaNac;}
	public String getCel() {return cel;}
	public String getHorasDia() {return horasDia;}
	public boolean getBaja() {return baja;}

/*	public void setNombre(String nombre) {this.nombre = nombre;}
	public void setApellido(String apellido) {this.apellido = apellido;}
	public void setCi(String ci) {this.ci = ci;}
	public void setTelefono(String telefono) {this.telefono = telefono;}
	public void setFechaNac(String fechaNac) {this.fechaNac = fechaNac;}
	public void setCel(String cel) {this.cel = cel;}
	public void setHorasDia(String horasDia) {this.horasDia = horasDia;}
*/	
	
	//Este constructor es utilizado cuando queremos rellenarlo desde la base de datos
	public VOEmpleado( String ci){
		super( "funcionarios");
		this.ci = ci;
	}
	
	
	//Este constructor es utilizado cuando queremos agregar una entrada
	public VOEmpleado(String nombre, String apellido, String ci, String fechaNac, String cel, String horasDia, boolean baja) {
		super( "funcionarios");
		this.nombre = nombre;
		this.apellido = apellido;
		this.ci = ci;
		this.fechaNac = fechaNac;
		this.cel = cel;
		this.horasDia = horasDia;
		this.baja = baja;
		String[] elementos = { "nomFun", "apeFun", "ciFun", "fechaNacFun", "celFun", "baja", "horasDia" };
		setElementos( elementos);
	}
}//class
