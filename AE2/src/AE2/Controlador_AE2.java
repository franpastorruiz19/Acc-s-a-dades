package AE2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controlador_AE2 {

	private Modelo_AE2 modelo;
	private Vista_AE2 vista;
	private ActionListener ALBuscar, ALReemplaçar;

	Controlador_AE2(Modelo_AE2 modelo, Vista_AE2 vista) {

		this.modelo = modelo;
		this.vista = vista;
		control();
	}

	private void control() {
		String fitxer = modelo.getFitxer();
		String Reemplaç = modelo.getReemplaç();
		MostrarFitxer(fitxer, 1);

		ALBuscar = new ActionListener() {
			public void actionPerformed(ActionEvent actionevent) {

				Buscar(fitxer);
			}
		};
		vista.getBtnBuscar().addActionListener(ALBuscar);

		ALReemplaçar = new ActionListener() {
			public void actionPerformed(ActionEvent actionevent) {

				Reemplaçar(fitxer, Reemplaç);
			}
		};
		vista.getBtnReemplazar().addActionListener(ALReemplaçar);

	}

	/*
	 * - Mètode: MostrarFitxer 
	 * - Descripció: Aquest métode crida al métode ContingutFitxer de la clase 
	 *   Modelo_AE2 y mostra el text del fitxer en el TextArea que ens interese. 
	 * - Paràmeters d'entrada: String fitxer y Int Textarea. 
	 * - Paràmeters d'eixida: No
	 * 
	 */
	private void MostrarFitxer(String Fitxer, int Textarea) {
		ArrayList<String> arrayLineas = modelo.ContingutFitxer(Fitxer);
		vista.getTextAreaModificado().setText("");//Buidem els TextArea abans de mostrar el text que vullgam
		for (String linea : arrayLineas) {
			if (Textarea == 1) {
				vista.getTextAreaOriginal().append(linea + "\n");
			} else if (Textarea == 2) {
				vista.getTextAreaModificado().append(linea + "\n");
			}

		}

	}

	/*
	 * - Mètode: Buscar
	 * - Descripció: Aquest métode crida al métode ContarParaules de la clase 
	 *   Modelo_AE2 y mostra un missatge amb el nombre voltes que s'ha repetit la paraula . 
	 * - Paràmeters d'entrada: String fitxer. 
	 * - Paràmeters d'eixida: No
	 * 
	 */
	
	private void Buscar(String fitxer) {
		int cantidad = modelo.ContarParaules(vista.getTextFieldBuscar().getText(), fitxer);
		vista.mostrarQuantitatDeParaules(
				vista.getTextFieldBuscar().getText() + " se ha repetido " + cantidad + " veces.");

	}
	
	/*
	 * - Mètode: Reemplaçar
	 * - Descripció: Aquest métode crida al métode Reemplaçar de la clase 
	 *   Modelo_AE2 y mostra el text del fitxer en el TextAreaModificado. 
	 * - Paràmeters d'entrada: String fitxer y String Reemplaç. 
	 * - Paràmeters d'eixida: No
	 * 
	 */
	private void Reemplaçar(String fitxer, String Reemplaç) {
		modelo.Reemplaçar(fitxer, vista.getTextFieldBuscar().getText(), vista.getTextFieldReemplazar().getText(),
				Reemplaç);
		MostrarFitxer(Reemplaç, 2);
	}

}
