package Main;

import grafica.controladores.c_RellenarEmpleado;
import grafica.controladores.color.*;
import grafica.ventanas.fMain;


public class Main {

	public static c_RellenarEmpleado gCon_RellenarEmpleado;
	public static c_CampoCI gCon_CampoCI;
	public static c_Fecha gCon_Fecha;
	
	public static void main(String[] args) {

		gCon_RellenarEmpleado = new c_RellenarEmpleado();
		gCon_CampoCI = new c_CampoCI();
		gCon_Fecha = new c_Fecha();
		
		fMain.main(args);
	}

}
