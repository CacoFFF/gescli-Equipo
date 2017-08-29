package grafica.ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import grafica.controladores.c_Funcionario;

public class PaneFuncionario extends JComponent {
	private c_Funcionario ctrl;

	private JPanel pLista, pCampos;
	private JComboBox<String> cbLista;
	private JButton btnVer, btnVaciar, btnGuardar, btnBaja,btnBuscar;
	private JLabel lblNomFun, lblCI, lblFecNac, lblCel, lblHorasDia;
	private JTextField tfNomFun, tfApefun, tfCI, tfFecNac1, tfFecNac2, tfFecNac3, tfCel, tfHoras;
	private JCheckBox chckActivo;
	public static String sSeparadorFecha="-";

	PaneFuncionario() {
		ctrl = new c_Funcionario();

		pLista = new JPanel();
		pLista.setBounds(0, 0, fMain.fW, 40);
		pLista.setLayout(null);
		add(pLista);

		cbLista = new JComboBox<String>();
		cbLista.setBounds(10, 10, 215, 25);
		ctrl.ListaFun(cbLista); // Agrega elementos a la lista
		cbLista.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String sSeleccion = e.getItem().toString();
					if(sSeleccion.isEmpty())btnBaja.setEnabled(false);
					btnVer.setEnabled(sSeleccion.isEmpty() ? false : true);
					
				}

			}
		});
		pLista.add(cbLista);

		btnVer = new JButton("Ver");
		btnVer.setBounds(235, 10, 70, 25);
		btnVer.setEnabled(false);
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//seguir aca
				String tmp=cbLista.getSelectedItem().toString();
				int iInicio=tmp.indexOf("["), iFin=tmp.indexOf("]");
				String sCI=tmp.substring(iInicio+1,iFin);
				ctrl.BuscarCI(btnBaja, tfNomFun, tfApefun, tfCI, tfFecNac1, tfFecNac2, tfFecNac3, tfCel, tfHoras, chckActivo, sCI);
				ctrl.CtrlBtnBaja(btnBaja, chckActivo.isSelected());
			}
		});
		pLista.add(btnVer);
		
		btnBuscar = new JButton("Buscar CI");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrl.BuscarCI(btnBaja, tfNomFun, tfApefun, tfCI, tfFecNac1, tfFecNac2, tfFecNac3, tfCel, tfHoras, chckActivo);
				ctrl.CtrlBtnBaja(btnBaja, chckActivo.isSelected());
			}
		});
		btnBuscar.setBounds(310, 10, 80, 25);
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

		tfApefun = new JTextField();
		tfApefun.setToolTipText("Apellido");
		tfApefun.setBounds(225, 5, 155, 20);
		pCampos.add(tfApefun);

		lblCI = new JLabel("Cedula:");
		lblCI.setBounds(10, 30, 100, 20);
		pCampos.add(lblCI);

		tfCI = new JTextField();
		tfCI.setToolTipText("Cedula (ej: 5.555.555-5)");
		tfCI.setBounds(120, 30, 260, 20);
		pCampos.add(tfCI);

		lblFecNac = new JLabel("Fecha de nac.:");
		lblFecNac.setBounds(10, 55, 100, 20);
		pCampos.add(lblFecNac);

		tfFecNac1 = new JTextField();
		tfFecNac1.setHorizontalAlignment(SwingConstants.CENTER);
		tfFecNac1.setToolTipText("dia");
		tfFecNac1.setBounds(120, 55, 70, 20);
		pCampos.add(tfFecNac1);

		tfFecNac2 = new JTextField();
		tfFecNac2.setHorizontalAlignment(SwingConstants.CENTER);
		tfFecNac2.setToolTipText("Mes");
		tfFecNac2.setBounds(200, 55, 70, 20);
		pCampos.add(tfFecNac2);

		tfFecNac3 = new JTextField();
		tfFecNac3.setHorizontalAlignment(SwingConstants.CENTER);
		tfFecNac3.setToolTipText("A\u00F1o");
		tfFecNac3.setBounds(280, 55, 100, 20);
		pCampos.add(tfFecNac3);

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
						sApefun=tfApefun.getText().trim(),
						sCI=tfCI.getText().trim(),
						sFecha=tfFecNac3.getText().trim()+sSeparadorFecha+tfFecNac2.getText().trim()+sSeparadorFecha+tfFecNac1.getText().trim(),
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
		btnBaja = new JButton("");
		btnBaja.setBounds(200, 265, 90, 25);
		btnBaja.setEnabled(false);
		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!ctrl.Baja(tfCI.getText().trim(), chckActivo)){
					ctrl.VaciarCampos(cbLista, chckActivo, tfNomFun, tfApefun, tfCI, tfCel,tfFecNac1,tfFecNac2,tfFecNac3,tfHoras);
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
				ctrl.VaciarCampos(cbLista, chckActivo, tfNomFun, tfApefun, tfCI, tfCel,tfFecNac1,tfFecNac2,tfFecNac3,tfHoras);
			}});
		pCampos.add(btnVaciar);

	}
}// class
