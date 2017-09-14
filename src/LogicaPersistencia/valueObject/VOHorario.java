package LogicaPersistencia.valueObject;

public class VOHorario extends VOGenerico{
	int  iHoras;
	String sNomServicio, sNumCliente, sCIFuncionario, sFecha;
	
	public int getiHoras() {return iHoras;}
	public String getsNomServicio() {return sNomServicio;}
	public String getsNumCliente() {return sNumCliente;}
	public String getsCIFuncionario() {return sCIFuncionario;}
	public String getsFecha() {return sFecha;}
	
	public void setiHoras(int iHoras) {this.iHoras = iHoras;}
	public void setsNomServicio(String sNomServicio) {this.sNomServicio = sNomServicio;}
	public void setsNumCliente(String sNumCliente) {this.sNumCliente = sNumCliente;}
	public void setsCIFuncionario(String sCIFuncionario) {this.sCIFuncionario = sCIFuncionario;}
	public void setsFecha(String sFecha) {this.sFecha = sFecha;}
	
	public VOHorario(int iHoras, String sNomServicio, String sNumCliente, String sCIFuncionario, String sFecha) {
		this.iHoras = iHoras;
		this.sNomServicio = sNomServicio;
		this.sNumCliente = sNumCliente;
		this.sCIFuncionario = sCIFuncionario;
		this.sFecha = sFecha;
	}
	
	

}
