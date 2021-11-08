package AE4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;

import AE4.Model_AE4;
import AE4.Vista_AE4;

public class Controlador_AE4 {

		private Model_AE4 model;
		private Vista_AE4 vista;
		private ActionListener ALConsultar;

		Controlador_AE4(Model_AE4 model, Vista_AE4 vista) {

			this.model = model;
			this.vista = vista;
			control();
		}

		private void control() {
			Connection con=model.getConexió();
			
//			Funcions per a llegir el fitxer csv i fer els inserts a la base de dades
//			ArrayList<String> al =model.ContingutFitxer();
//			model.insertar(al,con);
			
			String select1="SELECT titol, autor, any_publicacio FROM llibres WHERE any_naixement<1950;";
			vista.getTbl_Select1().setModel(model.consultaSelect(con,select1));
			
			String select2="SELECT editorial FROM llibres WHERE any_publicacio>=2000;";
			vista.getTbl_Select2().setModel(model.consultaSelect(con,select2));
		
			ALConsultar = new ActionListener() {
				public void actionPerformed(ActionEvent actionevent) {
					String consulta=vista.getTxtConsulta().getText();
					String[] tipusConsult=consulta.split(" ");
					
					//tipusConsult[0] fa referencia al tipus de consulta (primera paraula de la consulta).
					if(tipusConsult[0].toLowerCase().equals("select")) {
						vista.getTbl_Consulta().setModel(model.consultaSelect(con,consulta));
					}else {
						vista.mostrarResultat(model.consultes(con, consulta));
					}
					
					vista.getTbl_Select1().setModel(model.consultaSelect(con,select1));
					vista.getTbl_Select2().setModel(model.consultaSelect(con,select2));
				
				}
			};
			vista.getBtnConsultar().addActionListener(ALConsultar);
			
		}
	}

