package AE6;


public class Principal_AE6 {

	public static void main(String[] args) {
		Vista_AE6 vista = new Vista_AE6();
		Model_AE6 model=new Model_AE6();
		Controlador_AE6 controlador=new Controlador_AE6(model,vista);

	}

}
