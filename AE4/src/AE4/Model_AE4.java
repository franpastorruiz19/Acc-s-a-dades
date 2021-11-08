package AE4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Model_AE4 {

	public static final String URL = "jdbc:mysql://localhost:3306/Biblioteca";
	public static final String USER = "root";
	public static final String CLAVE = "";

	static String Fitxer = "AE04_T1_4_JDBC_Dades.csv";

	
	/*
	 * - Mètode: getConexió
	 * - Descripció: Aques métode es el encarregat de conectar amb la base de dades 
	 *   y retornar la seua conexió. 
	 * - Paràmeters d'entrada: Ningún paràmeter d'entrada. 
	 * - Paràmeters d'eixida: Connection con.
	 * 
	 */
	
	public static Connection getConexió() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = (Connection) DriverManager.getConnection(URL, USER, CLAVE);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return con;
	}


	/*
	 * - Mètode: ContingutFitxer 
	 * - Descripció: Aquest mètode llig el contingut del fitxer csv y el retorna en un ArrayList. 
	 * - Paràmeters d'entrada: Ningún paràmeter d'entrada. 
	 * - Paràmeters d'eixida: ArrayList contingutFitxer.
	 * 
	 */
	
	public static ArrayList<String> ContingutFitxer() {
		ArrayList<String> contingutFitxer = new ArrayList();
		File f = new File(Fitxer);
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String linea = br.readLine();
			linea = br.readLine();
			int i = 0;
			while (linea != null) {

				contingutFitxer.add(linea);
				linea = br.readLine();
				i++;
			}
			br.close();
			fr.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return contingutFitxer;
	}
	

	/*
	 * - Mètode: insertar
	 * - Descripció: Aquest mètode llig el contingut de un ArrayList y l'inserta en una base de dades. 
	 * - Paràmeters d'entrada: Connection con, ArrayList<String> l. 
	 * - Paràmeters d'eixida: Ningún paràmeter d'eixida.
	 * 
	 */

	public static void insertar(ArrayList<String> l, Connection con) {
		PreparedStatement ps;
		String sql;

		try {
			for (int i = 0; i < l.size(); i++) {
				String[] linea = l.get(i).split(";");
				for (int j = 0; j < linea.length; j++) {
					
					if (linea[j].equals("")) {
						//Si el contingut de una posició del array esta buit que siga sustituit per "N.C"
						linea[j] = "N.C";
					}
				}
				sql = "insert into llibres(titol, autor, any_naixement, any_publicacio, editorial, num_pags) values(?,?,?,?,?,?)";
				ps = con.prepareStatement(sql);
				ps.setString(1, linea[0]);
				ps.setString(2, linea[1]);
				ps.setString(3, linea[2]);
				ps.setString(4, linea[3]);
				ps.setString(5, linea[4]);
				ps.setString(6, linea[5]);
				ps.executeUpdate();
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error de conexió:" + e.getMessage());
		}
	}


	/*
	 * - Mètode: consultaSelect 
	 * - Descripció: Aquest mètode executa consultes SELECT a la base de dades 
	 *   i les retorna com a contingut de una tabla. 
	 * - Paràmeters d'entrada: Connection con, String consulta. 
	 * - Paràmeters d'eixida: DefaultTableModel dtm.
	 * 
	 */
	
	public static DefaultTableModel consultaSelect(Connection con, String consulta) {

		int size = 0;
		String info;
		DefaultTableModel dtm = new DefaultTableModel();
		String[] fila;
		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(consulta);
			ResultSetMetaData rsmd = rs.getMetaData();
			size = rsmd.getColumnCount(); //Agafem el nombre de columnes

			for (int j = 1; j <= size; j++) {
				//Anyadim al dtm les columnes amb els seus noms corresponents
				dtm.addColumn(rsmd.getColumnName(j));
			}
			while (rs.next()) {
				info = "";
				for (int i = 1; i <= size; i++) {
				
					if (i == size) {
						info += rs.getString(i);
					} else {
						//Que afegisca un ";" si no ha arribat al final de la "linea" del resultset .
						info += rs.getString(i) + ";";
					}
				}
				fila = info.split(";");
				dtm.addRow(fila);

			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		;
		return dtm;
	}


	/*
	 * - Mètode: consultes 
	 * - Descripció: Aquest mètode executa consultes (Update, Delete, Insert...) a la base 
	 *   de dades y retorna un missatje diguen si s'ha executat correctament o no.
	 * - Paràmeters d'entrada: Connection con, String consulta. 
	 * - Paràmeters d'eixida: String m.
	 * 
	 */
	
	public static String consultes(Connection con, String consulta) {

		String m = "";
		try {

			Statement stmt = con.createStatement();
			int c = stmt.executeUpdate(consulta);

			m = "La consulta s'ha realitzat amb exit";
			stmt.close();
		} catch (Exception e) {
			m += e;
		}
		;
		return m;
	}

}
