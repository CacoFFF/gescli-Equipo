package grafica.controladores;

import javax.swing.JTextField;

public class Con_RellenarEmpleado extends ControladorMaestro {

	public void RellenarEmpleado( 
			JTextField oNombre, 
			JTextField oApellido, 
			JTextField oTelefono,
			JTextField oFechaNac, 
			JTextField oCel,
			JTextField oHorasDia)
	{
		//Comunicar con fachada:
		// Fachada decide si empleado existe (basado en CI)
		// Si existe, hacer una segunda peticion que nos de un VO de empleado
		// Usar ese VO para rellenar los campos
	}
}
