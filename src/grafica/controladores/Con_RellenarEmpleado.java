package grafica.controladores;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import Main.Main;
import LogicaPersistencia.valueObject.VOEmpleado;

public class Con_RellenarEmpleado extends ControladorMaestro {

	public void RellenarEmpleado( String sCI,
			JTextField oNombre, 
			JTextField oApellido, 
			JTextField oFechaNac, 
			JTextField oCel,
			JTextField oHorasDia,
			JCheckBox oBaja)
	{
		
		if ( !ControladorMaestro.CIValida(sCI) )
			return; //Mostrar mensaje de error?
		
		VOEmpleado VO = gFachada.ObtenerEmpleado(sCI);
		if ( VO != null )
		{
			if ( VO.getError().length() != 0 )
			{
				System.out.println("Error RellenarEmpleado: " + VO.getError());
				//Mostrar mensaje de error
			}
			else
			{
				oNombre.setText  ( VO.getNombre() );
				oApellido.setText( VO.getApellido() );
				oFechaNac.setText( VO.getFechaNac() );
				oCel.setText     ( VO.getCel() );
				oHorasDia.setText( VO.getHorasDia() );
				oBaja.setSelected( VO.getBaja() );
				if ( VO.getResultado().length() != 0 )
				{} //Mostrar mensaje de resultado (?, si es que es necesario)
			}
		}
	}
}
