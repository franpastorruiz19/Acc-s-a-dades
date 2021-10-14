package AE2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controlador_AE2 {

	private Modelo_AE2 modelo;
	private Vista_AE2 vista;
	private ActionListener ALBuscar, ALReempla�ar;

	Controlador_AE2(Modelo_AE2 modelo, Vista_AE2 vista) {

		this.modelo = modelo;
		this.vista = vista;
		control();
	}

	private void control() {
		String fitxer = modelo.getFitxer();
		String Reempla� = modelo.getReempla�();
		MostrarFitxer(fitxer, 1);

		ALBuscar = new ActionListener() {
			public void actionPerformed(ActionEvent actionevent) {

				Buscar(fitxer);
			}
		};
		vista.getBtnBuscar().addActionListener(ALBuscar);

		ALReempla�ar = new ActionListener() {
			public void actionPerformed(ActionEvent actionevent) {

				Reempla�ar(fitxer, Reempla�);
			}
		};
		vista.getBtnReemplazar().addActionListener(ALReempla�ar);

	}

	/*
	 * - M�tode: MostrarFitxer 
	 * - Descripci�: Aquest m�tode crida al m�tode ContingutFitxer de la clase 
	 *   Modelo_AE2 y mostra el text del fitxer en el TextArea que ens interese. 
	 * - Par�meters d'entrada: String fitxer y Int Textarea. 
	 * - Par�meters d'eixida: No
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
	 * - M�tode: Buscar
	 * - Descripci�: Aquest m�tode crida al m�tode ContarParaules de la clase 
	 *   Modelo_AE2 y mostra un missatge amb el nombre voltes que s'ha repetit la paraula . 
	 * - Par�meters d'entrada: String fitxer. 
	 * - Par�meters d'eixida: No
	 * 
	 */
	
	private void Buscar(String fitxer) {
		int cantidad = modelo.ContarParaules(vista.getTextFieldBuscar().getText(), fitxer);
		vista.mostrarQuantitatDeParaules(
				vista.getTextFieldBuscar().getText() + " se ha repetido " + cantidad + " veces.");

	}
	
	/*
	 * - M�tode: Reempla�ar
	 * - Descripci�: Aquest m�tode crida al m�tode Reempla�ar de la clase 
	 *   Modelo_AE2 y mostra el text del fitxer en el TextAreaModificado. 
	 * - Par�meters d'entrada: String fitxer y String Reempla�. 
	 * - Par�meters d'eixida: No
	 * 
	 */
	private void Reempla�ar(String fitxer, String Reempla�) {
		modelo.Reempla�ar(fitxer, vista.getTextFieldBuscar().getText(), vista.getTextFieldReemplazar().getText(),
				Reempla�);
		MostrarFitxer(Reempla�, 2);
	}

}
