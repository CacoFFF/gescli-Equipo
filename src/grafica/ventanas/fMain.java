package grafica.ventanas;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

public class fMain extends JFrame {
	public static int fW=410, fH=400;

	//PaneInicio paneInicio;
	PaneCliente paneCli;
	PaneFuncionario paneFun;
	JTabbedPane tabbedPane = new JTabbedPane();

	public fMain() {

		/* Agregar panel de inicio (una img inicial, un par de botones(hacia otras pestañas o vent de ayuda, etc.)
		 * paneInicio=new PaneInicio();
		 * tabbedPane.addTab("Inicio", paneInicio);
		 * */
		
		paneCli = new PaneCliente();
		tabbedPane.addTab("Clientes", paneCli);
		
		paneFun=new PaneFuncionario();
		tabbedPane.addTab("Funcionarios", paneFun);		
		
		
		getContentPane().add(tabbedPane);

	}// contructor

	public static void main(String[] args) {
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception w) { System.out.println(w.getStackTrace());}

		fMain win = new fMain();
		win.setSize(fW, fH);
		win.setResizable(false);
		win.setLocationRelativeTo(null);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setVisible(true);

	}// main

}// class
