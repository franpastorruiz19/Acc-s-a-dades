package AE2;

import AE2.Controlador_AE2;
import AE2.Modelo_AE2;
import AE2.Vista_AE2;

public class Principal {

	public static void main(String[] args) {
		// TODO Apéndice de método generado automáticamente
		Modelo_AE2 modelo = new Modelo_AE2();
		Vista_AE2 vista = new Vista_AE2();
		Controlador_AE2 controlador = new Controlador_AE2(modelo, vista);
	}

}
