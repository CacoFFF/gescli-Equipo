package grafica.ventanas;

import Main.Main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AltaFuncionario extends JFrame {

	private JPanel contentPane;
	private JLabel txtNombre;
	private JLabel txtApellido;
	private JLabel txtCi;
	private JLabel txtTelefono;
	private JLabel txtFechanac;
	private JLabel txtCel;
	private JTextField textoNombre;
	private JLabel txtHorasdia;
	private JTextField textoApellido;
	private JTextField textoCI;
	private JTextField textoTelefono;
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
		
		txtTelefono = new JLabel();
		txtTelefono.setText("Telefono :");
		txtTelefono.setBounds(27, 100, 86, 20);
		contentPane.add(txtTelefono);
		
		txtFechanac = new JLabel();
		txtFechanac.setText("FechaNac :");
		txtFechanac.setBounds(27, 130, 86, 20);
		contentPane.add(txtFechanac);
		
		txtCel = new JLabel();
		txtCel.setText("Cel :");
		txtCel.setBounds(27, 160, 86, 20);
		contentPane.add(txtCel);
		
		textoNombre = new JTextField();
		textoNombre.setBounds(140, 10, 149, 20);
		contentPane.add(textoNombre);
		textoNombre.setColumns(10);
		
		txtHorasdia = new JLabel();
		txtHorasdia.setText("HorasDia :");
		txtHorasdia.setBounds(27, 190, 86, 20);
		contentPane.add(txtHorasdia);
		
		textoApellido = new JTextField();
		textoApellido.setBounds(140, 40, 149, 20);
		contentPane.add(textoApellido);
		textoApellido.setColumns(10);
		
		textoCI = new JTextField();
		textoCI.setToolTipText("Agregar con puntos y guiones");
		textoCI.setBounds(140, 70, 149, 20);
		contentPane.add(textoCI);
		textoCI.setColumns(10);
		
		textoTelefono = new JTextField();
		textoTelefono.setBounds(140, 100, 149, 20);
		contentPane.add(textoTelefono);
		textoTelefono.setColumns(10);
		
		textoFechaNac = new JTextField();
		textoFechaNac.setToolTipText("Ingresar en formato 'aaaa-mm-dd'");
		textoFechaNac.setBounds(140, 130, 149, 20);
		contentPane.add(textoFechaNac);
		textoFechaNac.setColumns(10);
		
		textoCel = new JTextField();
		textoCel.setBounds(140, 160, 149, 20);
		contentPane.add(textoCel);
		textoCel.setColumns(10);
		
		textoHorasDia = new JTextField();
		textoHorasDia.setBounds(140, 190, 149, 20);
		contentPane.add(textoHorasDia);
		textoHorasDia.setColumns(10);
		
		JButton btnAlta = new JButton("Alta");
		btnAlta.setToolTipText("Da de alta un empleado, si la CI ya existe en la base de datos, los datos del empleado son actualizados. El empleado pasa a estar activo.");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.Con_AltaEmpleado.AltaEmpleado
				( textoNombre.getText()
				, textoApellido.getText()
				, textoCI.getText()
				, textoTelefono.getText()
				, textoFechaNac.getText()
				, textoCel.getText()
				, textoHorasDia.getText()); //Fin de funcion
			}
		});
		btnAlta.setBounds(327, 10, 97, 23);
		contentPane.add(btnAlta);
		
		JButton btnBaja = new JButton("Baja");
		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.Con_BajaEmpleado.BajaEmpleado( textoCI.getText());
			}
		});
		btnBaja.setToolTipText("El empleado con la CI especificada pasa a estar inactivo.");
		btnBaja.setBounds(327, 45, 97, 23);
		contentPane.add(btnBaja);
		
		JButton btnRellenar = new JButton("Rellenar");
		btnRellenar.setToolTipText("Rellena los campos del empleado que tenga la CI especificada");
		btnRellenar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnRellenar.setBounds(327, 80, 97, 23);
		contentPane.add(btnRellenar);
	}
}
