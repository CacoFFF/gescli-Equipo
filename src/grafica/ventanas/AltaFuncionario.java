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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNombre = new JLabel();
		txtNombre.setText("Nombre :");
		txtNombre.setBounds(27, 11, 86, 20);
		contentPane.add(txtNombre);
		
		txtApellido = new JLabel();
		txtApellido.setText("Apellido :");
		txtApellido.setBounds(27, 45, 86, 20);
		contentPane.add(txtApellido);
		
		txtCi = new JLabel();
		txtCi.setText("Ci :");
		txtCi.setBounds(27, 76, 86, 20);
		contentPane.add(txtCi);
		
		txtTelefono = new JLabel();
		txtTelefono.setText("Telefono :");
		txtTelefono.setBounds(27, 109, 86, 20);
		contentPane.add(txtTelefono);
		
		txtFechanac = new JLabel();
		txtFechanac.setText("FechaNac :");
		txtFechanac.setBounds(27, 139, 86, 20);
		contentPane.add(txtFechanac);
		
		txtCel = new JLabel();
		txtCel.setText("Cel :");
		txtCel.setBounds(27, 170, 86, 20);
		contentPane.add(txtCel);
		
		textoNombre = new JTextField();
		textoNombre.setBounds(140, 11, 149, 20);
		contentPane.add(textoNombre);
		textoNombre.setColumns(10);
		
		txtHorasdia = new JLabel();
		txtHorasdia.setText("HorasDia :");
		txtHorasdia.setBounds(27, 201, 86, 20);
		contentPane.add(txtHorasdia);
		
		textoApellido = new JTextField();
		textoApellido.setBounds(140, 45, 149, 20);
		contentPane.add(textoApellido);
		textoApellido.setColumns(10);
		
		textoCI = new JTextField();
		textoCI.setBounds(140, 76, 149, 20);
		contentPane.add(textoCI);
		textoCI.setColumns(10);
		
		textoTelefono = new JTextField();
		textoTelefono.setBounds(140, 109, 149, 20);
		contentPane.add(textoTelefono);
		textoTelefono.setColumns(10);
		
		textoFechaNac = new JTextField();
		textoFechaNac.setBounds(140, 139, 147, 20);
		contentPane.add(textoFechaNac);
		textoFechaNac.setColumns(10);
		
		textoCel = new JTextField();
		textoCel.setBounds(140, 170, 149, 20);
		contentPane.add(textoCel);
		textoCel.setColumns(10);
		
		textoHorasDia = new JTextField();
		textoHorasDia.setBounds(140, 201, 149, 20);
		contentPane.add(textoHorasDia);
		textoHorasDia.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
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
		btnNewButton.setBounds(320, 227, 89, 23);
		contentPane.add(btnNewButton);
	}
}
