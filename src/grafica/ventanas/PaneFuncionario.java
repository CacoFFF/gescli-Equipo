package grafica.ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import LogicaPersistencia.valueObject.VOCliente;
import LogicaPersistencia.valueObject.VOEmpleado;
import Main.Main;
import grafica.controladores.c_Funcionario;

public class PaneFuncionario extends JComponent {
	private c_Funcionario ctrl;

	private JPanel pLista, pCampos;
	private JComboBox<String> cbLista;
	private JButton btnVaciar, btnGuardar, btnBaja,btnBuscar;
	private JLabel lblNomFun, lblCI, lblFecNac, lblCel, lblHorasDia;
	private JTextField tfNomFun, tfApeFun, tfCI, tfCel, tfHoras;
	private JCheckBox chckActivo;
	private cmpFecha cmpFecha;
	public static String sSeparadorFecha="-";
	
	private boolean bNoActualizarCampos;

	public void MostrarFuncionario( VOEmpleado oF)
	{
		if ( bNoActualizarCampos )
			return;
//		bCliExistente = (oCL != null);
		btnBaja.setEnabled( oF != null );
		if ( oF == null )
		{
			tfNomFun.setText("");
			tfApeFun.setText("");
			tfCI.setText("");
			tfCel.setText("");
			tfHoras.setText("");
			cmpFecha.resetValues();
			chckActivo.setSelected(false);
			btnBaja.setText("Baja");
		}
		else
		{
			tfNomFun.setText(oF.getNombre());
			tfApeFun.setText(oF.getApellido());
			tfCI.setText(oF.getCi());
			tfCel.setText(oF.getCel());
			tfHoras.setText( ""+oF.getHorasDia());
			cmpFecha.setText(oF.getFechaNac());
			chckActivo.setSelected(oF.getBaja());
			btnBaja.setText( oF.getBaja() ? "Dar Baja" : "Dar Alta");
		}
	}
	
	PaneFuncionario() {
		ctrl = new c_Funcionario();

		pLista = new JPanel();
		pLista.setBounds(0, 0, fMain.fW, 40);
		pLista.setLayout(null);
		add(pLista);

		cbLista = new JComboBox<String>();
		cbLista.setBounds(10, 10, 276, 25);
		cbLista.addItem("--Funcionarios--");
		cbLista.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {}
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0)
			{
				bNoActualizarCampos = true;
				ctrl.ListaFun(cbLista);
				bNoActualizarCampos = false;
			}
		});
		cbLista.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e)
			{
				MostrarFuncionario( ctrl.get( cbLista.getSelectedIndex()-1 ));
			}
		});
		pLista.add(cbLista);
		
		btnBuscar = new JButton("Buscar CI");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JTextField FN[] = cmpFecha.getDDMMAA(); 
				ctrl.BuscarCI(btnBaja, tfNomFun, tfApeFun, tfCI, FN[0], FN[1], FN[2], tfCel, tfHoras, chckActivo);
				ctrl.CtrlBtnBaja(btnBaja, chckActivo.isSelected());
			}
		});
		btnBuscar.setBounds(296, 10, 94, 25);
		pLista.add(btnBuscar);
		// ----------------------------------------------
		pCampos = new JPanel();
		pCampos.setBounds(0, pLista.getHeight(), fMain.fW, fMain.fH - pLista.getHeight());
		pCampos.setLayout(null);
		add(pCampos);

		lblNomFun = new JLabel("Nombre completo: ");
		lblNomFun.setBounds(10, 5, 100, 20);
		pCampos.add(lblNomFun);

		tfNomFun = new JTextField();
		tfNomFun.setToolTipText("Nombre");
		tfNomFun.setBounds(120, 5, 100, 20);
		pCampos.add(tfNomFun);

		tfApeFun = new JTextField();
		tfApeFun.setToolTipText("Apellido");
		tfApeFun.setBounds(225, 5, 155, 20);
		pCampos.add(tfApeFun);
		
		lblCI = new JLabel("Cedula:");
		lblCI.setBounds(10, 30, 100, 20);
		pCampos.add(lblCI);

		tfCI = new JTextField();
		tfCI.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				Main.gCon_CampoCI.ModificarCampo(tfCI);
			}
		});
		tfCI.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				Main.gCon_CampoCI.ModificarCampo(tfCI);
			}
		});
		tfCI.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent arg0) {
				Main.gCon_CampoCI.ModificarCampo(tfCI);
			}
			public void focusLost(FocusEvent e) {
				Main.gCon_CampoCI.ModificarCampo(tfCI);
			}
		});
		tfCI.setToolTipText("Cedula (ej: 5.555.555-5)");
		tfCI.setBounds(120, 30, 260, 20);
		pCampos.add(tfCI);

		lblFecNac = new JLabel("Fecha de nac.:");
		lblFecNac.setBounds(10, 55, 100, 20);
		pCampos.add(lblFecNac);
	
		lblCel = new JLabel("Celular: ");
		lblCel.setBounds(10, 80, 100, 20);
		pCampos.add(lblCel);

		tfCel = new JTextField();
		tfCel.setBounds(120, 80, 260, 20);
		pCampos.add(tfCel);

//		lblPass = new JLabel("Pass:");
//		lblPass.setBounds(200, 105, 50, 20);
//		pCampos.add(lblPass);
//
//		tfPass = new JTextField();
//		tfPass.setBounds(260, 105, 120, 20);
//		pCampos.add(tfPass);
//
//		lblIDGrupo = new JLabel("ID Grupo: ");
//		lblIDGrupo.setBounds(10, 105, 70, 20);
//		pCampos.add(lblIDGrupo);
//
//		tfIDGrupo = new JTextField();
//		tfIDGrupo.setBounds(120, 105, 35, 20);
//		pCampos.add(tfIDGrupo);

		lblHorasDia = new JLabel("Horas al dia:");
		lblHorasDia.setBounds(10, 130, 100, 20);
		pCampos.add(lblHorasDia);

		tfHoras = new JTextField();
		tfHoras.setBounds(120, 130, 130, 20);
		pCampos.add(tfHoras);
		
		chckActivo = new JCheckBox("Activo");
		chckActivo.setEnabled(false);
		chckActivo.setBounds(280, 129, 70, 23);
		pCampos.add(chckActivo);

		// btn finale
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(295, 265, 90, 25);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String	sNomFun=tfNomFun.getText().trim(),
						sApefun=tfApeFun.getText().trim(),
						sCI=tfCI.getText().trim(),
						sFecha=cmpFecha.getText().trim(),
						sCel=tfCel.getText().trim(),
						sHoras=tfHoras.getText().trim();
				boolean bActivo=chckActivo.isSelected();
				
				ctrl.Guardar(sNomFun, sApefun, sCI, sFecha, sCel, sHoras, bActivo);
				
				//actualizar cosas de la ventana
				ctrl.ListaFun(cbLista);//actualizar lista(ver esto)
				ctrl.CtrlBtnBaja(btnBaja, bActivo);

			}
		});
		pCampos.add(btnGuardar);

		/*mejorar esto (btnBaja toma el estado del chckActivo, y este es modificable desde la ventana(mal))*/
		btnBaja = new JButton("Baja");
		btnBaja.setBounds(200, 265, 90, 25);
		btnBaja.setEnabled(false);
		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!ctrl.Baja(tfCI.getText().trim(), chckActivo)){
					JTextField FN[] = cmpFecha.getDDMMAA();
					ctrl.VaciarCampos(cbLista, chckActivo, tfNomFun, tfApeFun, tfCI, tfCel,FN[0],FN[1],FN[2],tfHoras);
					btnBaja.setEnabled(false);				
				}else{
				ctrl.ListaFun(cbLista);
				ctrl.CtrlBtnBaja(btnBaja, chckActivo.isSelected());
				}

			}
		});
		pCampos.add(btnBaja);

		btnVaciar = new JButton("Vaciar");
		btnVaciar.setBounds(105, 265, 90, 25);
		btnVaciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField FN[] = cmpFecha.getDDMMAA();
				ctrl.VaciarCampos(cbLista, chckActivo, tfNomFun, tfApeFun, tfCI, tfCel,FN[0],FN[1],FN[2],tfHoras);
			}});
		pCampos.add(btnVaciar);
		
		cmpFecha = new cmpFecha();
		cmpFecha.setBounds(120, 55, 260, 20);
		pCampos.add(cmpFecha);

	}
}// class
