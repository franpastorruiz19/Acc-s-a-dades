package AE5;

public class Principal {

	public static void main(String[] args) {

		Vista_AE5 vista = new Vista_AE5();
		Model_AE5 model=new Model_AE5();
		Controlador_AE5 controlador=new Controlador_AE5(model,vista);

	}

}
