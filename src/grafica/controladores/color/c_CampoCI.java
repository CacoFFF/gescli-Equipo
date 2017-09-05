package grafica.controladores.color;

import javax.swing.JTextField;

import grafica.controladores.c_Maestro;

import java.awt.Color;

//Este controlador se encarga de proveer informacion en tiempo
//real al usuario con respecto a los campos de C.I
public class c_CampoCI extends c_Color
{
	public c_CampoCI()
	{
		super();
	}
	
	public void ModificarCampo( JTextField campo)
	{
		int i = campo.hasFocus() ? 1 : 0;

		if ( campo.getText().length() == 0 )
			campo.setBackground( colorVacio[i]);
		else if ( CIValida(campo.getText()))
			campo.setBackground( colorValido[i]);
		else
			campo.setBackground( colorInvalido[i]);
	}
}
