package AE4;

import AE4.Controlador_AE4;
import AE4.Model_AE4;
import AE4.Vista_AE4;

public class Principal {

	public static void main(String[] args) {
		Model_AE4 modelo = new Model_AE4();
		Vista_AE4 vista = new Vista_AE4();
		Controlador_AE4 controlador = new Controlador_AE4(modelo, vista);

	}

}
