package LogicaPersistencia.valueObject;

public class VOEmpleado extends VOGenerico {
	private String nombre, apellido, ci, fechaNac, cel, horasDia;
	private boolean baja;
	
	public String getNombre() {return nombre;}
	public String getApellido() {return apellido;}
	public String getCi() {return ci;}
	public String getFechaNac() {return fechaNac;}
	public String getCel() {return cel;}
	public String getHorasDia() {return horasDia;}
	public boolean getBaja() {return baja;}

	public void setNombre(String nombre) {this.nombre = nombre;}
	public void setApellido(String apellido) {this.apellido = apellido;}
	public void setCi(String ci) {this.ci = ci;}
	public void setFechaNac(String fechaNac) {this.fechaNac = fechaNac;}
	public void setCel(String cel) {this.cel = cel;}
	public void setHorasDia(String horasDia) {this.horasDia = horasDia;}
	public void setBaja( boolean baja) {this.baja = baja;}
	
	
	//Este constructor es utilizado cuando queremos rellenarlo desde la base de datos
	public VOEmpleado( String ci){
		super();
		this.ci = ci;
	}
	
	//Para BajaEmpleado
	public VOEmpleado(String sCI, boolean bBaja){
		super();
		ci=sCI;
		baja=bBaja;}
	
	//Este constructor es utilizado cuando queremos agregar una entrada
	public VOEmpleado(String nombre, String apellido, String ci, String fechaNac, String cel, String horasDia, boolean baja) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.ci = ci;
		this.fechaNac = fechaNac;
		this.cel = cel;
		this.horasDia = horasDia;
		this.baja = baja;
	}
}//class
