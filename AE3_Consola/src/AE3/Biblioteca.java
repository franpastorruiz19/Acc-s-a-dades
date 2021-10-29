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
		private String t�tol;
		private String autor;
		private int anypubli;
		private String editorial;
		private int pags;
		private static int ultimoid;
		

		public Llibre() {
		}

		public Llibre(int id, String t, String a, int ap, String ed, int p) {
			identificador = id;
			t�tol = t;
			autor = a;
			anypubli = ap;
			editorial = ed;
			pags = p;

		}

		public int getIdentificador() {
			return identificador;
		}

		public String getTitol() {
			return t�tol;
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

		public void setT�tol(String value) {
			t�tol = value;
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
		 * - M�tode: recuperarLlibre - Descripci�: torna un objecte llibre a partir d�un
		 * identificador. - Par�meters d'entrada: int id. - Par�meters d'eixida: Llibre
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
		 * - M�tode: recuperarTots - Descripci�: torna una llista amb tots els llibres
		 * de la biblioteca. - Par�meters d'entrada: Ning�n par�meter d'entrada -
		 * Par�meters d'eixida: ArrayList<Llibre> ll
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
		 * - M�tode: mostrarLlibre - Descripci�: mostra els atributs del llibre per
		 * pantalla. - Par�meters d'entrada: Llibre l - Par�meters d'eixida: Ning�n
		 * par�meter d'eixida
		 * 
		 */
		public static void mostrarLlibre(Llibre l) {
			System.out.println("T�tol: " + l.t�tol);
			System.out.println("Id: " + l.identificador);
			System.out.println("Autor: " + l.autor);
			System.out.println("Any de publicaci�: " + l.anypubli);
			System.out.println("Editorial: " + l.editorial);
			System.out.println("P�gines: " + l.pags);
		}

		/*
		 * - M�tode: CrearLlibre - Descripci�: Junt amb els llibres que ja habien en la
		 * biblioteca crea un nou llibre com a XML a partir de les dades proporcionades
		 * per l�usuari, torna l�identificador del llibre. - Par�meters d'entrada:
		 * Llibre l - Par�meters d'eixida: int identificador
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
					Element t�tol = doc.createElement("Titol");
					t�tol.appendChild(doc.createTextNode(String.valueOf(llibres.get(i).getTitol())));
					llib.appendChild(t�tol);
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
		 * - M�tode: actualitzaLlibre - Descripci�: actualitza (modifica) la informaci�
		 * d�un objecte llibre al xml a partir d�un identificador. - Par�meters
		 * d'entrada: int id - Par�meters d'eixida: Ning�n par�meter d'entrada.
		 * 
		 */

		public static void actualitzaLlibre(int id) {
			Scanner sc = new Scanner(System.in);
			Llibre l = llibres.get(id - 1);
			System.out.println("1. T�tol: " + llibres.get(id - 1).getTitol());
			System.out.println("2. Autro: " + llibres.get(id - 1).getAutor());
			System.out.println("3. Any de publicaci�: " + llibres.get(id - 1).getAnypubli());
			System.out.println("4. Editorial: " + llibres.get(id - 1).getEditorial());
			System.out.println("5. P�gines: " + llibres.get(id - 1).getPags());
			System.out.print("Escriu la opci� que vullgues modificar (1..5): ");
			String opcio = sc.nextLine();

			switch (opcio) {
			case "1":
				System.out.print("Nou T�tol: ");
				String t = sc.nextLine();
				llibres.get(id - 1).t�tol = t;
				break;

			case "2":
				System.out.print("Nou Autor: ");
				String a = sc.nextLine();
				llibres.get(id - 1).autor = a;
				break;

			case "3":
				System.out.print("Nou any de publicaci�: ");
				int any = sc.nextInt();
				llibres.get(id - 1).anypubli = any;
				break;

			case "4":
				System.out.print("Nova Editorial: ");
				String ed = sc.nextLine();
				llibres.get(id - 1).editorial = ed;
				break;

			case "5":
				System.out.print("Nou nombre de p�gines: ");
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
					Element t�tol = doc.createElement("Titol");
					t�tol.appendChild(doc.createTextNode(String.valueOf(llibres.get(i).getTitol())));
					llib.appendChild(t�tol);
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
		 * - M�tode: borrarLlibre - Descripci�: borra un objecte llibre del xml a partir
		 * d�un identificador. - Par�meters d'entrada: int id - Par�meters d'eixida:
		 * Ning�n par�meter d'entrada.
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
					Element t�tol = doc.createElement("Titol");
					t�tol.appendChild(doc.createTextNode(String.valueOf(llibres.get(i).getTitol())));
					llib.appendChild(t�tol);
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
	 * - M�tode: main - Descripci�: Mostra un men� per a elegir la funcionalitat que
	 * vols utilitzar y les executa segons la teua elecci�. - Par�meters d'entrada:
	 * int id - Par�meters d'eixida: Ning�n par�meter d'entrada.
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
			System.out.println("2. Mostrar informaci� detallada de un llibre");
			System.out.println("3. Crear un nou llibre");
			System.out.println("4. Actualitzar Llibre");
			System.out.println("5. Borrar llibre");
			System.out.println("6. Tanca la biblioteca");
			System.out.print("Escriu la opci� que desitjes (1..6): ");
			try {
				opcio = br.readLine();
			} catch (IOException e1) {
				// TODO Bloque catch generado autom�ticamente
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
				System.out.print("T�tol del Llibre: ");
				String tit = br.readLine();
				System.out.print("Autor del Llibre: ");
				String aut = br.readLine();
				System.out.print("Any de publicaci� del Llibre: ");
				int a = Integer.parseInt(br.readLine());
				System.out.print("Editorial del Llibre: ");
				String ed = br.readLine();
				System.out.print("Nombre de p�gines del Llibre: ");
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
				System.out.println("Elija una opci� v�lida");
				break;
			}
			System.out.println("Presiona quansevol tecla..");
			
			try {
				br.readLine();
			} catch (IOException e1) {
				// TODO Bloque catch generado autom�ticamente
				e1.printStackTrace();
			};
			
			System.out.println("");
			System.out.println("");
		} while (!opcio.equals("6"));

	}

}
