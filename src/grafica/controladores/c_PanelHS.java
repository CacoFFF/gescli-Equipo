package grafica.controladores;

import java.awt.Choice;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import grafica.ventanas.cmpFecha;

public abstract class c_PanelHS extends c_Maestro
{
	public static boolean Validar( JComboBox<String>[] Combos, cmpFecha Fecha, JTextField Horas)
	{
		for ( int i=0 ; i<3 ; i++ )
			if ( Combos[i].getSelectedIndex() == 0 )
				return false;
		if ( !Fecha.fechaValida() )
			return false;
		if ( !IsNumeric( Horas.getText() ) )
			return false;
		
		return true;
	}
	
	public static void ActivarBoton( JButton Boton, JComboBox<String>[] Combos, cmpFecha Fecha, JTextField Horas)
	{
		Boton.setEnabled( Validar(Combos, Fecha, Horas) );
	}
}
