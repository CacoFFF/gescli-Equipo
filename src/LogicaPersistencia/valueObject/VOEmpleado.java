package LogicaPersistencia.valueObject;

public class VOEmpleado {
	public String nombre, apellido, ci, telefono, fechaNac, cel, horasDia;
	
	public String getNombre() {return nombre;}
	public String getApellido() {return apellido;}
	public String getCi() {return ci;}
	public String getTelefono() {return telefono;}
	public String getFechaNac() {return fechaNac;}
	public String getCel() {return cel;}
	public String getHorasDia() {return horasDia;}

	public void setNombre(String nombre) {this.nombre = nombre;}
	public void setApellido(String apellido) {this.apellido = apellido;}
	public void setCi(String ci) {this.ci = ci;}
	public void setTelefono(String telefono) {this.telefono = telefono;}
	public void setFechaNac(String fechaNac) {this.fechaNac = fechaNac;}
	public void setCel(String cel) {this.cel = cel;}
	public void setHorasDia(String horasDia) {this.horasDia = horasDia;}
	
	public VOEmpleado(String nombre, String apellido, String ci, String telefono, String fechaNac, String cel, String horasDia) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.ci = ci;
		this.telefono = telefono;
		this.fechaNac = fechaNac;
		this.cel = cel;
		this.horasDia = horasDia;}
}//class
