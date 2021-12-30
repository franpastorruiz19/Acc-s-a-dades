package AE6;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.mongodb.client.MongoCollection;

public class Controlador_AE6 {
	private Model_AE6 model;
	private Vista_AE6 vista;
	private ActionListener ALCrear, ALBorrar, ALMostrarInfo, ALBuscarLlibre, ALActualitzar, ALMostrarLlibres, ALTancar;

	public Controlador_AE6(Model_AE6 model, Vista_AE6 vista) {

		this.model = model;
		this.vista = vista;
		control();
	}

	private void control() {

		MongoCollection c = model.CrearConexió();

		ALCrear = new ActionListener() {
			public void actionPerformed(ActionEvent actionevent) {
				String id = Llibre.ControlarId(Llibre.RecuperarLlibres(c));
				String t = vista.getTxtTitolCrear().getText();
				String a = vista.getTxtAutorCrear().getText();
				String any_publi = vista.getTxtAnyPubliCrear().getText();
				String any_naix = vista.getTxtAnyNaixCrear().getText();
				String ed = vista.getTxtEditorialCrear().getText();
				String pags = vista.getTxtPagsCrear().getText();
				Llibre l = new Llibre(id, t, a, any_publi, any_naix, ed, pags);
				Llibre.CrearLlibre(l, c);
				vista.mostrarResultat("El Llibre " + t + " s'ha creat correctament amb el id "+id);

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
				String id = vista.getTxtIdActu().getText();
				boolean trobat = false;
				for (int i = 0; i < Llibre.RecuperarLlibres(c).size(); i++) {
					if (Llibre.RecuperarLlibres(c).get(i).getId().equals(id)) {
						trobat = true;
						vista.getBtnActualitzar().setEnabled(true);

						vista.getTxtTitolActu().setText(Llibre.RecuperarLlibres(c).get(i).getTitol());
						vista.getTxtAutorActu().setText(Llibre.RecuperarLlibres(c).get(i).getAutor());
						vista.getTxtAnyPubliActu().setText(Llibre.RecuperarLlibres(c).get(i).getAny_publicacio() + "");
						vista.getTxtAnyNaixActu().setText(Llibre.RecuperarLlibres(c).get(i).getAny_naixement() + "");
						vista.getTxtEditorialActu().setText(Llibre.RecuperarLlibres(c).get(i).getEditorial());
						vista.getTxtPagsActu().setText(Llibre.RecuperarLlibres(c).get(i).getNum_pags() + "");
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
				String id = vista.getTxtIdActu().getText();
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

				Llibre ll = new Llibre(id, t, a, anyPubli, anyNaix, ed, pags);
				Llibre.ActualitzaLLibre(c, id, ll);
				vista.mostrarResultat("S' ha actualitzat " + ll.getTitol() + " correctamnet");
			}
		};
		vista.getBtnActualitzar().addActionListener(ALActualitzar);

		ALMostrarLlibres = new ActionListener() {
			public void actionPerformed(ActionEvent actionevent) {
				ArrayList<Llibre> ll = Llibre.RecuperarLlibres(c);
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
				String id = vista.getTxtIdBorrar().getText();
				boolean trobat = false;
				for (int i = 0; i < Llibre.RecuperarLlibres(c).size(); i++) {
					if (Llibre.RecuperarLlibres(c).get(i).getId().equals(id)) {
						trobat = true;

						vista.mostrarResultat(
								Llibre.RecuperarLlibres(c).get(i).getTitol() + " s'ha borrat correctament");
						Llibre.BorrarLlibre(id, c);

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
				String id = vista.getTxtIdInfo().getText();
				boolean trobat = false;
				for (int i = 0; i < Llibre.RecuperarLlibres(c).size(); i++) {
					if (Llibre.RecuperarLlibres(c).get(i).getId().equals(id)) {
						trobat = true;
						Llibre l = Llibre.RecuperarLLibre(id, c);
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
