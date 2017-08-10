package Main;

import grafica.ventanas.AltaFuncionario;
import grafica.controladores.*;

public class Main {

	public static Con_AltaEmpleado gCon_AltaEmpleado;
	public static Con_BajaEmpleado gCon_BajaEmpleado;
	public static Con_RellenarEmpleado gCon_RellenarEmpleado;
	public static Con_CampoCI gCon_CampoCI;
	
	public static void main(String[] args) {

		gCon_AltaEmpleado = new Con_AltaEmpleado();
		gCon_BajaEmpleado = new Con_BajaEmpleado();
		gCon_RellenarEmpleado = new Con_RellenarEmpleado();
		gCon_CampoCI = new Con_CampoCI();
		
		AltaFuncionario.main( args);
	}

}
