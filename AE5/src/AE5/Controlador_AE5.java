package AE5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Controlador_AE5 {
	private Model_AE5 model;
	private Vista_AE5 vista;
	private ActionListener ALCrear, ALBorrar, ALMostrarInfo, ALBuscarLlibre, ALActualitzar, ALMostrarLlibres, ALTancar;

	public Controlador_AE5(Model_AE5 model, Vista_AE5 vista) {

		this.model = model;
		this.vista = vista;
		control();
	}

	private void control() {

		SessionFactory sf = Model_AE5.crearSessio();
		Session s = sf.openSession();

		ALCrear = new ActionListener() {
			public void actionPerformed(ActionEvent actionevent) {

				String t = vista.getTxtTitolCrear().getText();
				String a = vista.getTxtAutorCrear().getText();
				String any_publi = vista.getTxtAnyPubliCrear().getText();
				String any_naix = vista.getTxtAnyNaixCrear().getText();
				String ed = vista.getTxtEditorialCrear().getText();
				String pags = vista.getTxtPagsCrear().getText();
				Llibre l = new Llibre(t, a, any_publi, any_naix, ed, pags);
				vista.mostrarResultat(
						"El Llibre " + t + " s'ha creat correctament amb el ID: " + Llibre.CrearLlibre(l, s) + "");

				vista.getTxtTitolCrear().setText("");
				vista.getTxtAutorCrear().setText("");
				vista.getTxtAnyPubliCrear().setText("");
				vista.getTxtAnyNaixCrear().setText("");
				vista.getTxtEditorialCrear().setText("");
				vista.getTxtPagsCrear().setText("");
			}
		};
		vista.getBtnCrear().addActionListener(ALCrear);

		ALBuscarLlibre = new ActionListener() {
			public void actionPerformed(ActionEvent actionevent) {
				int id = Integer.parseInt(vista.getTxtIdActu().getText());
				boolean trobat = false;
				for (int i = 0; i < Llibre.RecuperarLlibres(s).size(); i++) {
					if (Llibre.RecuperarLlibres(s).get(i).getId() == id) {
						trobat = true;
						vista.getBtnActualitzar().setEnabled(true);

						vista.getTxtTitolActu().setText(Llibre.RecuperarLlibres(s).get(i).getTitol());
						vista.getTxtAutorActu().setText(Llibre.RecuperarLlibres(s).get(i).getAutor());
						vista.getTxtAnyPubliActu().setText(Llibre.RecuperarLlibres(s).get(i).getAny_publicacio() + "");
						vista.getTxtAnyNaixActu().setText(Llibre.RecuperarLlibres(s).get(i).getAny_naixement() + "");
						vista.getTxtEditorialActu().setText(Llibre.RecuperarLlibres(s).get(i).getEditorial());
						vista.getTxtPagsActu().setText(Llibre.RecuperarLlibres(s).get(i).getNum_pags() + "");
					}
				}
				if (!trobat) {

					vista.mostrarResultat("No s'ha trobat un llibre amb el Id: " + id);
				}
			}
		};
		vista.getBtnCercar().addActionListener(ALBuscarLlibre);

		ALActualitzar = new ActionListener() {
			public void actionPerformed(ActionEvent actionevent) {
				int id = Integer.parseInt(vista.getTxtIdActu().getText());
				String t = vista.getTxtTitolActu().getText();
				String a = vista.getTxtAutorActu().getText();
				String anyPubli = vista.getTxtAnyPubliActu().getText();
				String anyNaix = vista.getTxtAnyNaixActu().getText();
				String ed = vista.getTxtEditorialActu().getText();
				String pags = vista.getTxtPagsActu().getText();

				vista.getBtnActualitzar().setEnabled(false);
				vista.getTxtTitolActu().setText("");
				vista.getTxtAutorActu().setText("");
				vista.getTxtAnyPubliActu().setText("");
				vista.getTxtAnyNaixActu().setText("");
				vista.getTxtEditorialActu().setText("");
				vista.getTxtPagsActu().setText("");

				Llibre ll = new Llibre(t, a, anyPubli, anyNaix, ed, pags);
				Llibre.ActualitzaLLibre(s, id, ll);
				vista.mostrarResultat("S' ha actualitzat " + ll.getTitol() + " correctamnet");
			}
		};
		vista.getBtnActualitzar().addActionListener(ALActualitzar);

		ALMostrarLlibres = new ActionListener() {
			public void actionPerformed(ActionEvent actionevent) {
				ArrayList<Llibre> ll = Llibre.RecuperarLlibres(s);
				String mensaje = "";
				for (int i = 0; i < ll.size(); i++) {
					mensaje += ll.get(i).getTitol() + "   ID: " + ll.get(i).getId() + "\n";
				}
				vista.mostrarResultat(mensaje);
			}
		};
		vista.getBtnMostrarLlibres().addActionListener(ALMostrarLlibres);

		ALBorrar = new ActionListener() {
			public void actionPerformed(ActionEvent actionevent) {
				int id = Integer.parseInt(vista.getTxtIdBorrar().getText());
				boolean trobat = false;
				for (int i = 0; i < Llibre.RecuperarLlibres(s).size(); i++) {
					if (Llibre.RecuperarLlibres(s).get(i).getId() == id) {
						trobat = true;
						SessionFactory sfa = Model_AE5.crearSessio();
						vista.mostrarResultat(
								Llibre.RecuperarLlibres(s).get(i).getTitol() + " s'ha borrat correctament");
						Llibre.BorrarLlibre(id, sfa);

						vista.getTxtIdBorrar().setText("");

					}

				}
				if (!trobat) {

					vista.mostrarResultat("No s'ha trobat un llibre amb el Id: " + id);
				}

			}
		};
		vista.getBtnBorrar().addActionListener(ALBorrar);

		ALMostrarInfo = new ActionListener() {
			public void actionPerformed(ActionEvent actionevent) {
				String mensaje = "";
				int id = Integer.parseInt(vista.getTxtIdInfo().getText());
				boolean trobat = false;
				for (int i = 0; i < Llibre.RecuperarLlibres(s).size(); i++) {
					if (Llibre.RecuperarLlibres(s).get(i).getId() == id) {
						trobat = true;
						Llibre l = Llibre.RecuperarLLibre(id, s);
						mensaje += "Id: " + l.getId() + "\n";
						mensaje += "Titol: " + l.getTitol() + "\n";
						mensaje += "Autor: " + l.getAutor() + "\n";
						mensaje += "Any de publicació: " + l.getAny_publicacio() + "\n";
						mensaje += "Any de naixement: " + l.getAny_naixement() + "\n";
						mensaje += "Editorial: " + l.getEditorial() + "\n";
						mensaje += "Pàgines: " + l.getNum_pags();
					}

				}

				if (!trobat) {

					mensaje = "No s'ha trobat un llibre amb el Id: " + id;
				}
				vista.mostrarResultat(mensaje);
				vista.getTxtIdInfo().setText("");

			}
		};
		vista.getBtnMontrarInfo().addActionListener(ALMostrarInfo);

		ALTancar = new ActionListener() {
			public void actionPerformed(ActionEvent actionevent) {
				vista.mostrarResultat("Biblioteca Fran Pastor ha tancat");

				System.exit(1);

			}
		};
		vista.getBtnTancar().addActionListener(ALTancar);

	}

}
