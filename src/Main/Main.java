package Main;

import grafica.controladores.*;
import grafica.controladores.color.*;
import grafica.ventanas.fMain;


public class Main {

	public static c_RellenarEmpleado gCon_RellenarEmpleado;
	public static c_CampoCI gCon_CampoCI;
	public static c_Fecha gCon_Fecha;
	public static c_NServicio gCon_NServicio; 
	public static c_Cliente gCon_Cliente;
	public static c_Funcionario gCon_Funcionario;
	public static c_Horarios gCon_Horarios;
	
	public static void main(String[] args) {

		gCon_RellenarEmpleado = new c_RellenarEmpleado();
		gCon_CampoCI = new c_CampoCI();
		gCon_Fecha = new c_Fecha();
		gCon_NServicio = new c_NServicio();
		gCon_Cliente = new c_Cliente();
		gCon_Funcionario = new c_Funcionario();
		gCon_Horarios = new c_Horarios();
		fMain.main(args);
	}

}
