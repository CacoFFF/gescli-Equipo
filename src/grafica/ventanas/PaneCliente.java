package grafica.ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import LogicaPersistencia.valueObject.VOCliente;
import grafica.controladores.c_Cliente;
import grafica.controladores.c_Maestro;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;


public class PaneCliente extends JComponent {
	private c_Cliente ctrl;
	

	private JPanel pLista, pCampos;
	private JComboBox<String> cbLista, cbDepartamentos, cbMoneda/*, cbTipoPersona*/;
	private JButton btnVaciar, btnBorrar, btnGuardar;
	private JLabel /*txtContacto,*/ txtRut, txtNumCli, txtTelefono, txtDireccion, txtDepartamento, txtNomCli, txtHoras,
			txtHonorarios/*, txtTipoPersona*/;
	private JTextField /*tfContacto,*/ tfRut, tfNumCli, tfTelefono, tfDireccion, tfNomCli, tfHoras, tfHonorarios;
	
	private boolean bNoActualizarCampos;

	public void MostrarCliente( VOCliente oCL)
	{
		if ( bNoActualizarCampos )
			return;
		btnBorrar.setEnabled( oCL != null);
		if ( oCL == null )
		{
			tfNumCli.setText( "");
			tfTelefono.setText( "");
			tfRut.setText( "");
			tfDireccion.setText( "");
			tfNomCli.setText( "");
			tfHoras.setText( "");
			tfHonorarios.setText( "");
			cbDepartamentos.setSelectedIndex( 0);
			cbMoneda.setSelectedIndex( 0);
		}
		else
		{
			tfNumCli.setText( oCL.getsNroCli());
			tfTelefono.setText( oCL.getsTel());
			tfRut.setText( oCL.getsRut());
			tfDireccion.setText( oCL.getsDireccion());
			tfNomCli.setText( oCL.getsNomCli());
			tfHoras.setText( Integer.toString(oCL.getiHrCargables()));
			tfHonorarios.setText( Integer.toString(oCL.getiHonorarios()));
			cbDepartamentos.setSelectedIndex( oCL.getiIdDepto());
			cbMoneda.setSelectedIndex( oCL.getiMoneda());
		}
	}
	
	PaneCliente() {
		ctrl = new c_Cliente();

		pLista = new JPanel();
		pLista.setBounds(0, 0, fMain.fW, 40);
		pLista.setLayout(null);
		add(pLista);
		
		cbLista = new JComboBox<String>();
		cbLista.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {}
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0)
			{
				bNoActualizarCampos = true;
				ctrl.ListaClientes(cbLista);
				bNoActualizarCampos = false;
			}
		});
		cbLista.setBounds(10, 10,260, 25);
		cbLista.addItem("--Cliente--");
		cbLista.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e)
			{
				MostrarCliente( ctrl.get( cbLista.getSelectedIndex()-1 ));
			}
		});
		pLista.add(cbLista);
		//--------------------------
	
		pCampos=new JPanel();
		pCampos.setLayout(null);
		pCampos.setBounds(0,pLista.getHeight(),fMain.fW, fMain.fH-pLista.getHeight());
		add(pCampos);
		
		
		//Intentar agregar autocompletar en tfNum y tfNom
		txtNumCli = new JLabel("Nro. Cliente:");
		txtNumCli.setBounds(10, 5, 100, 20);
		pCampos.add(txtNumCli);

		tfNumCli = new JTextField();
		tfNumCli.setBounds(150, 5, 200, 20);
		pCampos.add(tfNumCli);

		txtNomCli = new JLabel("Nombre: ");
		txtNomCli.setBounds(10, 30, 100, 20);
		pCampos.add(txtNomCli);
		
		tfNomCli = new JTextField();
		tfNomCli.setBounds(150, 30, 200, 20);
		pCampos.add(tfNomCli);
		
		txtRut=new JLabel("Rut:");
		txtRut.setBounds(10,55,100,20);
		pCampos.add(txtRut);
		
		tfRut=new JTextField();
		tfRut.setBounds(150, 55, 200, 20);
		pCampos.add(tfRut);
		
		txtTelefono=new JLabel("Telefono:");
		txtTelefono.setBounds(10,80,100,20);
		pCampos.add(txtTelefono);
		
		tfTelefono=new JTextField();
		tfTelefono.setBounds(150, 80, 200, 20);
		pCampos.add(tfTelefono);
		
		txtDireccion=new JLabel("Direccion: ");
		txtDireccion.setBounds(10,105,100,20);
		pCampos.add(txtDireccion);
		
		tfDireccion=new JTextField();
		tfDireccion.setBounds(150, 105, 200, 20);
		pCampos.add(tfDireccion);
		
		txtDepartamento=new JLabel("Departamento:");
		txtDepartamento.setBounds(10,130,100,20);
		pCampos.add(txtDepartamento);
		
		cbDepartamentos=new JComboBox<String>();
		cbDepartamentos.setBounds(150, 130, 200, 20);
		ctrl.ListaDepto(cbDepartamentos);
		cbDepartamentos.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					btnGuardar.setEnabled( (cbMoneda.getSelectedIndex() > 0) && (cbDepartamentos.getSelectedIndex() > 0) );
				}}});
			pCampos.add(cbDepartamentos);
		
		txtHoras=new JLabel("Horas cargables:");
		txtHoras.setBounds(10,155,100,20);
		pCampos.add(txtHoras);
		
		tfHoras=new JTextField();
		tfHoras.setBounds(150, 155, 200, 20);
		pCampos.add(tfHoras);
		
		txtHonorarios=new JLabel("Honorarios");
		txtHonorarios.setBounds(10, 180, 100, 20);
		pCampos.add(txtHonorarios);
		
		tfHonorarios=new JTextField();
		tfHonorarios.setBounds(150,180,100,20);
		pCampos.add(tfHonorarios);
		
		cbMoneda=new JComboBox<String>();
		cbMoneda.setBounds(260,180,90,20);
		ctrl.ListaMonedas(cbMoneda);
		cbMoneda.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					btnGuardar.setEnabled( (cbMoneda.getSelectedIndex() > 0) && (cbDepartamentos.getSelectedIndex() > 0) );
				}}});
		
		pCampos.add(cbMoneda);
		
		btnGuardar=new JButton("Guardar");
		btnGuardar.setBounds(295, 265, 90, 25);
		btnGuardar.setEnabled(false);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//si cliente ya existe modificar en lugar de agregar
				ctrl.Guardar(tfRut.getText().trim(), tfNumCli.getText().trim(), 
						tfTelefono.getText().trim(), tfDireccion.getText().trim(), 
						tfNomCli.getText().trim(), tfHoras.getText().trim(), 
						tfHonorarios.getText().trim(), (String)cbDepartamentos.getSelectedItem(), cbMoneda.getSelectedIndex());
				//ctrl.VaciarCampos(cbDepartamentos, cbMoneda,tfRut,tfNumCli,tfTelefono,tfDireccion,tfNomCli,tfHoras,tfHonorarios);
				ctrl.ListaClientes(cbLista);
			}
		});
		pCampos.add(btnGuardar);
				
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(200, 265, 90, 25);
		btnBorrar.setEnabled(false);
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent w) {
				ctrl.EliminarCliente(tfNumCli.getText().trim());
				ctrl.VaciarCampos(cbDepartamentos, cbMoneda,tfRut,tfNumCli,tfTelefono,tfDireccion,tfNomCli,tfHoras,tfHonorarios);
				ctrl.ListaClientes(cbLista);
				btnBorrar.setEnabled(false);
				}
		});
		pCampos.add(btnBorrar);
		
		btnVaciar = new JButton("Vaciar");
		btnVaciar.setBounds(105, 265, 90, 25);
		btnVaciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.VaciarCampos(cbDepartamentos, cbMoneda,tfRut,tfNumCli,tfTelefono,tfDireccion,tfNomCli,tfHoras,tfHonorarios);
			}
		});
		pCampos.add(btnVaciar);
		
	

	}// contructor
}// class
