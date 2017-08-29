package grafica.controladores;

import javax.swing.JTextField;
import java.awt.Color;

//Este controlador se encarga de proveer informacion en tiempo
//real al usuario con respecto a los campos de C.I
public class Con_CampoCI extends c_Maestro
{
	private Color colorVacioS;
	private Color colorValidoS;
	private Color colorInvalidoS;
	private Color colorVacio;
	private Color colorValido;
	private Color colorInvalido;
	
	public Con_CampoCI()
	{
		colorVacioS = new Color( 200, 255, 255);
		colorValidoS = new Color( 100, 255, 100);
		colorInvalidoS = new Color( 255, 100, 100);
		colorVacio = new Color( 255, 255, 255);
		colorValido = new Color( 255, 255, 255);
		colorInvalido = new Color( 255, 0, 0);
	}
	
	public void ModificarCampo( JTextField campo)
	{
		if ( campo.hasFocus() )
		{
			if ( campo.getText().length() == 0 )
				campo.setBackground( colorVacioS);
			else if ( CIValida(campo.getText()))
				campo.setBackground( colorValidoS);
			else
				campo.setBackground( colorInvalidoS);
		}
		else
		{
			if ( campo.getText().length() == 0 )
				campo.setBackground( colorVacio);
			else if ( CIValida(campo.getText()))
				campo.setBackground( colorValido);
			else
				campo.setBackground( colorInvalido);
		}
	}
}
