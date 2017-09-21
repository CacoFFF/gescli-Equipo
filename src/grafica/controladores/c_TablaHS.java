package grafica.controladores;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import LogicaPersistencia.valueObject.VOCliente;
import LogicaPersistencia.valueObject.VOHorario;

public abstract class c_TablaHS extends c_Maestro
{
	private static VOHorario cache[];
	
	public static VOHorario get( int i)
	{
		if ( (cache != null) && (i >= 0) && (i < cache.length) )
			return cache[i];
		return null;
	}
	
	public static int ContarPaginas( JTable table, int sqlMode, String sqlParams[])
	{
		return 1 + (gFachada.ContarHorarios( sqlMode, sqlParams)-1) / table.getRowCount();
	}
	
	public static void MostrarPagina( JTable table, int pag, int sqlMode, String sqlParams[])
	{
		cache = gFachada.ListarHorarios( sqlMode, sqlParams, pag, table.getRowCount() );
		int i = 0;
		TableModel model = table.getModel();
		for ( ; i<cache.length ; i++ )
		{
			VOHorario oHS = cache[i];
			model.setValueAt( oHS.getsFecha()             , i, 0);
			model.setValueAt( oHS.getsNomServicio()       , i, 1);
			model.setValueAt( oHS.getsCIFuncionario()     , i, 2);
			model.setValueAt( oHS.getsNumCliente()        , i, 3);
			model.setValueAt( new Integer(oHS.getiHoras()), i, 4);
		}
		for ( ; i<table.getRowCount(); i++ )
			for ( int j=0 ; j<5 ; j++ )
				model.setValueAt( null, i, j);
	}
	
	//Borra un elemento del cache
	public static void BorrarElemento( int i)
	{
		VOHorario vH = get(i);
		if ( vH != null )
			gFachada.BorrarHorario( vH);
	}
}
