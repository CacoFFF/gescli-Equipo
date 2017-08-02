package grafica.ventanas;

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

public class AltaFuncionario extends JFrame {

	private JPanel contentPane;
	private JLabel txtNombre;
	private JLabel txtApellido;
	private JLabel txtCi;
	private JLabel txtTelefono;
	private JLabel txtFechanac;
	private JLabel txtCel;
	private JTextField textField;
	private JLabel txtHorasdia;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

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
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(320, 227, 89, 23);
		contentPane.add(btnNewButton);
		
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
		
		textField = new JTextField();
		textField.setBounds(140, 11, 149, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		txtHorasdia = new JLabel();
		txtHorasdia.setText("HorasDia :");
		txtHorasdia.setBounds(27, 201, 86, 20);
		contentPane.add(txtHorasdia);
		
		textField_1 = new JTextField();
		textField_1.setBounds(140, 45, 149, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(140, 76, 149, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(140, 109, 149, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(140, 139, 147, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(140, 170, 149, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(140, 201, 149, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
	}
}
