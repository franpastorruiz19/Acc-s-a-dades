package AE2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Modelo_AE2 {
	private String fitxer;
	private String reempla�;

	Modelo_AE2() {
		this.fitxer = "Fitxer.txt";
		this.reempla� = "Reempla�.txt";
	}
	
	/*
	 * - M�tode: ContingutFitxer 
	 * - Descripci�: Aquest m�tode llig el contingut de un fitxer de text y el retorna en un ArrayList. 
	 * - Par�meters d'entrada: String fitxer. 
	 * - Par�meters d'eixida: ArrayList contingutFitxer.
	 * 
	 */

	public ArrayList<String> ContingutFitxer(String fitxer) {
		ArrayList<String> contingutFitxer = new ArrayList<String>();//Almacenar� el contingut del fitxer
		File f = new File(fitxer);
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String linea = br.readLine();
			while (linea != null) {
				contingutFitxer.add(linea);//Anyadim cada linea al ArrayList
				linea = br.readLine();
			}
			br.close();
			fr.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return contingutFitxer;
	}
	
	/*
	 * - M�tode: ContarParaules
	 * - Descripci�: Aquest m�tode busca una paraula en un 
	 *   fitxer y conta les vegades que es repetix. 
	 * - Par�meters d'entrada: String fitxer y String paraula(a buscar). 
	 * - Par�meters d'eixida: Coincidencies (vegades que ix una paraula)
	 * 
	 */

	public int ContarParaules(String paraula, String fitxer) {
		File f = new File(fitxer);
		int coincidencies = 0;
		try {
			if (f.exists()) {
				BufferedReader archiullegir = new BufferedReader(new FileReader(f));
				String lineallegida;

				while ((lineallegida = archiullegir.readLine()) != null) {

					String[] parts = lineallegida.split(" ");// separarem les posicions del array per cada espai que hi hasca.

					for (int i = 0; i < parts.length; i++) {
						if (parts[i].equals(paraula)) {
							coincidencies += 1;//Si el contingut del array es igual a la paraula sumarem una coincid�ncia.
						}
					}

				}
				archiullegir.close();
			}

		} catch (Exception e) {
		}
		return coincidencies;
	}

	/*
	 * - M�tode: getFitxer
	 * - Descripci�: Aques m�tode retorna el fitxer principal sobre el que treballem. 
	 * - Par�meters d'entrada: No. 
	 * - Par�meters d'eixida: String fitxer.
	 * 
	 */
	
	public String getFitxer() {
		return fitxer;
	}

	/*
	 * - M�tode: getReempla�
	 * - Descripci�: Aques m�tode retorna el fitxer de reempla� sobre el que treballem. 
	 * - Par�meters d'entrada: No. 
	 * - Par�meters d'eixida: String reempla�.
	 * 
	 */
	public String getReempla�() {
		return reempla�;
	}
	
	/*
	 * - M�tode: Reempla�ar 
	 * - Descripci�: Aquest m�tode agafa una paraula de un fitxer 
	 *   de text y la cambia per altra paraula en un fitxer diferent. 
	 * - Par�meters d'entrada: String fitxer, String fitxerReempla�, 
	 *   String paraula(a canviar) y String Reempla�(paraula per la que canvies) . 
	 * - Par�meters d'eixida: No
	 * 
	 */

	public void Reempla�ar(String fitxer, String p, String Reempla�, String fitxerReempla�) {

		try {
			BufferedReader reader = new BufferedReader(new FileReader(fitxer));

			String linea = "";
			String contingut = "";
			while ((linea = reader.readLine()) != null) {
				contingut += linea + "\r\n";
			}//LLegim el fitxer linea a linea i l'amagatzenem en una variable String.

			reader.close();

			String ContingutNou = contingut.replaceAll(p, Reempla�);//Reempla�em una paraula per el reempla� en una nova variable String

			FileWriter writer = new FileWriter(fitxerReempla�);

			writer.write(ContingutNou);//Escribim a un fitxer nou tot el text reempla�at.

			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
