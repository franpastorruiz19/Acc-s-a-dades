package AE3;

import AE3.Controlador_AE3;
import AE3.Model;
import AE3.Vista_AE3;

public class Principal {

	public static void main(String[] args) {
		// TODO Apéndice de método generado automáticamente
		
		Model modelo = new Model();
		Model.Biblioteca.setLlibres(Model.Biblioteca.Llibre.recuperarTots());
		Vista_AE3 vista = new Vista_AE3();
		Controlador_AE3 controlador = new Controlador_AE3(modelo, vista);
		
	}

}
