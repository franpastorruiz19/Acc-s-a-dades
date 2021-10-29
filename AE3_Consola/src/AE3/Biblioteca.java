package AE3;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Biblioteca {
	
	private static ArrayList<Llibre> llibres = new ArrayList();
	
	public static class Llibre {
		private int identificador;
		private String títol;
		private String autor;
		private int anypubli;
		private String editorial;
		private int pags;
		private static int ultimoid;
		

		public Llibre() {
		}

		public Llibre(int id, String t, String a, int ap, String ed, int p) {
			identificador = id;
			títol = t;
			autor = a;
			anypubli = ap;
			editorial = ed;
			pags = p;

		}

		public int getIdentificador() {
			return identificador;
		}

		public String getTitol() {
			return títol;
		}

		public String getAutor() {
			return autor;
		}

		public int getAnypubli() {
			return anypubli;
		}

		public String getEditorial() {
			return editorial;
		}

		public int getPags() {
			return pags;
		}

		public int getUltimoId() {
			return ultimoid;
		}

		public void setIdentificador(int value) {
			identificador = value;
		}

		public void setTítol(String value) {
			títol = value;
		}

		public void setAny(int value) {
			anypubli = value;
		}

		public void setPags(int value) {
			pags = value;
		}

		public void setAutor(String value) {
			autor = value;
		}

		public void setEditorial(String value) {
			editorial = value;
		}

		public static void setUltimoId(int value) {
			ultimoid = value;
		}

		/*
		 * - Mètode: recuperarLlibre - Descripció: torna un objecte llibre a partir d’un
		 * identificador. - Paràmeters d'entrada: int id. - Paràmeters d'eixida: Llibre
		 * l
		 * 
		 */

		public static Llibre recuperarLlibre(int id) {
			boolean encontrado = false;
			Llibre l = new Llibre();
			try {

				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document document = dBuilder.parse(new File("Biblioteca.xml"));
				NodeList nodeList = document.getElementsByTagName("Llibre");
				for (int i = 0; i < nodeList.getLength(); i++) {
					Node node = nodeList.item(i);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) node;
						int iden = Integer.parseInt(eElement.getAttribute("id"));
						if (iden == id) {
							String titol = eElement.getElementsByTagName("Titol").item(0).getTextContent();
							String autor = eElement.getElementsByTagName("Autor").item(0).getTextContent();
							int anyo = Integer.parseInt(eElement.getElementsByTagName("Any").item(0).getTextContent());
							String edit = eElement.getElementsByTagName("Editorial").item(0).getTextContent();
							int pags = Integer.parseInt(eElement.getElementsByTagName("Pags").item(0).getTextContent());
							l = new Llibre(id, titol, autor, anyo, edit, pags);
							encontrado = true;
						}

					}
				}
				if (!encontrado) {
					System.out.println("No s'ha trobat un llibre amb aquest identificador");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return l;
		}

		/*
		 * - Mètode: recuperarTots - Descripció: torna una llista amb tots els llibres
		 * de la biblioteca. - Paràmeters d'entrada: Ningún paràmeter d'entrada -
		 * Paràmeters d'eixida: ArrayList<Llibre> ll
		 * 
		 */

		public static ArrayList<Llibre> recuperarTots() {
			int ultimoID = 1;
			ArrayList<Llibre> ll = new ArrayList();
			try {

				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document document = dBuilder.parse(new File("Biblioteca.xml"));
				NodeList nodeList = document.getElementsByTagName("Llibre");
				for (int i = 0; i < nodeList.getLength(); i++) {
					Node node = nodeList.item(i);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) node;
						int id = Integer.parseInt(eElement.getAttribute("id"));
						String titol = eElement.getElementsByTagName("Titol").item(0).getTextContent();
						String autor = eElement.getElementsByTagName("Autor").item(0).getTextContent();
						int anyo = Integer.parseInt(eElement.getElementsByTagName("Any").item(0).getTextContent());
						String edit = eElement.getElementsByTagName("Editorial").item(0).getTextContent();
						int pags = Integer.parseInt(eElement.getElementsByTagName("Pags").item(0).getTextContent());
						Llibre l = new Llibre(id, titol, autor, anyo, edit, pags);
						ll.add(l);
						ultimoID = id;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			Llibre.setUltimoId(ultimoID + 1);
			return ll;
		}

		/*
		 * - Mètode: mostrarLlibre - Descripció: mostra els atributs del llibre per
		 * pantalla. - Paràmeters d'entrada: Llibre l - Paràmeters d'eixida: Ningún
		 * paràmeter d'eixida
		 * 
		 */
		public static void mostrarLlibre(Llibre l) {
			System.out.println("Títol: " + l.títol);
			System.out.println("Id: " + l.identificador);
			System.out.println("Autor: " + l.autor);
			System.out.println("Any de publicació: " + l.anypubli);
			System.out.println("Editorial: " + l.editorial);
			System.out.println("Pàgines: " + l.pags);
		}

		/*
		 * - Mètode: CrearLlibre - Descripció: Junt amb els llibres que ja habien en la
		 * biblioteca crea un nou llibre com a XML a partir de les dades proporcionades
		 * per l’usuari, torna l’identificador del llibre. - Paràmeters d'entrada:
		 * Llibre l - Paràmeters d'eixida: int identificador
		 * 
		 */

		public static int CrearLlibre(Llibre l) {

			int identificador = Llibre.ultimoid;
			Llibre.ultimoid++;
			llibres.add(l);
			try {
				DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
				DocumentBuilder build = dFact.newDocumentBuilder();
				Document doc = build.newDocument();
				Element raiz = doc.createElement("Biblioteca");
				doc.appendChild(raiz);
				for (int i = 0; i < llibres.size(); i++) {
					Element llib = doc.createElement("Llibre");
					String id = String.valueOf(llibres.get(i).getIdentificador());
					llib.setAttribute("id", id);
					raiz.appendChild(llib);
					Element títol = doc.createElement("Titol");
					títol.appendChild(doc.createTextNode(String.valueOf(llibres.get(i).getTitol())));
					llib.appendChild(títol);
					Element autor = doc.createElement("Autor");
					autor.appendChild(doc.createTextNode(String.valueOf(llibres.get(i).getAutor())));
					llib.appendChild(autor);
					Element any = doc.createElement("Any");
					any.appendChild(doc.createTextNode(String.valueOf(llibres.get(i).getAnypubli())));
					llib.appendChild(any);
					Element edi = doc.createElement("Editorial");
					edi.appendChild(doc.createTextNode(String.valueOf(llibres.get(i).getEditorial())));
					llib.appendChild(edi);
					Element pags = doc.createElement("Pags");
					pags.appendChild(doc.createTextNode(String.valueOf(llibres.get(i).getPags())));
					llib.appendChild(pags);
				}
				TransformerFactory tranFactory = TransformerFactory.newInstance(); // Crear serializador
				Transformer aTransformer = tranFactory.newTransformer();
				aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
				aTransformer.setOutputProperty(OutputKeys.METHOD, "xml");
				aTransformer.setOutputProperty("{http://xml.Apache.org/xslt}indent-amount", "4");
				aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
				DOMSource source = new DOMSource(doc);
				try {
					FileWriter fw = new FileWriter("Biblioteca.xml"); // Definir el nombre del fichero y guardar
					StreamResult result = new StreamResult(fw);
					aTransformer.transform(source, result);
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (TransformerException ex) {
				System.out.println("Error escribiendo el documento");
			} catch (ParserConfigurationException ex) {
				System.out.println("Error construyendo el documento");
			}
			return identificador;
		}

		/*
		 * - Mètode: actualitzaLlibre - Descripció: actualitza (modifica) la informació
		 * d’un objecte llibre al xml a partir d’un identificador. - Paràmeters
		 * d'entrada: int id - Paràmeters d'eixida: Ningún paràmeter d'entrada.
		 * 
		 */

		public static void actualitzaLlibre(int id) {
			Scanner sc = new Scanner(System.in);
			Llibre l = llibres.get(id - 1);
			System.out.println("1. Títol: " + llibres.get(id - 1).getTitol());
			System.out.println("2. Autro: " + llibres.get(id - 1).getAutor());
			System.out.println("3. Any de publicació: " + llibres.get(id - 1).getAnypubli());
			System.out.println("4. Editorial: " + llibres.get(id - 1).getEditorial());
			System.out.println("5. Pàgines: " + llibres.get(id - 1).getPags());
			System.out.print("Escriu la opció que vullgues modificar (1..5): ");
			String opcio = sc.nextLine();

			switch (opcio) {
			case "1":
				System.out.print("Nou Títol: ");
				String t = sc.nextLine();
				llibres.get(id - 1).títol = t;
				break;

			case "2":
				System.out.print("Nou Autor: ");
				String a = sc.nextLine();
				llibres.get(id - 1).autor = a;
				break;

			case "3":
				System.out.print("Nou any de publicació: ");
				int any = sc.nextInt();
				llibres.get(id - 1).anypubli = any;
				break;

			case "4":
				System.out.print("Nova Editorial: ");
				String ed = sc.nextLine();
				llibres.get(id - 1).editorial = ed;
				break;

			case "5":
				System.out.print("Nou nombre de pàgines: ");
				int p = sc.nextInt();
				llibres.get(id - 1).pags = p;
				break;
			}

			try {
				DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
				DocumentBuilder build = dFact.newDocumentBuilder();
				Document doc = build.newDocument();
				Element raiz = doc.createElement("Biblioteca");
				doc.appendChild(raiz);
				for (int i = 0; i < llibres.size(); i++) {
					Element llib = doc.createElement("Llibre");
					String iden = String.valueOf(llibres.get(i).getIdentificador());
					llib.setAttribute("id", iden);
					raiz.appendChild(llib);
					Element títol = doc.createElement("Titol");
					títol.appendChild(doc.createTextNode(String.valueOf(llibres.get(i).getTitol())));
					llib.appendChild(títol);
					Element autor = doc.createElement("Autor");
					autor.appendChild(doc.createTextNode(String.valueOf(llibres.get(i).getAutor())));
					llib.appendChild(autor);
					Element any = doc.createElement("Any");
					any.appendChild(doc.createTextNode(String.valueOf(llibres.get(i).getAnypubli())));
					llib.appendChild(any);
					Element edi = doc.createElement("Editorial");
					edi.appendChild(doc.createTextNode(String.valueOf(llibres.get(i).getEditorial())));
					llib.appendChild(edi);
					Element pags = doc.createElement("Pags");
					pags.appendChild(doc.createTextNode(String.valueOf(llibres.get(i).getPags())));
					llib.appendChild(pags);
				}
				TransformerFactory tranFactory = TransformerFactory.newInstance(); // Crear serializador
				Transformer aTransformer = tranFactory.newTransformer();
				aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
				aTransformer.setOutputProperty(OutputKeys.METHOD, "xml");
				aTransformer.setOutputProperty("{http://xml.Apache.org/xslt}indent-amount", "4");
				aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
				DOMSource source = new DOMSource(doc);
				try {
					FileWriter fw = new FileWriter("Biblioteca.xml"); // Definir el nombre del fichero y guardar
					StreamResult result = new StreamResult(fw);
					aTransformer.transform(source, result);
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (TransformerException ex) {
				System.out.println("Error escribiendo el documento");
			} catch (ParserConfigurationException ex) {
				System.out.println("Error construyendo el documento");
			}
		}

		/*
		 * - Mètode: borrarLlibre - Descripció: borra un objecte llibre del xml a partir
		 * d’un identificador. - Paràmeters d'entrada: int id - Paràmeters d'eixida:
		 * Ningún paràmeter d'entrada.
		 * 
		 */

		public static void borrarLlibre(int id) {
			llibres.remove(id - 1);
			try {
				DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
				DocumentBuilder build = dFact.newDocumentBuilder();
				Document doc = build.newDocument();
				Element raiz = doc.createElement("Biblioteca");
				doc.appendChild(raiz);
				for (int i = 0; i < llibres.size(); i++) {
					Element llib = doc.createElement("Llibre");
					String iden = String.valueOf(llibres.get(i).getIdentificador());
					llib.setAttribute("id", iden);
					raiz.appendChild(llib);
					Element títol = doc.createElement("Titol");
					títol.appendChild(doc.createTextNode(String.valueOf(llibres.get(i).getTitol())));
					llib.appendChild(títol);
					Element autor = doc.createElement("Autor");
					autor.appendChild(doc.createTextNode(String.valueOf(llibres.get(i).getAutor())));
					llib.appendChild(autor);
					Element any = doc.createElement("Any");
					any.appendChild(doc.createTextNode(String.valueOf(llibres.get(i).getAnypubli())));
					llib.appendChild(any);
					Element edi = doc.createElement("Editorial");
					edi.appendChild(doc.createTextNode(String.valueOf(llibres.get(i).getEditorial())));
					llib.appendChild(edi);
					Element pags = doc.createElement("Pags");
					pags.appendChild(doc.createTextNode(String.valueOf(llibres.get(i).getPags())));
					llib.appendChild(pags);
				}
				TransformerFactory tranFactory = TransformerFactory.newInstance(); // Crear serializador
				Transformer aTransformer = tranFactory.newTransformer();
				aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
				aTransformer.setOutputProperty(OutputKeys.METHOD, "xml");
				aTransformer.setOutputProperty("{http://xml.Apache.org/xslt}indent-amount", "4");
				aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
				DOMSource source = new DOMSource(doc);
				try {
					FileWriter fw = new FileWriter("Biblioteca.xml"); // Definir el nombre del fichero y guardar
					StreamResult result = new StreamResult(fw);
					aTransformer.transform(source, result);
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (TransformerException ex) {
				System.out.println("Error escribiendo el documento");
			} catch (ParserConfigurationException ex) {
				System.out.println("Error construyendo el documento");
			}
		}
	}

	/*
	 * - Mètode: main - Descripció: Mostra un menú per a elegir la funcionalitat que
	 * vols utilitzar y les executa segons la teua elecció. - Paràmeters d'entrada:
	 * int id - Paràmeters d'eixida: Ningún paràmeter d'entrada.
	 * 
	 */

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		llibres = Llibre.recuperarTots();
		
		InputStreamReader isr=new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(isr);
		String opcio="";
		
		String tecla;
		do {
			System.out.println("BIBLIOTECA FRAN PASTOR");
			System.out.println("1. Mostrar tots el llibres de la Biblioteca");
			System.out.println("2. Mostrar informació detallada de un llibre");
			System.out.println("3. Crear un nou llibre");
			System.out.println("4. Actualitzar Llibre");
			System.out.println("5. Borrar llibre");
			System.out.println("6. Tanca la biblioteca");
			System.out.print("Escriu la opció que desitjes (1..6): ");
			try {
				opcio = br.readLine();
			} catch (IOException e1) {
				// TODO Bloque catch generado automáticamente
				e1.printStackTrace();
			};
			System.out.println("");
			switch (opcio) {
			case "1":

				for (int i = 0; i < llibres.size(); i++) {
					System.out.println(
							llibres.get(i).getTitol() + "   ID: " + llibres.get(i).getIdentificador());
				}
				System.out.println("");
				break;

			case "2":
				System.out.print("Escriu el ID del llibre: ");
				int ident = sc.nextInt();
				Llibre l = Llibre.recuperarLlibre(ident);
				int Id = l.getIdentificador();
				if (Id != 0) {
					Llibre.mostrarLlibre(l);
				}
				System.out.println("");
				break;

			case "3":
				try {
				System.out.print("Títol del Llibre: ");
				String tit = br.readLine();
				System.out.print("Autor del Llibre: ");
				String aut = br.readLine();
				System.out.print("Any de publicació del Llibre: ");
				int a = Integer.parseInt(br.readLine());
				System.out.print("Editorial del Llibre: ");
				String ed = br.readLine();
				System.out.print("Nombre de pàgines del Llibre: ");
				int p = Integer.parseInt(br.readLine());
				System.out.println("");
				Llibre ll = new Llibre(Llibre.ultimoid, tit, aut, a, ed, p);
				System.out.println("S'ha creat el llibre correctament amb el ID " + Llibre.CrearLlibre(ll));
				System.out.println("");
				}catch(IOException e) {
					e.printStackTrace();
				}
				break;

			case "4":
				System.out.print("Escriu el ID del llibre que vols modificar: ");
				int id = sc.nextInt();
				Llibre.actualitzaLlibre(id);
				System.out.println("");
				break;

			case "5":
				System.out.print("Escriu el ID del llibre que vols eliminar: ");
				int iden = sc.nextInt();
				Llibre.borrarLlibre(iden);
				System.out.println("");

				break;

			case "6":
				System.out.println("LA BIBLIOTECA HA TANCAT");
				System.exit(1);
				break;

			default:
				System.out.println("Elija una opció válida");
				break;
			}
			System.out.println("Presiona quansevol tecla..");
			
			try {
				br.readLine();
			} catch (IOException e1) {
				// TODO Bloque catch generado automáticamente
				e1.printStackTrace();
			};
			
			System.out.println("");
			System.out.println("");
		} while (!opcio.equals("6"));

	}

}
