package AE3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
public class Vista_AE3 {

	
		private JButton btnCercarLlibre;
		private JButton btnCrear;
		private JButton btnActualitzar;
		private JButton btnMostrarLlibres;
		private JButton btnMostrarInformacio;
		private JButton btnBorrar;
		private JButton btnTancar;
		private JFrame frame;
		private JTextField txtTitolCrear;
		private JTextField txtAutorCrear;
		private JTextField txtAnyCrear;
		private JTextField txtEditorialCrear;
		private JTextField txtPagsCrear;
		private JTextField txtTitolActu;
		private JTextField txtAutorActu;
		private JTextField txtAnyActu;
		private JTextField txtEditorialActu;
		private JTextField txtPagsActu;
		private JTextField txtIdActu;
		private JTextField txtIdInfo;
		private JTextField txtIdBorrar;

		
		public Vista_AE3() {
			initialize();
		}
		

		private void initialize() {
			frame = new JFrame();
			frame.getContentPane().setBackground(new Color(255, 0, 0));
			frame.setBounds(100, 100, 920, 704);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			
			JPanel panel = new JPanel();
			panel.setBounds(26, 90, 308, 264);
			frame.getContentPane().add(panel);
			panel.setLayout(null);
			
			btnCrear = new JButton("Crear Llibre");
			btnCrear.setFont(new Font("Calibri", Font.PLAIN, 17));
			btnCrear.setBounds(143, 215, 134, 37);
			panel.add(btnCrear);
			
			JLabel lblCrearUnLlibre = new JLabel("Crear un Llibre");
			lblCrearUnLlibre.setFont(new Font("Calibri", Font.BOLD, 20));
			lblCrearUnLlibre.setBounds(10, 0, 146, 27);
			panel.add(lblCrearUnLlibre);
			
			JLabel lblTtolCrear = new JLabel("T\u00EDtol: ");
			lblTtolCrear.setFont(new Font("Calibri", Font.BOLD, 16));
			lblTtolCrear.setBounds(10, 38, 45, 27);
			panel.add(lblTtolCrear);
			
			txtTitolCrear = new JTextField();
			txtTitolCrear.setColumns(10);
			txtTitolCrear.setBounds(65, 35, 212, 29);
			panel.add(txtTitolCrear);
			
			txtAutorCrear = new JTextField();
			txtAutorCrear.setColumns(10);
			txtAutorCrear.setBounds(65, 70, 212, 29);
			panel.add(txtAutorCrear);
			
			JLabel lblAutorCrear = new JLabel("Autor: ");
			lblAutorCrear.setFont(new Font("Calibri", Font.BOLD, 16));
			lblAutorCrear.setBounds(10, 73, 55, 27);
			panel.add(lblAutorCrear);
			
			JLabel lblAnyCrear = new JLabel("Any de publicaci\u00F3: ");
			lblAnyCrear.setFont(new Font("Calibri", Font.BOLD, 16));
			lblAnyCrear.setBounds(10, 108, 137, 27);
			panel.add(lblAnyCrear);
			
			txtAnyCrear = new JTextField();
			txtAnyCrear.setColumns(10);
			txtAnyCrear.setBounds(157, 105, 120, 29);
			panel.add(txtAnyCrear);
			
			txtEditorialCrear = new JTextField();
			txtEditorialCrear.setColumns(10);
			txtEditorialCrear.setBounds(81, 140, 196, 29);
			panel.add(txtEditorialCrear);
			
			JLabel lblEditorialCrear = new JLabel("Editorial:");
			lblEditorialCrear.setFont(new Font("Calibri", Font.BOLD, 16));
			lblEditorialCrear.setBounds(10, 143, 72, 27);
			panel.add(lblEditorialCrear);
			
			JLabel lblpagsCrear = new JLabel("Nombre de p\u00E0gines: ");
			lblpagsCrear.setFont(new Font("Calibri", Font.BOLD, 16));
			lblpagsCrear.setBounds(10, 178, 146, 27);
			panel.add(lblpagsCrear);
			
			txtPagsCrear = new JTextField();
			txtPagsCrear.setColumns(10);
			txtPagsCrear.setBounds(157, 175, 120, 29);
			panel.add(txtPagsCrear);
			
			JPanel pnlActualizar = new JPanel();
			pnlActualizar.setLayout(null);
			pnlActualizar.setBounds(379, 90, 481, 390);
			frame.getContentPane().add(pnlActualizar);
			
			btnActualitzar = new JButton("Actualitzar \r\nLlibre");
			btnActualitzar.setEnabled(false);
			btnActualitzar.setFont(new Font("Calibri", Font.PLAIN, 17));
			btnActualitzar.setBounds(297, 322, 158, 46);
			pnlActualizar.add(btnActualitzar);
			
			JLabel lblActualizarLlibre = new JLabel("Actualitzar Llibre");
			lblActualizarLlibre.setFont(new Font("Calibri", Font.BOLD, 20));
			lblActualizarLlibre.setBounds(10, 0, 169, 27);
			pnlActualizar.add(lblActualizarLlibre);
			
			JLabel lblTtolActu = new JLabel("T\u00EDtol: ");
			lblTtolActu.setFont(new Font("Calibri", Font.BOLD, 16));
			lblTtolActu.setBounds(10, 150, 45, 27);
			pnlActualizar.add(lblTtolActu);
			
			txtTitolActu = new JTextField();
			txtTitolActu.setColumns(10);
			txtTitolActu.setBounds(65, 147, 212, 29);
			pnlActualizar.add(txtTitolActu);
			
			txtAutorActu = new JTextField();
			txtAutorActu.setColumns(10);
			txtAutorActu.setBounds(65, 182, 212, 29);
			pnlActualizar.add(txtAutorActu);
			
			JLabel lblAutorActu = new JLabel("Autor: ");
			lblAutorActu.setFont(new Font("Calibri", Font.BOLD, 16));
			lblAutorActu.setBounds(10, 185, 55, 27);
			pnlActualizar.add(lblAutorActu);
			
			JLabel lblAnyActu = new JLabel("Any de publicaci\u00F3: ");
			lblAnyActu.setFont(new Font("Calibri", Font.BOLD, 16));
			lblAnyActu.setBounds(10, 220, 137, 27);
			pnlActualizar.add(lblAnyActu);
			
			txtAnyActu = new JTextField();
			txtAnyActu.setColumns(10);
			txtAnyActu.setBounds(157, 217, 120, 29);
			pnlActualizar.add(txtAnyActu);
			
			txtEditorialActu = new JTextField();
			txtEditorialActu.setColumns(10);
			txtEditorialActu.setBounds(81, 252, 196, 29);
			pnlActualizar.add(txtEditorialActu);
			
			JLabel lblEditorialActu = new JLabel("Editorial:");
			lblEditorialActu.setFont(new Font("Calibri", Font.BOLD, 16));
			lblEditorialActu.setBounds(10, 255, 72, 27);
			pnlActualizar.add(lblEditorialActu);
			
			JLabel lblPagsActu = new JLabel("Nombre de p\u00E0gines: ");
			lblPagsActu.setFont(new Font("Calibri", Font.BOLD, 16));
			lblPagsActu.setBounds(10, 290, 146, 27);
			pnlActualizar.add(lblPagsActu);
			
			txtPagsActu = new JTextField();
			txtPagsActu.setColumns(10);
			txtPagsActu.setBounds(157, 287, 120, 29);
			pnlActualizar.add(txtPagsActu);
			
			JLabel lblIdActu = new JLabel("Escriu el Id del llibre que vols actualitzar: ");
			lblIdActu.setFont(new Font("Calibri", Font.BOLD, 18));
			lblIdActu.setBounds(10, 31, 314, 27);
			pnlActualizar.add(lblIdActu);
			
			txtIdActu = new JTextField();
			txtIdActu.setColumns(10);
			txtIdActu.setBounds(323, 29, 120, 29);
			pnlActualizar.add(txtIdActu);
			
			btnCercarLlibre = new JButton("Cercar \r\nLlibre");
			btnCercarLlibre.setFont(new Font("Calibri", Font.PLAIN, 17));
			btnCercarLlibre.setBounds(297, 70, 158, 55);
			pnlActualizar.add(btnCercarLlibre);
			
			JPanel pnlInfoDetallada = new JPanel();
			pnlInfoDetallada.setBounds(379, 508, 481, 127);
			frame.getContentPane().add(pnlInfoDetallada);
			pnlInfoDetallada.setLayout(null);
			
			JLabel lblMostrarInfo = new JLabel("Informaci\u00F3 \r\ndetallada de un llibre");
			lblMostrarInfo.setToolTipText("\r\n");
			lblMostrarInfo.setFont(new Font("Calibri", Font.BOLD, 20));
			lblMostrarInfo.setBounds(10, 0, 351, 32);
			pnlInfoDetallada.add(lblMostrarInfo);
			
			JLabel lblIdInfo = new JLabel("Escriu el Id del llibre que vols saber la informaci\u00F3 detallada: ");
			lblIdInfo.setFont(new Font("Calibri", Font.BOLD, 18));
			lblIdInfo.setBounds(10, 29, 461, 27);
			pnlInfoDetallada.add(lblIdInfo);
			
			txtIdInfo = new JTextField();
			txtIdInfo.setColumns(10);
			txtIdInfo.setBounds(10, 66, 95, 30);
			pnlInfoDetallada.add(txtIdInfo);
			
			btnMostrarInformacio = new JButton("Mostrar Informaci\u00F3");
			btnMostrarInformacio.setFont(new Font("Calibri", Font.PLAIN, 17));
			btnMostrarInformacio.setBounds(134, 66, 170, 30);
			pnlInfoDetallada.add(btnMostrarInformacio);
			
			JPanel pnlInfoDetallada_1 = new JPanel();
			pnlInfoDetallada_1.setLayout(null);
			pnlInfoDetallada_1.setBounds(26, 383, 308, 127);
			frame.getContentPane().add(pnlInfoDetallada_1);
			
			JLabel lblBorrarUnLlibre = new JLabel("Borrar un Llibre");
			lblBorrarUnLlibre.setToolTipText("\r\n");
			lblBorrarUnLlibre.setFont(new Font("Calibri", Font.BOLD, 20));
			lblBorrarUnLlibre.setBounds(10, 0, 199, 32);
			pnlInfoDetallada_1.add(lblBorrarUnLlibre);
			
			JLabel lblIdBorrar = new JLabel("Escriu el Id del llibre que vols borrar\r\n: ");
			lblIdBorrar.setFont(new Font("Calibri", Font.BOLD, 18));
			lblIdBorrar.setBounds(10, 29, 288, 27);
			pnlInfoDetallada_1.add(lblIdBorrar);
			
			txtIdBorrar = new JTextField();
			txtIdBorrar.setColumns(10);
			txtIdBorrar.setBounds(10, 66, 95, 30);
			pnlInfoDetallada_1.add(txtIdBorrar);
			
			btnBorrar = new JButton("Borrar");
			btnBorrar.setFont(new Font("Calibri", Font.PLAIN, 17));
			btnBorrar.setBounds(134, 66, 95, 30);
			pnlInfoDetallada_1.add(btnBorrar);
			
			JPanel pnlMostrarInfo = new JPanel();
			pnlMostrarInfo.setLayout(null);
			pnlMostrarInfo.setBounds(26, 538, 308, 97);
			frame.getContentPane().add(pnlMostrarInfo);
			
			JLabel lblMostrarLlibresDe = new JLabel("Mostrar llibres de la Biblioteca");
			lblMostrarLlibresDe.setToolTipText("\r\n");
			lblMostrarLlibresDe.setFont(new Font("Calibri", Font.BOLD, 20));
			lblMostrarLlibresDe.setBounds(10, 0, 288, 32);
			pnlMostrarInfo.add(lblMostrarLlibresDe);
			
			btnMostrarLlibres = new JButton("Mostrar Llibres");
			btnMostrarLlibres.setFont(new Font("Calibri", Font.PLAIN, 17));
			btnMostrarLlibres.setBounds(159, 33, 139, 42);
			pnlMostrarInfo.add(btnMostrarLlibres);
			
			JPanel pnlBiblioteca = new JPanel();
			pnlBiblioteca.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			pnlBiblioteca.setBackground(new Color(255, 255, 255));
			pnlBiblioteca.setBounds(0, 0, 906, 68);
			frame.getContentPane().add(pnlBiblioteca);
			pnlBiblioteca.setLayout(null);
			
			JLabel lblBibliotecaFranPastor = new JLabel("BIBLIOTECA FRAN PASTOR");
			lblBibliotecaFranPastor.setBounds(297, 10, 333, 48);
			lblBibliotecaFranPastor.setFont(new Font("Calibri", Font.BOLD, 30));
			pnlBiblioteca.add(lblBibliotecaFranPastor);
			
			btnTancar = new JButton("CERRAR BIBLIOTECA");
			btnTancar.setFont(new Font("Calibri", Font.BOLD, 15));
			btnTancar.setBackground(Color.RED);
			btnTancar.setBounds(708, 10, 188, 48);
			pnlBiblioteca.add(btnTancar);
			
			this.frame.setVisible(true);
		}
		
		/*
		 * - Mètodes: getBtn
		 * - Descripció: Retornen els botons de la clase vista. 
		 * - Paràmeters d'entrada: Ningún paràmeter d'entrada. 
		 * - Paràmeters d'eixida: JButton btnCrear, btnActualizar, btnBorrar, btnMostrarInfo,
		 *   btnMostrarLlibres, btnCercar, btnTancar
		 * 
		 */
		
		public JButton getBtnCrear() {
			return btnCrear;
		}
		
		public JButton getBtnActualitzar() {
			return btnActualitzar;
		}
		public JButton getBtnCercar() {
			return btnCercarLlibre;
		}
		public JButton getBtnMostrarLlibres() {
			return btnMostrarLlibres;
		}
		
		public JButton getBtnMontrarInfo() {
			return btnMostrarInformacio;
		}
		
		public JButton getBtnBorrar() {
			return btnBorrar;
		}
		
		public JButton getBtnTancar() {
			return btnTancar;
		}
		
		/*
		 * - Mètodes: getTxt
		 * - Descripció: Retornen els TextField de la clase vista. 
		 * - Paràmeters d'entrada: Ningún paràmeter d'entrada. 
		 * - Paràmeters d'eixida: JTextField txtTitolCrear, txtAutorCrear, txtAnyCrear, txtEditorialCrear, 
		 *   txtPagsCrear, txtTitolActu, txtAutorActu, txtAnyActu, txtEditorialActu, txtPagsActu, 
		 *   txtIdBorrar, txtIdActu, txtIdInfo.
		 * 
		 */
		
		public JTextField getTxtTitolCrear() {
			return txtTitolCrear;
		}
		
		public JTextField getTxtAutorCrear() {
			return txtAutorCrear;
		}
		
		public JTextField getTxtAnyCrear() {
			return txtAnyCrear;
		}
		
		public JTextField getTxtEditorialCrear() {
			return txtEditorialCrear;
		}
		
		public JTextField getTxtPagsCrear() {
			return txtPagsCrear;
		}
		
		public JTextField getTxtTitolActu() {
			return txtTitolActu;
		}
		
		public JTextField getTxtAutorActu() {
			return txtAutorActu;
		}
		
		public JTextField getTxtAnyActu() {
			return txtAnyActu;
		}
		
		public JTextField getTxtEditorialActu() {
			return txtEditorialActu;
		}
		
		public JTextField getTxtPagsActu() {
			return txtPagsActu;
		}
		
		public JTextField getTxtIdActu() {
			return txtIdActu;
		}
		
		public JTextField getTxtIdBorrar() {
			return txtIdBorrar;
		}
		
		public JTextField getTxtIdInfo() {
			return txtIdInfo;
		}
		
		/*
		 * - Mètode: mostrarResultat
		 * - Descripció: Aquest mètode mostra el missatge que nosaltres desitjem per pantalla, . 
		 * - Paràmeters d'entrada: String missatge. 
		 * - Paràmeters d'eixida: No
		 * 
		 */
		
		public void mostrarResultat(String missatge) {
			JOptionPane.showMessageDialog(new JFrame(), missatge, "Quantitat de paraules", JOptionPane.INFORMATION_MESSAGE);
		}
	}