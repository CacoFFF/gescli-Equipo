package grafica.controladores;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import LogicaPersistencia.valueObject.VOCliente;
import grafica.controladores.c_Cliente.OpcVerificarCliente;


public class c_Cliente extends c_Maestro{
	
	
	//cachwe?
	private int[] iIdCli;
	private String[] sNumCli, sNomCli;
	public enum OpcVerificarCliente{ NumCliente, NomCliente, IDCliente; }
	public static OpcVerificarCliente opc;
		
	public void ListaClientes(JComboBox<String> cb){
		int iViejo = cb.getSelectedIndex();
		
		cb.removeAllItems();
		cb.addItem("--Cliente--");
		ArrayList<String> alCli=new ArrayList<String>();
		alCli=gFachada.ListaCli();
		
		if(alCli.isEmpty()) return;
		
		//Agrega al cachwe
		iIdCli=new int[alCli.size()];
		sNumCli=new String[alCli.size()];
		sNomCli=new String[alCli.size()];
		
		for (int i=0; i<alCli.size(); i++){
			iIdCli[i]=IntConvertidor(alCli.get(i).substring(0, (alCli.get(i).toString().indexOf("@"))));
			sNumCli[i]=Substring(alCli.get(i), "[", "]");
			sNomCli[i]=alCli.get(i).substring(alCli.get(i).toString().indexOf("-")+1).trim();
		}

		//Agrega a JComboBox
		for(String sCli : alCli)
			cb.addItem(sCli.substring(sCli.indexOf("@")+1)); //quita el IDCliente de la lista
		
		//Mantiene seleccionado?
		if ( cb.getItemCount() > iViejo )
			cb.setSelectedIndex( iViejo);
		
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
		String[] asMonedas={"Pesos uy", "dolares", "otros"};
		cb.addItem("");
		for(String str : asMonedas) cb.addItem(str);
	}
	
	public boolean BuscarCliente(JTextField tfRut, JTextField tfNumCli, JTextField tfTelefono,
			JTextField tfDireccion, JTextField tfNomCli, JTextField tfHoras, JTextField tfHonorarios,
			JComboBox<String> cbDepartamentos, JComboBox<String> cbMoneda, String sBuscador, boolean bConfirmarRellenado){
		/*bConfirmarRellenado, en principio, solo para confirmar si se desea rellenar los datos al encontrar cliente desde los TextFields 
		* ( -__-) es molesto escribir y que se te rellene solo todo */
		
		//Buscardor puede ser NomCli o NumCli
		
		boolean bIngreso=false; 
		int id=0; 
		String sNumCli="", sNomCli="";
		
		bIngreso = StringValido(sBuscador) ? true : false; //Verifica ingreso
		if(!bIngreso) return false;
				
		//Busca el ID en cachwe
		//busca por numCli(siendo NumCli solo numeros)
		if(IsNumeric(sBuscador)){ sNumCli=sBuscador;
		for(int i=0; i<this.sNumCli.length; i++){
			if(sNumCli.equals(this.sNumCli[i])){
				id=iIdCli[i];
				continue;}}}
		
		//busca por NomCli(siendo NomCli String no solo numerico... algo asi)
		if(!IsNumeric(sBuscador)){ sNomCli=sBuscador;
		for(int q=0; q<this.sNumCli.length; q++){
			if(sNomCli.equals(this.sNomCli[q])){
				id=iIdCli[q];
				sNumCli=this.sNumCli[q];
				continue;}}}
		
		if(id==0) return false;
		
		voCli=gFachada.ObtenerCliente(sNumCli, id);
		
		if ( voCli != null ){
			if ( voCli.getError().length() != 0 ) MensajeWin("Rellenar Cliente ERROR:\n"+voCli.getError());
			else{
				if(bConfirmarRellenado){
					if(ConfirmWin("Cliente existente! \nRellenar campos?")){
						LlenarCampos(voCli, tfRut, tfNumCli, tfTelefono, tfDireccion, tfNomCli, tfHoras, tfHonorarios,
								 cbDepartamentos, cbMoneda); 
					}else{return false;}
					
				}
				LlenarCampos(voCli, tfRut, tfNumCli, tfTelefono, tfDireccion, tfNomCli, tfHoras, tfHonorarios,
						 cbDepartamentos, cbMoneda); 
			}}
		return true;}
			
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
	
	public String VerificarCliente(String sBuscar, OpcVerificarCliente opc){
		String sResultado="";
		
		switch (opc){
		case NomCliente: 
			for (int i=0; i<sNomCli.length; i++){
				if(sBuscar.equals(sNumCli[i])){
					sResultado=sNomCli[i];
					return sResultado;
				}
			}
			break;
		case NumCliente: 
			for (int i = 0; i < sNumCli.length; i++) {
				if(sBuscar.equals(sNomCli[i])){
					sResultado=sNumCli[i];
					return sResultado;
				}
			}
			break;
		
		case IDCliente: 
			for (int i=0; i<sNumCli.length; i++){
				if(sBuscar.equals(sNumCli[i])){
					sResultado=""+iIdCli[i];
					return sResultado;
				}
			}
			break;
		}//switch
		return sResultado;
	}
	
	public void EliminarCliente(String sNumCli){
		//elimina cliente
		//buscar ID cliente
		int iID=IntConvertidor(VerificarCliente(sNumCli, opc.IDCliente));
		
		//crea VO
		voCli=new VOCliente(sNumCli, iID);
		
		MensajeWin(gFachada.EliminarCliente(voCli) ? voCli.getResultado() : voCli.getError() );
		
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
		
		if(VerificarDatos(sRut, sNumCli, sTelefono, sDireccion, sNomCli, sHoras, sHonorarios)){
			int iDepto=IDDepartamento(sDepto);
			
			//Opcion de modificacion
			if(bCliExistente){
				//seguir esto (como mantener el numCli viejo para modificar? T_T)
				//voCli=new VOCliente(iDepto, IntConvertidor(sHoras), IntConvertidor(sHonorarios), iMoneda, sRut, sNumCli, sTelefono, sDireccion, sNomCli, IntConvertidor(VerificarCliente(sNumCli, 3)), sNumCli);
			}
			
			//Opcion de agregar nuevo cliente
			else{
				voCli=new VOCliente(iDepto, IntConvertidor(sHoras), IntConvertidor(sHonorarios), iMoneda, sRut, sNumCli, sTelefono, sDireccion, sNomCli);
				MensajeWin(gFachada.AgregarCliente(voCli));
			}
			
			
		}
	}
}
