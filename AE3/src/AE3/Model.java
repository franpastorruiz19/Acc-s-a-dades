package AE3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Model {
	public static class Biblioteca {
		private static ArrayList<Llibre> llibres = new ArrayList();
		
		/*
		 * - Mètode: getLlibres 
		 * - Descripció: Retorna el array llibres. 
		 * - Paràmeters d'entrada: Ningún paràmeter d'entrada. 
		 * - Paràmeters d'eixida: Array<Llibre> llibres.
		 * 
		 */
		
		public static ArrayList<Llibre> getLlibres() {
			return llibres;
		}

		/*
		 * - Mètode: setLlibres 
		 * - Descripció: Afegix un nou valor a al array llibres. 
		 * - Paràmeters d'entrada: Array<Llibre> llibres.
		 * - Paràmeters d'eixida: Ningún paràmeter d'eixida.
		 * 
		 */
		
		public static void setLlibres(ArrayList<Llibre> l) {
			llibres = l;
		}
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
			/*
			 * - Mètodes: get
			 * - Descripció: Retornen els valors de les variables de la clase Llibre. 
			 * - Paràmeters d'entrada: Ningún paràmeter d'entrada. 
			 * - Paràmeters d'eixida: String titol, autor, editorial y int identificador, anypubli, pags y
			 *   static int ultimoid..
			 * 
			 */
			
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

			public static int getUltimoId() {
				return ultimoid;
			}

			/*
			 * - Mètode: set 
			 * - Descripció: Afegix un nou valor a les variables de la clase Llibre. 
			 * - Paràmeters d'entrada: String titol, autor, editorial; int identificador, anypubli, pags y
			 *   static int ultimoid.
			 * - Paràmeters d'eixida: Ningún paràmeter d'eixida.
			 * 
			 */

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
			 * - Mètode: recuperarLlibre 
			 * - Descripció: torna un objecte llibre a partir d’un identificador. 
			 * - Paràmeters d'entrada: int id. 
			 * - Paràmeters d'eixida: Llibre l
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
								int anyo = Integer
										.parseInt(eElement.getElementsByTagName("Any").item(0).getTextContent());
								String edit = eElement.getElementsByTagName("Editorial").item(0).getTextContent();
								int pags = Integer
										.parseInt(eElement.getElementsByTagName("Pags").item(0).getTextContent());
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
			 * - Mètode: recuperarTots 
			 * - Descripció: torna una llista amb tots els llibres
			 *   de la biblioteca. - Paràmeters d'entrada: Ningún paràmeter d'entrada -
			 *   Paràmeters d'eixida: ArrayList<Llibre> ll
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
			 * - Mètode: mostrarLlibre 
			 * - Descripció: mostra els atributs del llibre per pantalla. 
			 * - Paràmeters d'entrada: Llibre l 
			 * - Paràmeters d'eixida: Ningún paràmeter d'eixida
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
			 * - Mètode: CrearLlibre 
			 * - Descripció: Junt amb els llibres que ja habien en la biblioteca crea 
			 *   un nou llibre com a XML a partir de les dades proporcionades
			 *   per l’usuari, torna l’identificador del llibre. 
			 * - Paràmeters d'entrada:Llibre l 
			 * - Paràmeters d'eixida: int identificador
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
			 * - Mètode: actualitzaLlibre 
			 * - Descripció: actualitza (modifica) la informació d’un objecte 
			 *   llibre al xml a partir d’un identificador. 
			 * - Paràmeters d'entrada: int id 
			 * - Paràmeters d'eixida: Ningún paràmeter d'entrada.
			 */

			public static void actualitzaLlibre(int id, Llibre l) {

				llibres.get(id - 1).títol = l.títol;
				llibres.get(id - 1).autor = l.autor;
				llibres.get(id - 1).anypubli = l.anypubli;
				llibres.get(id - 1).editorial = l.editorial;
				llibres.get(id - 1).pags = l.pags;

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
			 * - Mètode: borrarLlibre 
			 * - Descripció: borra un objecte llibre del xml a partir d’un identificador. 
			 * - Paràmeters d'entrada: int id 
			 * - Paràmeters d'eixida: Ningún paràmeter d'entrada.
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
	}
}
