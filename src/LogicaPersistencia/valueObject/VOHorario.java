package LogicaPersistencia.valueObject;

public class VOHorario extends VOGenerico{
	int iHoras;
	int idCli, idFun, idServ; //Solo usados en el cache
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

	public int getIdCli() {	return idCli;	}
	public int getIdFun() {	return idFun;	}
	public int getIdServ() {return idServ;	}
	public void setIdCli(int idCli) {	this.idCli = idCli;	}
	public void setIdFun(int idFun) {	this.idFun = idFun;	}
	public void setIdServ(int idServ) {	this.idServ = idServ;	}
	
	

}
