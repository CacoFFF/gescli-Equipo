package grafica.controladores;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import Main.Main;
import LogicaPersistencia.valueObject.VOEmpleado;

public class Con_RellenarEmpleado extends ControladorMaestro {

	public void RellenarEmpleado( String sCI,
			JTextField oNombre, 
			JTextField oApellido, 
			JTextField oTelefono,
			JTextField oFechaNac, 
			JTextField oCel,
			JTextField oHorasDia,
			JCheckBox oBaja)
	{
		
		if ( !ControladorMaestro.CIValida(sCI) )
			return; //Mostrar mensaje de error?
		
		VOEmpleado VO = Main.gFachada.ObtenerEmpleado(sCI);
		if ( VO != null )
		{
			oNombre.setText  ( VO.getNombre() );
			oApellido.setText( VO.getApellido() );
			oFechaNac.setText( VO.getFechaNac() );
			oCel.setText     ( VO.getCel() );
			oHorasDia.setText( VO.getHorasDia() );
			oBaja.setSelected( VO.getBaja() );
		}
		//Comunicar con fachada:
		// Fachada decide si empleado existe (basado en CI)
		// Si existe, hacer una segunda peticion que nos de un VO de empleado
	}
}
