package AE3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import AE3.Model;
import AE3.Vista_AE3;

public class Controlador_AE3 {

	private Model model;
	private Vista_AE3 vista;
	private ActionListener ALCrear, ALBorrar, ALMostrarInfo, ALBuscarLlibre, ALActualitzar, ALMostrarLlibres, ALTancar;

	Controlador_AE3(Model model, Vista_AE3 vista) {

		this.model = model;
		this.vista = vista;
		control();
	}

	private void control() {

		ALCrear = new ActionListener() {
			public void actionPerformed(ActionEvent actionevent) {

				String t = vista.getTxtTitolCrear().getText();
				String a = vista.getTxtAutorCrear().getText();
				int any = Integer.parseInt(vista.getTxtAnyCrear().getText());
				String ed = vista.getTxtEditorialCrear().getText();
				int pags = Integer.parseInt(vista.getTxtPagsCrear().getText());
				Model.Biblioteca.Llibre l = new Model.Biblioteca.Llibre(Model.Biblioteca.Llibre.getUltimoId(), t, a,
						any, ed, pags);
				vista.mostrarResultat("El Llibre " + t + " s'ha creat correctament amb el ID: "
						+ Model.Biblioteca.Llibre.CrearLlibre(l) + "");
				vista.getTxtTitolCrear().setText("");
				vista.getTxtAutorCrear().setText("");
				vista.getTxtAnyCrear().setText("");
				vista.getTxtEditorialCrear().setText("");
				vista.getTxtPagsCrear().setText("");
			}
		};
		vista.getBtnCrear().addActionListener(ALCrear);

		ALBuscarLlibre = new ActionListener() {
			public void actionPerformed(ActionEvent actionevent) {

				int id = Integer.parseInt(vista.getTxtIdActu().getText());
				Model.Biblioteca.Llibre l = Model.Biblioteca.Llibre.recuperarLlibre(id);
				int Id = l.getIdentificador();
				if (Id != 0) {
					vista.getBtnActualitzar().setEnabled(true);

					vista.getTxtTitolActu().setText(l.getTitol());
					vista.getTxtAutorActu().setText(l.getAutor());
					vista.getTxtAnyActu().setText(l.getAnypubli() + "");
					vista.getTxtEditorialActu().setText(l.getEditorial());
					vista.getTxtPagsActu().setText(l.getPags() + "");

				} else {
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
				int any = Integer.parseInt(vista.getTxtAnyActu().getText());
				String ed = vista.getTxtEditorialActu().getText();
				int pags = Integer.parseInt(vista.getTxtPagsActu().getText());

				vista.getBtnActualitzar().setEnabled(false);
				vista.getTxtTitolActu().setText("");
				vista.getTxtAutorActu().setText("");
				vista.getTxtAnyActu().setText("");
				vista.getTxtEditorialActu().setText("");
				vista.getTxtPagsActu().setText("");

				Model.Biblioteca.Llibre ll = new Model.Biblioteca.Llibre(id, t, a, any, ed, pags);
				Model.Biblioteca.Llibre.actualitzaLlibre(id, ll);
				vista.mostrarResultat("S' ha actualitzat " + ll.getTitol() + " correctamnet");
			}
		};
		vista.getBtnActualitzar().addActionListener(ALActualitzar);

		ALMostrarLlibres = new ActionListener() {
			public void actionPerformed(ActionEvent actionevent) {
				String mensaje = "";
				for (int i = 0; i < Model.Biblioteca.getLlibres().size(); i++) {
					mensaje += Model.Biblioteca.getLlibres().get(i).getTitol() + "   ID: "
							+ Model.Biblioteca.getLlibres().get(i).getIdentificador() + "\n";
				}
				vista.mostrarResultat(mensaje);
			}
		};
		vista.getBtnMostrarLlibres().addActionListener(ALMostrarLlibres);

		ALBorrar = new ActionListener() {
			public void actionPerformed(ActionEvent actionevent) {
				int id = Integer.parseInt(vista.getTxtIdBorrar().getText());
				Model.Biblioteca.Llibre l = Model.Biblioteca.Llibre.recuperarLlibre(id);
				int Id = l.getIdentificador();
				if (Id != 0) {
					Model.Biblioteca.Llibre.borrarLlibre(id);
					vista.mostrarResultat(l.getTitol() + " s'ha borrat correctament");
					vista.getTxtIdBorrar().setText("");
				} else {
					vista.mostrarResultat("No s'ha trobat un llibre amb el Id: " + id);
				}

			}
		};
		vista.getBtnBorrar().addActionListener(ALBorrar);

		ALMostrarInfo = new ActionListener() {
			public void actionPerformed(ActionEvent actionevent) {
				String mensaje = "";
				int id = Integer.parseInt(vista.getTxtIdInfo().getText());
				Model.Biblioteca.Llibre l = Model.Biblioteca.Llibre.recuperarLlibre(id);
				mensaje += "Id: " + l.getIdentificador() + "\n";
				mensaje += "Titol: " + l.getTitol() + "\n";
				mensaje += "Autor: " + l.getAutor() + "\n";
				mensaje += "Any de publicació: " + l.getAnypubli() + "\n";
				mensaje += "Editorial: " + l.getEditorial() + "\n";
				mensaje += "Pàgines: " + l.getPags();
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
