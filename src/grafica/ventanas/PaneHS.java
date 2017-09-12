package grafica.ventanas;

import Main.Main;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class PaneHS extends JPanel {
	private JTextField textField;
	private JTextField txtNombreDeServicio;
	
	private JComboBox cbCliente;

	/**
	 * Create the panel.
	 */
	public PaneHS() {
		addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent arg0) {
				if (Main.gCon_Cliente != null)
					Main.gCon_Cliente.ListaClientes(cbCliente);
			}
		});
		setLayout(null);
		
		JComboBox cbServicio = new JComboBox();
		cbServicio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if ( cbServicio.getSelectedIndex() > 0 && cbServicio.getSelectedItem() != null )
					txtNombreDeServicio.setText( (String)cbServicio.getSelectedItem() );
			}
		});
		cbServicio.setBounds(10, 11, 170, 20);
		
		if ( Main.gCon_NServicio != null ) //Windowbuilder mode
			Main.gCon_NServicio.Listar(cbServicio);
		
		add(cbServicio);
		
		cbCliente = new JComboBox();
		cbCliente.setBounds(10, 42, 170, 20);
//		if (Main.gCon_Cliente != null)
//			Main.gCon_Cliente.ListaClientes(cbCliente);
		add(cbCliente);
		
		JComboBox cbFuncionario = new JComboBox();
		cbFuncionario.setBounds(10, 73, 170, 20);
		add(cbFuncionario);
		
		textField = new JTextField();
		textField.setBounds(94, 104, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblHoras = new JLabel("Horas:");
		lblHoras.setBounds(10, 104, 86, 20);
		add(lblHoras);
		
		cmpFecha cmpFecha_ = new cmpFecha();
		cmpFecha_.setBounds(10, 135, 170, 20);
		add(cmpFecha_);
		
		JButton btnAgregarHS = new JButton("Agregar");
		btnAgregarHS.setBounds(10, 179, 80, 23);
		add(btnAgregarHS);
		
		JButton btnQuitarHS = new JButton("Quitar");
		btnQuitarHS.setBounds(95, 179, 85, 23);
		add(btnQuitarHS);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 213, 170, 2);
		add(separator);
		
		txtNombreDeServicio = new JTextField();
		txtNombreDeServicio.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombreDeServicio.setText("Nombre de servicio");
		txtNombreDeServicio.setBounds(10, 226, 170, 20);
		add(txtNombreDeServicio);
		txtNombreDeServicio.setColumns(10);
		
		JButton btnAgregarS = new JButton("Agregar");
		btnAgregarS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ( Main.gCon_NServicio != null )
				{
					Main.gCon_NServicio.Agregar( txtNombreDeServicio.getText() );
					Main.gCon_NServicio.Listar(cbServicio);
					cbServicio.setSelectedIndex(cbServicio.getItemCount()-1);
				}
			}
		});
		btnAgregarS.setBounds(10, 271, 80, 23);
		add(btnAgregarS);
		
		JButton btnQuitarS = new JButton("Quitar");
		btnQuitarS.setBounds(95, 271, 85, 23);
		add(btnQuitarS);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(190, 11, 200, 283);
		add(scrollPane);
		
		JLabel lblListaDeHs = new JLabel("Lista de H/S aca");
		scrollPane.setViewportView(lblListaDeHs);

	}
}
