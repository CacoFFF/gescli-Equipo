package grafica.ventanas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Main.Main;
import grafica.controladores.Con_AltaEmpleado;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AltaFuncionario extends JFrame {

	private JPanel contentPane;
	private JLabel txtNombre;
	private JLabel txtApellido;
	private JLabel txtCi;
	private JLabel txtFechanac;
	private JLabel txtCel;
	private JTextField textoNombre;
	private JLabel txtHorasdia;
	private JTextField textoApellido;
	private JTextField textoCI;
	private JTextField textoFechaNac;
	private JTextField textoCel;
	private JTextField textoHorasDia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaFuncionario frame = new AltaFuncionario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AltaFuncionario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 263);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNombre = new JLabel();
		txtNombre.setText("Nombre :");
		txtNombre.setBounds(27, 10, 86, 20);
		contentPane.add(txtNombre);
		
		txtApellido = new JLabel();
		txtApellido.setText("Apellido :");
		txtApellido.setBounds(27, 40, 86, 20);
		contentPane.add(txtApellido);
		
		txtCi = new JLabel();
		txtCi.setText("Ci :");
		txtCi.setBounds(27, 70, 86, 20);
		contentPane.add(txtCi);
		
		txtFechanac = new JLabel();
		txtFechanac.setText("FechaNac :");
		txtFechanac.setBounds(27, 130, 86, 20);
		contentPane.add(txtFechanac);
		
		txtCel = new JLabel();
		txtCel.setText("Cel :");
		txtCel.setBounds(27, 100, 86, 20);
		contentPane.add(txtCel);
		
		textoNombre = new JTextField();
		textoNombre.setBounds(140, 10, 149, 20);
		contentPane.add(textoNombre);
		textoNombre.setColumns(10);
		
		txtHorasdia = new JLabel();
		txtHorasdia.setText("HorasDia :");
		txtHorasdia.setBounds(27, 160, 86, 20);
		contentPane.add(txtHorasdia);
		
		JLabel txtActivo = new JLabel("Activo:");
		txtActivo.setBounds(27, 190, 86, 20);
		contentPane.add(txtActivo);
		
		textoApellido = new JTextField();
		textoApellido.setBounds(140, 40, 149, 20);
		contentPane.add(textoApellido);
		textoApellido.setColumns(10);
		
		textoCI = new JTextField();
		textoCI.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				Main.gCon_CampoCI.ModificarCampo(textoCI);
			}
		});
		textoCI.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				Main.gCon_CampoCI.ModificarCampo(textoCI);
			}
		});
		textoCI.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent arg0) {
				Main.gCon_CampoCI.ModificarCampo(textoCI);
			}
			public void focusLost(FocusEvent e) {
				Main.gCon_CampoCI.ModificarCampo(textoCI);
			}
		});
		textoCI.setToolTipText("Agregar con puntos y guiones");
		textoCI.setBounds(140, 70, 149, 20);
		contentPane.add(textoCI);
		textoCI.setColumns(10);
		
		textoFechaNac = new JTextField();
		textoFechaNac.setToolTipText("Ingresar en formato 'aaaa-mm-dd'");
		textoFechaNac.setBounds(140, 130, 149, 20);
		contentPane.add(textoFechaNac);
		textoFechaNac.setColumns(10);
		
		textoCel = new JTextField();
		textoCel.setBounds(140, 100, 149, 20);
		contentPane.add(textoCel);
		textoCel.setColumns(10);
		
		textoHorasDia = new JTextField();
		textoHorasDia.setBounds(140, 160, 149, 20);
		contentPane.add(textoHorasDia);
		textoHorasDia.setColumns(10);
	
		JCheckBox chkActivo = new JCheckBox("");
		chkActivo.setBounds(140, 190, 97, 23);
//		chkActivo.setEnabled(false);//ver NotaDeIvan.txt
		contentPane.add(chkActivo);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setToolTipText("Da de alta un empleado, si la CI ya existe en la base de datos, los datos del empleado son actualizados. El empleado pasa a estar activo.");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.gCon_AltaEmpleado.AltaEmpleado //error NullPointer
//				Con_AltaEmpleado ctrlAlta=new Con_AltaEmpleado();
//				ctrlAlta.AltaEmpleado
				( textoNombre.getText().trim()
				, textoApellido.getText().trim()
				, textoCI.getText().trim()
				, textoFechaNac.getText().trim()
				, textoCel.getText().trim()
				, textoHorasDia.getText().trim()
				, chkActivo.isSelected() ); //Fin de funcion
			}
		});
		btnAgregar.setBounds(327, 10, 97, 23);
		contentPane.add(btnAgregar);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.gCon_BajaEmpleado.BajaEmpleado( textoCI.getText());
			}
		});
		btnActualizar.setToolTipText("El empleado con la CI especificada pasa a estar inactivo.");
		btnActualizar.setBounds(327, 45, 97, 23);
		contentPane.add(btnActualizar);
		
		JButton btnRellenar = new JButton("Rellenar");
		btnRellenar.setToolTipText("Rellena los campos del empleado que tenga la CI especificada");
		btnRellenar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main.gCon_RellenarEmpleado.RellenarEmpleado
				( textoCI.getText().trim()
				, textoNombre
				, textoApellido
				, textoFechaNac
				, textoCel
				, textoHorasDia
				, chkActivo );
			}
		});
		btnRellenar.setBounds(327, 80, 97, 23);
		contentPane.add(btnRellenar);
	}
}
