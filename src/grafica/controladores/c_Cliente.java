package grafica.controladores;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import LogicaPersistencia.valueObject.VOCliente;

public class c_Cliente extends c_Maestro{
	//cache?
	private VOCliente cache[];
	
	public VOCliente get( int i)
	{
		if ( (cache != null) && (i >= 0) && (i < cache.length) )
			return cache[i];
		return null;
	}
	
	public void ListaClientes(JComboBox<String> cb)	{

		int iViejo = cb.getSelectedIndex();
		VOCliente oViejo = get( iViejo-1);
		
		cb.removeAllItems();
		cb.addItem("--Cliente--");
		cache = gFachada.ListaCli();

		for ( int i=0 ; i<cache.length ; i++ )
			cb.addItem( "[" + cache[i].getsNroCli() + "] " + cache[i].getsNomCli() );
		
		//Re-seleccionar el elemento previamente seleccionado
		VOCliente oNuevo = get( iViejo-1);
		if ( (oNuevo != null) && (oViejo != null) && (oNuevo.getiIdCli() == oViejo.getiIdCli()) )
			cb.setSelectedIndex( iViejo);
		else if ( oViejo != null )
		{
			for ( int i=0 ; i<cache.length ; i++ )
				if ( cache[i].getiIdCli() == oViejo.getiIdCli()  )
				{
					cb.setSelectedIndex(i+1);
					break;
				}
		}
	}
	
	public void ListaDepto(JComboBox<String> cb){
		ArrayList<String> llDeptos=new ArrayList<String>();
		cb.addItem(""); //deshabilita btnGuardar, evita algun error
		llDeptos=gFachada.ListaDeptos();
		for(String sDepto: llDeptos){
			cb.addItem(sDepto);
		}
	}
	public void ListaMonedas(JComboBox<String> cb){
		//agregar tipos de moneda? o JTextField?
		String[] asMonedas={"Pesos uy", "Dolares", "Otros"};
		cb.addItem("");
		for(String str : asMonedas) cb.addItem(str);
	}
	
	public VOCliente BuscarCache_Num( String numCli)
	{
		if ( cache != null && IsNumeric(numCli) )
			for ( int i=0 ; i<cache.length ; i++ )
				if ( cache[i].getsNroCli().equals(numCli) )
					return cache[i];
		return null;
	}
	
	public VOCliente BuscarCache_Nombre( String nomCli)
	{
		if ( cache != null )
			for ( int i=0 ; i<cache.length ; i++ )
				if ( cache[i].getsNomCli().equals(nomCli) )
					return cache[i];
		return null;
	}
	
	public boolean BuscarCliente(JTextField tfRut, JTextField tfNumCli, JTextField tfTelefono,
			JTextField tfDireccion, JTextField tfNomCli, JTextField tfHoras, JTextField tfHonorarios,
			JComboBox<String> cbDepartamentos, JComboBox<String> cbMoneda, String sBuscador, boolean bConfirmarRellenado){
		/*bConfirmarRellenado, en principio, solo para confirmar si se desea rellenar los datos al encontrar cliente desde los TextFields 
		* ( -__-) es molesto escribir y que se te rellene solo todo */
		
		//Buscardor puede ser NomCli o NumCli
		if( !StringValido(sBuscador) )
			return false;
		VOCliente voCli = null;

		if ( voCli == null )	voCli = BuscarCache_Num( sBuscador);
		if ( voCli == null )	voCli = BuscarCache_Nombre( sBuscador);
		
		if ( voCli == null )
			return false;

		if ( voCli.getError().length() != 0 ) //Los valores cacheados nunca tienen error... pero por las dudas
			MensajeWin("Rellenar Cliente ERROR:\n"+voCli.getError());
		else
		{
			if( bConfirmarRellenado && !ConfirmWin("Cliente existente! \nRellenar campos?") )
				return false;
			LlenarCampos(voCli,
						tfRut,
						tfNumCli,
						tfTelefono,
						tfDireccion,
						tfNomCli,
						tfHoras,
						tfHonorarios,
						cbDepartamentos,
						cbMoneda); 
			System.out.println(voCli.getsNroCli()+" - "+voCli.getsNomCli());
		}
		return true;
	}
			
	public void LlenarCampos(VOCliente voCli, JTextField tfRut, JTextField tfNumCli, JTextField tfTelefono,
			JTextField tfDireccion, JTextField tfNomCli, JTextField tfHoras, JTextField tfHonorarios,
			JComboBox<String> cbDepartamentos, JComboBox<String> cbMoneda) {
		
		tfRut.setText  ( voCli.getsRut() );
		tfNumCli.setText( voCli.getsNroCli() );
		tfTelefono.setText(voCli.getsTel());
		tfDireccion.setText ( voCli.getsDireccion());
		tfNomCli.setText( voCli.getsNomCli() );
		tfHoras.setText(""+voCli.getiHrCargables());//jejeje
		tfHonorarios.setText(""+voCli.getiHonorarios());
		cbDepartamentos.setSelectedIndex(voCli.getiIdDepto());
		cbMoneda.setSelectedIndex(voCli.getiMoneda());
		
		
		if ( voCli.getResultado().length() != 0 ) MensajeWin(voCli.getResultado());
		}
	public void VaciarCampos(JComboBox<String> cbDeps, JComboBox<String> cbMon, JTextField...textField){
		cbDeps.setSelectedIndex(0);
		cbMon.setSelectedIndex(0);
		for(JTextField tf : textField) tf.setText("");
		
	}
	
	private int IDDepartamento(String sDepartamento){
		VOCliente voCli=new VOCliente(sDepartamento);
		gFachada.getIDDepartamento(voCli);
		return voCli.getiIdDepto();
	}

	public void EliminarCliente(String sNumCli){
		VOCliente voCli = BuscarCache_Num( sNumCli);
		if ( voCli == null ) //Esto ocurre?
		{
			//No esta cacheado
			//Buscar en base de datos y procesar a lo antiguo
//			voCli = new VOCliente( )
		}
		MensajeWin( gFachada.EliminarCliente(voCli) ? voCli.getResultado() : voCli.getError() );
	}
	
	private boolean VerificarDatos(String sRut, String sNumCli, String sTelefono, String sDireccion, String sNomCli, String sHoras, String sHonorarios){
		boolean bRut=true, bNumCli=true, bTelefono=true, bDireccion=true, bNomCli=true, bHoras=true, bHonorarios=true;
		String sErrMensaje="Error en:";
		
		if(!StringValido(sRut) || !IsNumeric(sRut)){sErrMensaje+="\n-Rut";bRut=false;}
		if(!StringValido(sNumCli) || !IsNumeric(sNumCli)){sErrMensaje+="\n-Numero cliente";bNumCli=false;}
		if(!StringValido(sTelefono) || !IsNumeric(sTelefono)){sErrMensaje+="\n-Telefono";bTelefono=false;}
		if(!StringValido(sDireccion)){sErrMensaje+="\n-Direccion"; bDireccion=false;}
		if(!StringValido(sNomCli)){sErrMensaje+="\n-Nombre cliente"; bNomCli=false;}
		if(!StringValido(sHoras) || !IsNumeric(sHoras)){sErrMensaje+="\n-Horas";bHoras=false;}
		if(!StringValido(sHonorarios) || !IsNumeric(sHonorarios)){sErrMensaje+="\n-Honorarios";bHonorarios=false;}		
		
		if(bRut && bNumCli && bTelefono && bDireccion && bNomCli && bHoras && bHonorarios){
			if(ConfirmWin("Guardar datos?")){return true;}
			else{MensajeWin("Accion cancelada"); return false;}
			
		}
		
		MensajeWin(sErrMensaje);
		
		return false;
	}
	public void Guardar(String sRut, String sNumCli, String sTelefono, String sDireccion, String sNomCli, String sHoras, String sHonorarios, String sDepto, int iMoneda, boolean bCliExistente){
		
		System.out.println(bCliExistente);
		
		if(VerificarDatos(sRut, sNumCli, sTelefono, sDireccion, sNomCli, sHoras, sHonorarios)){
			int iDepto=IDDepartamento(sDepto);
			
			//Opcion de modificacion
			if(bCliExistente){
				//seguir esto (como mantener el numCli viejo para modificar? T_T)
				//voCli=new VOCliente(iDepto, IntConvertidor(sHoras), IntConvertidor(sHonorarios), iMoneda, sRut, sNumCli, sTelefono, sDireccion, sNomCli, IntConvertidor(VerificarCliente(sNumCli, 3)), sNumCli);
			}
			
			//Opcion de agregar nuevo cliente
			else{
				VOCliente voCli = new VOCliente(iDepto, IntConvertidor(sHoras), IntConvertidor(sHonorarios), iMoneda, sRut, sNumCli, sTelefono, sDireccion, sNomCli);
				MensajeWin(gFachada.AgregarCliente(voCli));
			}
			
			
		}
	}
}
