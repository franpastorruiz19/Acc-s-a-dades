package AE1;

import java.util.Scanner;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AE1 {

	static boolean ControlError(File f) {
		boolean bool = true;
		if (!f.exists()) {
			bool = false;
		} else if (!f.canExecute()) {
			bool = false;
		} else if (!f.canRead()) {
			bool = false;
		} else if (!f.canWrite()) {
			bool = false;
		}
		return bool;
	}

	public static void GetInformacio(String[] arg) {
		File directorio = new File(arg[0]);
		System.out.println("Nombre: " + directorio.getName());
		if (directorio.isDirectory()) {
			System.out.println("Tipo: Directori");
			File[] lista = directorio.listFiles();
			int contador = 0;
			for (int i = 0; i < lista.length; i++) {
				contador++;
			}
			System.out.println("Nombre d'elements: " + contador);
			System.out.println("Espai Total: " + directorio.getTotalSpace());
			System.out.println("Espai Lliure: " + directorio.getFreeSpace());
			System.out.println("Espai Ocupat: " + (directorio.getTotalSpace() - directorio.getFreeSpace()));

		} else {
			System.out.println("Tipo: Fitxer");
			System.out.println("Grandària en bytes: " + directorio.length());
		}
		System.out.println("Ruta Absoluta: " + directorio.getAbsolutePath());
		long UltimaModificacion = directorio.lastModified();

		String formato = "dd-MM-yyyy hh:mm";
		SimpleDateFormat FormatoFecha = new SimpleDateFormat(formato);

		Date fecha = new Date(UltimaModificacion);
		System.out.println("Data de l’última modificació: " + FormatoFecha.format(fecha));
		System.out.println("Oculto: " + directorio.isHidden());
	}

	public static void CreaCarpeta(String[] arg) {

		File directorio = new File(arg[0]);
		Scanner sc = new Scanner(System.in);
		System.out.print("Escriu el nom del directori: ");
		String direc = sc.nextLine();
		
		boolean accion = new File(directorio.getAbsolutePath() + "/" + direc).mkdirs();
	}

	public static void CreaFitxer(String[] arg) {

		File directorio = new File(arg[0]);
		Scanner sc = new Scanner(System.in);
		System.out.print("Escriu el nom del directori on vols crear el fitxer: ");
		String direc = sc.nextLine();
		File d = new File(directorio.getAbsolutePath() + "/" + direc);

		if (ControlError(d)) {
			System.out.print("Escriu el nom del fitxer amb l'extensió: ");
			String fitxer = sc.nextLine();
			File accion = new File(directorio.getAbsolutePath() + "/" + direc + "/" + fitxer);
			try {
				accion.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Directorio no existente");
		}
	}

	public static void Elimina(String[] arg) {
		File directorio = new File(arg[0]);
		Scanner sc = new Scanner(System.in);
		String opcion = "";

		System.out.print("Vols eliminar un directori o un fitxer (d/f): ");
		opcion = sc.nextLine();
		
		if (opcion.equals("d") || opcion.equals("directori")) {
			System.out.print("Escriu el nom del directori: ");
			String direc = sc.nextLine();
			
			File accion = new File(directorio.getAbsolutePath() + "/" + direc);
			if (ControlError(accion)) {
				accion.delete();
			} else {
				System.out.println("Directori Inexistent");
			}

		} else if (opcion.equals("f") || opcion.equals("fitxer")) {
			System.out.print("Escriu el nom del directori on esta el fitxer que vols eliminar: ");
			String direc = sc.nextLine();
			File d = new File(directorio.getAbsolutePath() + "/" + direc);
			if (ControlError(d)) {

				System.out.print("Escriu el nom del fitxer que vols eliminar amb l'extensió: ");
				String fitxer = sc.nextLine();
				File accion = new File(directorio.getAbsolutePath() + "/" + direc + "/" + fitxer);
				if (ControlError(accion)) {
					accion.delete();
				} else {
					System.out.println("Fitxer Inexistent");
				}
			} else {
				System.out.println("Directori Inexistent");
			}
		} else {
			System.out.println("No ha escrit una opció válida");
		}
	}

	public static void Renomena(String[] arg) {
		File directorio = new File(arg[0]);
		Scanner sc = new Scanner(System.in);
		String opcion = "";

		System.out.print("Vols renomenar un directori o un fitxer (d/f): ");
		opcion = sc.nextLine();
		
		if (opcion.equals("d") || opcion.equals("directori")) {
			System.out.print("Escriu el nom del directori: ");
			String direc = sc.nextLine();
			File accion = new File(directorio.getAbsolutePath() + "/" + direc);
			if (ControlError(accion)) {

				System.out.print("Escriu el nou nom per al directori: ");
				String renom = sc.nextLine();

				File nou = new File(directorio.getAbsolutePath() + "/" + renom);
				try {
					accion.renameTo(nou);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Directori Inexistent");
			}

		} else if (opcion.equals("f") || opcion.equals("fitxer")) {
			System.out.print("Escriu el nom del directori on esta el fitxer que vols renombrar: ");
			String direc = sc.nextLine();
			File d = new File(directorio.getAbsolutePath() + "/" + direc);
			if (ControlError(d)) {
				System.out.print("Escriu el nom del fitxer que vols renombrar amb l'extensió: ");
				String fitxer = sc.nextLine();
				File accion = new File(directorio.getAbsolutePath() + "/" + direc + "/" + fitxer);
				if (ControlError(accion)) {
					System.out.print("Escriu el nou nom per al fitxer amb l'extensió: ");
					String renomfit = sc.nextLine();

					File noufit = new File(directorio.getAbsolutePath() + "/" + direc + "/" + renomfit);
					try {
						accion.renameTo(noufit);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("Fitxer Inexistent");
				}
			} else {
				System.out.println("Directori Inexistent");
			}
		} else {
			System.out.println("No ha escrito una opcion válida");
		}
	}

	public static void main(String[] args) {
		
		String opcion;
		Scanner teclado = new Scanner(System.in);
		do {
			System.out.println("1. Recopilar informació del teu fitxer/directori");
			System.out.println("2. Crear Directori");
			System.out.println("3. Crear Fitxer");
			System.out.println("4. Eliminar fitxer/directori ");
			System.out.println("5. Renomenar fitxer/directori ");
			System.out.println("6. Eixir del programa ");
			System.out.print("Elegix una opció (1..6): ");

			
			opcion = teclado.nextLine();
			
			switch (opcion) {
			case "1":
				GetInformacio(args);
				break;
			case "2":
				CreaCarpeta(args);
				break;
			case "3":
				CreaFitxer(args);
				break;
			case "4":
				Elimina(args);
				break;
			case "5":
				Renomena(args);
				break;
			case "6":
				System.exit(0);
				break;
			}

		} while (!opcion.equals("6"));
	}

}
