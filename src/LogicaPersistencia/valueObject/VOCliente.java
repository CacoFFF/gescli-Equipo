package LogicaPersistencia.valueObject;

public class VOCliente extends VOGenerico{

	private int iIdCli, iIdDepto, iHrCargables, iHonorarios, iMoneda, iTipoPersona;
	private String sContacto, sRut, sNroCli, sTel, sDireccion, sNomCli;
	
	private String sNomDepto;
	/*sNomDepto es el Nombre que se muestra en el ComboBox, iIdDepto es el identificador para agregar cliente*/
	/*sNroCliNuevo es usado para la modificacion del cliente*/
	
	public int getiIdCli() {return iIdCli;}
	public int getiIdDepto() {return iIdDepto;}
	public int getiHrCargables() {return iHrCargables;}
	public int getiHonorarios() {return iHonorarios;}
	public int getiMoneda() {return iMoneda;}
	public int getiTipoPersona() {return iTipoPersona;}
	public String getsContacto() {return sContacto;}
	public String getsRut() {return sRut;}
	public String getsNroCli() {return sNroCli;}
	public String getsTel() {return sTel;}
	public String getsDireccion() {return sDireccion;}
	public String getsNomCli() {return sNomCli;}
	
	public void setiIdCli(int iIdCli) {this.iIdCli = iIdCli;}
	public void setiIdDepto(int iIdDepto) {this.iIdDepto = iIdDepto;}
	public void setiHrCargables(int iHrCargables) {this.iHrCargables = iHrCargables;}
	public void setiHonorarios(int iHonorarios) {this.iHonorarios = iHonorarios;}
	public void setiMoneda(int iMoneda) {this.iMoneda = iMoneda;}
	public void setiTipoPersona(int iTipoPersona) {this.iTipoPersona = iTipoPersona;}
	public void setsContacto(String sContacto) {this.sContacto = sContacto;}
	public void setsRut(String sRut) {this.sRut = sRut;}
	public void setsNroCli(String sNroCli) {this.sNroCli = sNroCli;}
	public void setsTel(String sTel) {this.sTel = sTel;}
	public void setsDireccion(String sDireccion) {this.sDireccion = sDireccion;}
	public void setsNomCli(String sNomCli) {this.sNomCli = sNomCli;}
	
	public String getsNomDepto() {return sNomDepto;}
	public void setsNomDepto(String sNomDepto) {this.sNomDepto = sNomDepto;}

	//Agregar
	public VOCliente(int iIdDepto, int iHrCargables, int iHonorarios, int iMoneda,
			String sRut, String sNroCli, String sTel, String sDireccion, String sNomCli) {
		super();
		this.iIdDepto = iIdDepto;
		this.iHrCargables = iHrCargables;
		this.iHonorarios = iHonorarios;
		this.iMoneda = iMoneda;
		this.sRut = sRut;
		this.sNroCli = sNroCli;
		this.sTel = sTel;
		this.sDireccion = sDireccion;
		this.sNomCli = sNomCli;
	}
	//Obtener ID Departamento
	public VOCliente(String sNomDepto){ super(); this.sNomDepto=sNomDepto;}
	
	//Buscar y borrar
	public VOCliente(String sNumCli, int iIDCli){ super(); this.sNroCli=sNumCli; this.iIdCli=iIDCli;}
	
	//Modificar
	public VOCliente(int iIdDepto, int iHrCargables, int iHonorarios, int iMoneda,
			String sRut, String sNroCliNuevo, String sTel, String sDireccion, String sNomCli, int iIDCli, String sNumCli){
		super(); 
		this.iIdDepto = iIdDepto;
		this.iHrCargables = iHrCargables;
		this.iHonorarios = iHonorarios;
		this.iMoneda = iMoneda;
		this.sRut = sRut;
		this.sNroCli = sNroCliNuevo;
		this.sTel = sTel;
		this.sDireccion = sDireccion;
		this.sNomCli = sNomCli;
		
		this.iIdCli = iIDCli;
		this.sNroCli = sNumCli;
		
		
	}


	
	
	




}
