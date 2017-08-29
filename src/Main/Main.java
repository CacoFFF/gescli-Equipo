package Main;

import grafica.controladores.Con_CampoCI;
import grafica.controladores.Con_RellenarEmpleado;
import grafica.ventanas.fMain;


public class Main {

	public static Con_RellenarEmpleado gCon_RellenarEmpleado;
	public static Con_CampoCI gCon_CampoCI;
	
	public static void main(String[] args) {

		gCon_RellenarEmpleado = new Con_RellenarEmpleado();
		gCon_CampoCI = new Con_CampoCI();
		
		fMain.main(args);
	}

}
