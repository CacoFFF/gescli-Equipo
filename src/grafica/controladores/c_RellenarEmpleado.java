package grafica.controladores;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import Main.Main;
import LogicaPersistencia.valueObject.VOEmpleado;

public class c_RellenarEmpleado extends c_Maestro {

	public void RellenarEmpleado( String sCI,
			JTextField oNombre, 
			JTextField oApellido, 
			JTextField oFechaNac, 
			JTextField oCel,
			JTextField oHorasDia,
			JCheckBox oBaja)
	{
		if ( !CIValida(sCI) )
		{

			MensajeWin( "Formato de CI erróneo" + "\r\n" + "Usar: x.xxx.xxx-x");
			return; //Mostrar mensaje de error?
		}
		
		VOEmpleado VO = gFachada.ObtenerEmpleado(sCI);
		if ( VO != null )
		{
			if ( VO.getError().length() != 0 )
				MensajeWin(VO.getError());
			else
			{
				oNombre.setText  ( VO.getNombre() );
				oApellido.setText( VO.getApellido() );
				oFechaNac.setText( VO.getFechaNac() );
				oCel.setText     ( VO.getCel() );
				oHorasDia.setText( VO.getHorasDia() );
				oBaja.setSelected( VO.getBaja() );
				if ( VO.getResultado().length() != 0 )
					MensajeWin(VO.getResultado());
			}
		}
	}
}
