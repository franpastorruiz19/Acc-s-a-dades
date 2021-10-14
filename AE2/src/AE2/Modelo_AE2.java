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
	private String reemplaç;

	Modelo_AE2() {
		this.fitxer = "Fitxer.txt";
		this.reemplaç = "Reemplaç.txt";
	}
	
	/*
	 * - Mètode: ContingutFitxer 
	 * - Descripció: Aquest mètode llig el contingut de un fitxer de text y el retorna en un ArrayList. 
	 * - Paràmeters d'entrada: String fitxer. 
	 * - Paràmeters d'eixida: ArrayList contingutFitxer.
	 * 
	 */

	public ArrayList<String> ContingutFitxer(String fitxer) {
		ArrayList<String> contingutFitxer = new ArrayList<String>();//Almacenarà el contingut del fitxer
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
	 * - Mètode: ContarParaules
	 * - Descripció: Aquest mètode busca una paraula en un 
	 *   fitxer y conta les vegades que es repetix. 
	 * - Paràmeters d'entrada: String fitxer y String paraula(a buscar). 
	 * - Paràmeters d'eixida: Coincidencies (vegades que ix una paraula)
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
							coincidencies += 1;//Si el contingut del array es igual a la paraula sumarem una coincidència.
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
	 * - Mètode: getFitxer
	 * - Descripció: Aques mètode retorna el fitxer principal sobre el que treballem. 
	 * - Paràmeters d'entrada: No. 
	 * - Paràmeters d'eixida: String fitxer.
	 * 
	 */
	
	public String getFitxer() {
		return fitxer;
	}

	/*
	 * - Mètode: getReemplaç
	 * - Descripció: Aques mètode retorna el fitxer de reemplaç sobre el que treballem. 
	 * - Paràmeters d'entrada: No. 
	 * - Paràmeters d'eixida: String reemplaç.
	 * 
	 */
	public String getReemplaç() {
		return reemplaç;
	}
	
	/*
	 * - Mètode: Reemplaçar 
	 * - Descripció: Aquest métode agafa una paraula de un fitxer 
	 *   de text y la cambia per altra paraula en un fitxer diferent. 
	 * - Paràmeters d'entrada: String fitxer, String fitxerReemplaç, 
	 *   String paraula(a canviar) y String Reemplaç(paraula per la que canvies) . 
	 * - Paràmeters d'eixida: No
	 * 
	 */

	public void Reemplaçar(String fitxer, String p, String Reemplaç, String fitxerReemplaç) {

		try {
			BufferedReader reader = new BufferedReader(new FileReader(fitxer));

			String linea = "";
			String contingut = "";
			while ((linea = reader.readLine()) != null) {
				contingut += linea + "\r\n";
			}//LLegim el fitxer linea a linea i l'amagatzenem en una variable String.

			reader.close();

			String ContingutNou = contingut.replaceAll(p, Reemplaç);//Reemplaçem una paraula per el reemplaç en una nova variable String

			FileWriter writer = new FileWriter(fitxerReemplaç);

			writer.write(ContingutNou);//Escribim a un fitxer nou tot el text reemplaçat.

			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
