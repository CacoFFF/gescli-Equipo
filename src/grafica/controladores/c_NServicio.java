package grafica.controladores;

import java.util.ArrayList;
import javax.swing.JComboBox;

import LogicaPersistencia.valueObject.VONServicio;

public class c_NServicio extends c_Maestro
{
	//Cache
	private int[] iIDs;
	private String[] sNombres;
	
	public void Agregar(String sNombre)
	{
		for ( int i=0 ; i<sNombres.length ; i++ ) 
			if ( sNombres[i].equals(sNombre) )
			{
				MensajeWin( "Entrada ya existente");
				return;
			}
		VONServicio voNS = gFachada.AgregarNServicio( sNombre);
		if ( voNS.getError().length() > 0 )
			MensajeWin(voNS.getError() );
		else
			MensajeWin(voNS.getResultado() );
	}
	
	public void Listar(JComboBox<String> cb)
	{
		cb.removeAllItems();
		ArrayList<String> rawList = gFachada.ListaNServicios();
		
		//Preparar cache
		iIDs = new int[rawList.size()+1];
		sNombres = new String[rawList.size()+1];
		
		//--Servicios-- (marcar como valor no utilizable)
		iIDs[0] = -1;
		sNombres[0] = "--Servicios--";
		
		for ( int i=0 ; i<rawList.size(); i++ )
		{
			String a = rawList.get(i);
			int pos = a.indexOf("@");
			if ( pos < 0 ) //BUG
				continue;
			iIDs[i+1] = Integer.parseInt(a.substring(0, pos));
			sNombres[i+1] = a.substring( pos+1);
		}

		for ( int i=0 ; i<sNombres.length ; i++ )
			cb.addItem( sNombres[i]);
	}
}
