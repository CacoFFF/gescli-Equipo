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
import grafica.controladores.*;
import LogicaPersistencia.valueObject.*;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
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
	private boolean bCampoServicioClick;
	

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
			}
		});
		cbServicio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if ( cbServicio.getSelectedIndex() > 0 && cbServicio.getSelectedItem() != null ){
					txtNombreDeServicio.setText( (String)cbServicio.getSelectedItem());
				}
				
				
			}
		});
		cbServicio.setBounds(10, 10, 200, 20);
		
		if ( Main.gCon_NServicio != null ) //Windowbuilder mode
			Main.gCon_NServicio.Listar(cbServicio);
		
		add(cbServicio);
		
		cbCliente = new JComboBox<String>();
		cbCliente.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				c_PanelHS.ActivarBoton( btnAgregarHS, combos, cmpFecha, tfHoras);
			}
		});
		cbCliente.setBounds(10, 40, 200, 20);
//		if (Main.gCon_Cliente != null)
//			Main.gCon_Cliente.ListaClientes(cbCliente);
		add(cbCliente);
		
		cbFuncionario = new JComboBox<String>();
		cbFuncionario.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				c_PanelHS.ActivarBoton( btnAgregarHS, combos, cmpFecha, tfHoras);

			}
		});
		cbFuncionario.setBounds(10, 70, 200, 20);
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
		tfHoras.setBounds(305, 11, 85, 20);
		add(tfHoras);
		tfHoras.setColumns(10);
		
		JLabel lblHoras = new JLabel("Horas:");
		lblHoras.setBounds(220, 11, 85, 20);
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
		cmpFecha.setBounds(220, 41, 170, 20);
		add(cmpFecha);
		
		btnAgregarHS = new JButton("Agregar");
		btnAgregarHS.setBounds(220, 70, 170, 25);
		btnAgregarHS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sServicio=cbServicio.getSelectedItem().toString();
				
				String sNumCliente;
				sNumCliente=cbCliente.getSelectedIndex() >- 1 ? (String) cbCliente.getSelectedItem() : "--";
				if(!sNumCliente.startsWith("--")) sNumCliente=c_Maestro.Substring(sNumCliente, "[", "]");
				
				String sCIFuncionario;
				sCIFuncionario=cbFuncionario.getSelectedIndex() >- 1 ? (String) cbFuncionario.getSelectedItem() : "--";
				if(!sCIFuncionario.startsWith("--")) sCIFuncionario=c_Maestro.Substring(sCIFuncionario, "[", "]");
				
				Main.gCon_Horarios.Agregar(
						sServicio, 
						sNumCliente, 
						sCIFuncionario,
						tfHoras.getText().trim(), 
						cmpFecha.getText().trim());
			}
		});
		add(btnAgregarHS);
		
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 215, 380, 2);
		add(separator);
		
		//Agregar Servicios				
		txtNombreDeServicio = new JTextField();

		txtNombreDeServicio.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombreDeServicio.setText("Nombre de servicio");
		txtNombreDeServicio.setBounds(10, 225, 170, 20);
		txtNombreDeServicio.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if(txtNombreDeServicio.getText().equals("Nombre de servicio") && !bCampoServicioClick )
					txtNombreDeServicio.setText("");
				bCampoServicioClick = true;
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
		btnAgregarS.setBounds(220, 225, 80, 25);
		add(btnAgregarS);
		
		JButton btnQuitarS = new JButton("Quitar");
		btnQuitarS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( Main.gCon_NServicio != null )
				{
					if( Main.gCon_NServicio.Borrar( txtNombreDeServicio.getText() )){
						Main.gCon_NServicio.Listar(cbServicio);
						cbServicio.setSelectedIndex(0);
					}		
				}
			}
		});
		btnQuitarS.setBounds(305, 225, 85, 25);
		add(btnQuitarS);
		
		JButton btnBTodos = new JButton("Buscar Todos");
		btnBTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new fHS( 0, null).setVisible( true);
			}
		});
		btnBTodos.setBounds(10, 100, 180, 25);
		add(btnBTodos);
		
		JButton btnBElementos = new JButton("Buscar por Elementos");
		btnBElementos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<String> sqlParams = new ArrayList<String>();
				int mode = 0;
				VOEmpleado vF = Main.gCon_Funcionario.get(cbFuncionario.getSelectedIndex()-1);
				VOCliente vC = Main.gCon_Cliente.get(cbCliente.getSelectedIndex()-1);
				if ( vF != null )
				{
					mode |= 0b0001;
					sqlParams.add( Integer.toString( vF.getId()) );
				}
				if ( vC != null )
				{
					mode |= 0b0010;
					sqlParams.add( Integer.toString( vC.getiIdCli()) );
				}
				if ( cbServicio.getSelectedIndex() != 0 )
				{
					mode |= 0b0100;
					sqlParams.add( (String)cbServicio.getSelectedItem() );
				}
				String params[] = new String[sqlParams.size()];
				sqlParams.toArray( params);
				new fHS( mode, params).setVisible( true);
			}
		});
		btnBElementos.setBounds(10, 135, 180, 25);
		add(btnBElementos);
		
		JButton btnBFecha = new JButton("Buscar por Fecha");
		btnBFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( cmpFecha.fechaValida() )
				{
					String params[] = { cmpFecha.getText() };
					new fHS( 0b1000, params).setVisible( true);
				}
			}
		});
		btnBFecha.setBounds(10, 170, 180, 25);
		add(btnBFecha);

	}
}
