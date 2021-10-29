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
		 * - M�tode: getLlibres 
		 * - Descripci�: Retorna el array llibres. 
		 * - Par�meters d'entrada: Ning�n par�meter d'entrada. 
		 * - Par�meters d'eixida: Array<Llibre> llibres.
		 * 
		 */
		
		public static ArrayList<Llibre> getLlibres() {
			return llibres;
		}

		/*
		 * - M�tode: setLlibres 
		 * - Descripci�: Afegix un nou valor a al array llibres. 
		 * - Par�meters d'entrada: Array<Llibre> llibres.
		 * - Par�meters d'eixida: Ning�n par�meter d'eixida.
		 * 
		 */
		
		public static void setLlibres(ArrayList<Llibre> l) {
			llibres = l;
		}
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
			/*
			 * - M�todes: get
			 * - Descripci�: Retornen els valors de les variables de la clase Llibre. 
			 * - Par�meters d'entrada: Ning�n par�meter d'entrada. 
			 * - Par�meters d'eixida: String titol, autor, editorial y int identificador, anypubli, pags y
			 *   static int ultimoid..
			 * 
			 */
			
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

			public static int getUltimoId() {
				return ultimoid;
			}

			/*
			 * - M�tode: set 
			 * - Descripci�: Afegix un nou valor a les variables de la clase Llibre. 
			 * - Par�meters d'entrada: String titol, autor, editorial; int identificador, anypubli, pags y
			 *   static int ultimoid.
			 * - Par�meters d'eixida: Ning�n par�meter d'eixida.
			 * 
			 */

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
			 * - M�tode: recuperarLlibre 
			 * - Descripci�: torna un objecte llibre a partir d�un identificador. 
			 * - Par�meters d'entrada: int id. 
			 * - Par�meters d'eixida: Llibre l
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
			 * - M�tode: recuperarTots 
			 * - Descripci�: torna una llista amb tots els llibres
			 *   de la biblioteca. - Par�meters d'entrada: Ning�n par�meter d'entrada -
			 *   Par�meters d'eixida: ArrayList<Llibre> ll
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
			 * - M�tode: mostrarLlibre 
			 * - Descripci�: mostra els atributs del llibre per pantalla. 
			 * - Par�meters d'entrada: Llibre l 
			 * - Par�meters d'eixida: Ning�n par�meter d'eixida
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
			 * - M�tode: CrearLlibre 
			 * - Descripci�: Junt amb els llibres que ja habien en la biblioteca crea 
			 *   un nou llibre com a XML a partir de les dades proporcionades
			 *   per l�usuari, torna l�identificador del llibre. 
			 * - Par�meters d'entrada:Llibre l 
			 * - Par�meters d'eixida: int identificador
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
			 * - M�tode: actualitzaLlibre 
			 * - Descripci�: actualitza (modifica) la informaci� d�un objecte 
			 *   llibre al xml a partir d�un identificador. 
			 * - Par�meters d'entrada: int id 
			 * - Par�meters d'eixida: Ning�n par�meter d'entrada.
			 */

			public static void actualitzaLlibre(int id, Llibre l) {

				llibres.get(id - 1).t�tol = l.t�tol;
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
			 * - M�tode: borrarLlibre 
			 * - Descripci�: borra un objecte llibre del xml a partir d�un identificador. 
			 * - Par�meters d'entrada: int id 
			 * - Par�meters d'eixida: Ning�n par�meter d'entrada.
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
	}
}
