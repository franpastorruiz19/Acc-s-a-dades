package AE4;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.ScrollPane;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Vista_AE4 {

	private JFrame frame;
	private JTable tbl_Select1;

	private JTable tbl_Select2;
	private JTextField txtConsulta;
	private JTable tbl_Consulta;
	private JButton btnConsultar;
	private JLabel lblBiblioteca;
	private JLabel lbl_Select1;
	private JLabel lbl_Select2_1;
	private JLabel lbl_Select2;
	

	public Vista_AE4() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);

		JPanel panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBounds(0, 10, 436, 253);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane scp_select1 = new JScrollPane();
		scp_select1.setBounds(36, 145, 535, 173);
		panel.add(scp_select1);

		tbl_Select1 = new JTable();
		scp_select1.setViewportView(tbl_Select1);

		JScrollPane scp_select2 = new JScrollPane();
		scp_select2.setBounds(616, 145, 205, 173);
		panel.add(scp_select2);

		tbl_Select2 = new JTable();
		scp_select2.setViewportView(tbl_Select2);

		JLabel lblConsulta = new JLabel("Escribe la consulta que quieras hacer: ");
		lblConsulta.setFont(new Font("Calibri", Font.PLAIN, 24));
		lblConsulta.setBounds(30, 349, 451, 37);
		panel.add(lblConsulta);

		txtConsulta = new JTextField();
		txtConsulta.setFont(new Font("Calibri", Font.PLAIN, 16));
		txtConsulta.setBounds(30, 383, 648, 44);
		panel.add(txtConsulta);
		txtConsulta.setColumns(10);

		JScrollPane scp_Consulta = new JScrollPane();
		scp_Consulta.setBounds(30, 440, 786, 228);
		panel.add(scp_Consulta);

		tbl_Consulta = new JTable();
		scp_Consulta.setViewportView(tbl_Consulta);

		btnConsultar = new JButton("CONSULTAR");
		btnConsultar.setFont(new Font("Calibri", Font.PLAIN, 15));
		btnConsultar.setBounds(707, 383, 112, 44);
		panel.add(btnConsultar);
		
		lblBiblioteca = new JLabel("BIBLIOTECA FRAN PASTOR");
		lblBiblioteca.setFont(new Font("Calibri", Font.BOLD, 30));
		lblBiblioteca.setBounds(256, 24, 360, 37);
		panel.add(lblBiblioteca);
		
		lbl_Select1 = new JLabel("Llibres que l'autor va naixer abans de 1950");
		lbl_Select1.setFont(new Font("Calibri", Font.PLAIN, 17));
		lbl_Select1.setBounds(36, 125, 315, 21);
		panel.add(lbl_Select1);
		
		lbl_Select2_1 = new JLabel("Editorials que van publicar llibres ");
		lbl_Select2_1.setFont(new Font("Calibri", Font.PLAIN, 17));
		lbl_Select2_1.setBounds(611, 99, 245, 21);
		panel.add(lbl_Select2_1);
		
		lbl_Select2 = new JLabel("en el segle XXI");
		lbl_Select2.setFont(new Font("Calibri", Font.PLAIN, 17));
		lbl_Select2.setBounds(611, 120, 245, 21);
		panel.add(lbl_Select2);
		frame.setBounds(100, 100, 902, 741);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.frame.setVisible(true);
	}

	/*
	 * - M�todes: getTbl
	 * - Descripci�: Retornen les tables de la clase vista. 
	 * - Par�meters d'entrada: Ning�n par�meter d'entrada. 
	 * - Par�meters d'eixida: JTable tbl_Select1, tbl_Select2, tbl_Consulta
	 * 
	 */
	
	public JTable getTbl_Select1() {
		return tbl_Select1;
	}

	public JTable getTbl_Select2() {
		return tbl_Select2;
	}

	public JTable getTbl_Consulta() {
		return tbl_Consulta;
	}
	
	/*
	 * - M�todes: getTxtConsulta
	 * - Descripci�: Retorna el TextField txtConsulta de la clase vista. 
	 * - Par�meters d'entrada: Ning�n par�meter d'entrada. 
	 * - Par�meters d'eixida: JTextField txtConsulta.
	 * 
	 */
	
	public JTextField getTxtConsulta() {
		return txtConsulta;
	}
	
	/*
	 * - M�todes: getBtnConsultar
	 * - Descripci�: Retorna els bot� btnConsultar de la clase vista. 
	 * - Par�meters d'entrada: Ning�n par�meter d'entrada. 
	 * - Par�meters d'eixida: JButton btnConsultar
	 * 
	 */
	
	public JButton getBtnConsultar() {
		return btnConsultar;
	}
	
	/*
	 * - M�tode: mostrarResultat
	 * - Descripci�: Aquest m�tode mostra el missatge que nosaltres desitjem per pantalla, . 
	 * - Par�meters d'entrada: String missatge. 
	 * - Par�meters d'eixida: No
	 * 
	 */
	
	public void mostrarResultat(String missatge) {
		JOptionPane.showMessageDialog(new JFrame(), missatge, "Av�s", JOptionPane.INFORMATION_MESSAGE);
	}
}
