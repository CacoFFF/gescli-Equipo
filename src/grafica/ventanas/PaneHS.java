package grafica.ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Main.Main;
import grafica.controladores.c_Maestro;
import grafica.controladores.c_PanelHS;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PaneHS extends JPanel {
	private JTextField tfHoras;
	private JTextField txtNombreDeServicio;
	private cmpFecha cmpFecha;
	private JButton btnAgregarHS;
	
	private JComboBox<String> cbServicio;
	private JComboBox<String> cbCliente;
	private JComboBox<String> cbFuncionario;
	private JComboBox<String> combos[];
	
	private c_Maestro ctrlMaestro;
	
//	private boolean bServicios, bFuncionario=false, bCliente=false; //ver cuando habilitador de btn funcione
	
	

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("unchecked")
	public PaneHS() {
		addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent arg0) {
				if (Main.gCon_Cliente != null)
					Main.gCon_Cliente.ListaClientes(cbCliente);
				
				if (Main.gCon_Funcionario != null)
					Main.gCon_Funcionario.ListaFun(cbFuncionario);
			}
		});
		setLayout(null);
		
		cbServicio = new JComboBox<String>();
		cbServicio.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				c_PanelHS.ActivarBoton( btnAgregarHS, combos, cmpFecha, tfHoras);
				if(cbServicio.isFocusOwner()) System.out.println(cbServicio.getSelectedItem());


			}
		});
		cbServicio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if ( cbServicio.getSelectedIndex() > 0 && cbServicio.getSelectedItem() != null ){
					txtNombreDeServicio.setText( (String)cbServicio.getSelectedItem());
					
//					bServicios=cbServicio.getSelectedItem().toString().startsWith("--") ? false : true;	
//					btnAgregarHS.setEnabled(bServicios && bFuncionario && bCliente);

				}
				
				
			}
		});
		cbServicio.setBounds(10, 10, 170, 20);
		
		if ( Main.gCon_NServicio != null ) //Windowbuilder mode
			Main.gCon_NServicio.Listar(cbServicio);
		
		add(cbServicio);
		
		cbCliente = new JComboBox<String>();
		cbCliente.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				c_PanelHS.ActivarBoton( btnAgregarHS, combos, cmpFecha, tfHoras);
			}
		});
		cbCliente.setBounds(10, 40, 170, 20);
//		if (Main.gCon_Cliente != null)
//			Main.gCon_Cliente.ListaClientes(cbCliente);
		add(cbCliente);
		
		cbFuncionario = new JComboBox<String>();
		cbFuncionario.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				c_PanelHS.ActivarBoton( btnAgregarHS, combos, cmpFecha, tfHoras);

			}
		});
		cbFuncionario.setBounds(10, 70, 170, 20);
//		if (Main.gCon_Funcionario != null)
//		Main.gCon_Funcionario.ListaFun(cbFuncionario);
		add(cbFuncionario);
		
		//Conveniencia
		combos = new JComboBox[3];
		combos[0] = cbServicio;
		combos[1] = cbCliente;
		combos[2] = cbFuncionario;
		
		tfHoras = new JTextField();
		tfHoras.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				c_PanelHS.ActivarBoton( btnAgregarHS, combos, cmpFecha, tfHoras);
			}
		});
		tfHoras.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				c_PanelHS.ActivarBoton( btnAgregarHS, combos, cmpFecha, tfHoras);
			}
		});
		tfHoras.setBounds(95, 105, 85, 20);
		add(tfHoras);
		tfHoras.setColumns(10);
		
		JLabel lblHoras = new JLabel("Horas:");
		lblHoras.setBounds(10, 105, 85, 20);
		add(lblHoras);
		
		cmpFecha = new cmpFecha();
		for ( int i=0 ; i<3 ; i++ )
		{
			JTextField tf = cmpFecha.getDDMMAA()[i];
			tf.addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent arg0) {
					c_PanelHS.ActivarBoton( btnAgregarHS, combos, cmpFecha, tfHoras);
				}
			});
			tf.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					c_PanelHS.ActivarBoton( btnAgregarHS, combos, cmpFecha, tfHoras);
				}
			});
		}
		cmpFecha.setBounds(10, 135, 170, 20);
		add(cmpFecha);
		
		btnAgregarHS = new JButton("Agregar");
		btnAgregarHS.setBounds(10, 180, 80, 23);
		btnAgregarHS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrlMaestro=new c_Maestro();
				
				String sServicio=cbServicio.getSelectedItem().toString();
				
				
				String sNumCliente;
				sNumCliente=cbCliente.getSelectedIndex() >- 1 ? (String) cbCliente.getSelectedItem() : "--";
				if(!sNumCliente.startsWith("--")) sNumCliente=ctrlMaestro.Substring(sNumCliente, "[", "]");
				
				String sCIFuncionario;
				sCIFuncionario=cbFuncionario.getSelectedIndex() >- 1 ? (String) cbFuncionario.getSelectedItem() : "--";
				if(!sCIFuncionario.startsWith("--")) sCIFuncionario=ctrlMaestro.Substring(sCIFuncionario, "[", "]");

				Main.gCon_Horarios.Agregar(
						sServicio, 
						sNumCliente, 
						sCIFuncionario,
						tfHoras.getText().trim(), 
						cmpFecha.getText().trim());
			}
		});
		add(btnAgregarHS);
		
		JButton btnQuitarHS = new JButton("Quitar");
		btnQuitarHS.setBounds(95, 180, 85, 23);
		add(btnQuitarHS);
		
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 215, 170, 2);
		add(separator);
		
		//Agregar Servicios				
		txtNombreDeServicio = new JTextField();

		txtNombreDeServicio.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombreDeServicio.setText("Nombre de servicio");
		txtNombreDeServicio.setBounds(10, 225, 170, 20);
		txtNombreDeServicio.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if(txtNombreDeServicio.getText().equals("Nombre de servicio"))
				txtNombreDeServicio.setText("");
				}});		
		add(txtNombreDeServicio);
		txtNombreDeServicio.setColumns(10);
		
		JButton btnAgregarS = new JButton("Agregar");
		btnAgregarS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ( Main.gCon_NServicio != null )
				{
					if(Main.gCon_NServicio.Agregar( txtNombreDeServicio.getText() )){
						Main.gCon_NServicio.Listar(cbServicio);
						cbServicio.setSelectedIndex(cbServicio.getItemCount()-1);
					}					
				}
				
			}});
		btnAgregarS.setBounds(10, 270, 80, 25);
		add(btnAgregarS);
		
		JButton btnQuitarS = new JButton("Quitar");
		btnQuitarS.setBounds(95, 271, 85, 25);
		add(btnQuitarS);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(190, 10, 200, 285);
		add(scrollPane);
		
		JLabel lblListaDeHs = new JLabel("Lista de H/S aca");
		scrollPane.setViewportView(lblListaDeHs);

	}
}
