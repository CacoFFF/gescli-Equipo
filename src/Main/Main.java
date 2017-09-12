package Main;

import grafica.controladores.*;
import grafica.controladores.color.*;
import grafica.ventanas.fMain;


public class Main {

	public static c_RellenarEmpleado gCon_RellenarEmpleado;
	public static c_CampoCI gCon_CampoCI;
	public static c_Fecha gCon_Fecha;
	public static c_NServicio gCon_NServicio; 
	
	public static void main(String[] args) {

		gCon_RellenarEmpleado = new c_RellenarEmpleado();
		gCon_CampoCI = new c_CampoCI();
		gCon_Fecha = new c_Fecha();
		gCon_NServicio = new c_NServicio();
		
		fMain.main(args);
	}

}
