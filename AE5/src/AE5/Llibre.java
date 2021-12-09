package AE5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Llibre {
	private int id;
	private String titol;

	private String autor;
	private String any_publicacio;
	private String any_naixement;

	private String editorial;
	private String num_pags;

	public Llibre() {
	}

	public Llibre(String t, String a, String an, String ap, String ed, String p) {
		titol = t;
		autor = a;
		any_publicacio = ap;
		any_naixement = an;
		editorial = ed;
		num_pags = p;

	}
	/*
	 * - Mètodes: get 
	 * - Descripció: Retornen els valors de les variables de la clase Llibre. 
	 * - Paràmeters d'entrada: Ningún paràmeter d'entrada. 
	 * - Paràmeters d'eixida: String titol, autor, editorial y int identificador, anypubli, pags.
	 * 
	 */

	public int getId() {
		return id;
	}

	public String getTitol() {
		return titol;
	}

	public String getAutor() {
		return autor;
	}

	public String getAny_publicacio() {
		return any_publicacio;
	}

	public String getAny_naixement() {
		return any_naixement;
	}

	public String getEditorial() {
		return editorial;
	}

	public String getNum_pags() {
		return num_pags;
	}

	/*
	 * - Mètode: set 
	 * - Descripció: Afegix un nou valor a les variables de la clase Llibre. 
	 * - Paràmeters d'entrada: String titol, autor, editorial; int
	 *   identificador, anypubli, pags. 
	 * - Paràmeters d'eixida: Ningún paràmeter d'eixida.
	 * 
	 */

	public void setId(int value) {
		id = value;
	}

	public void setTitol(String titol) {
		this.titol = titol;
	}

	public void setAny_publicacio(String value) {
		any_publicacio = value;
	}

	public void setAny_naixement(String value) {
		any_naixement = value;
	}

	public void setNum_pags(String value) {
		num_pags = value;
	}

	public void setAutor(String value) {
		autor = value;
	}

	public void setEditorial(String value) {
		editorial = value;
	}

	/*
	 * - Mètode: CrearLlibre 
	 * - Descripció: Junt amb els llibres que ja habien en la biblioteca crea 
	 *   un nou llibre com a XML a partir de les dades proporcionades
	 *   per l’usuari, torna l’identificador del llibre. 
	 * - Paràmeters d'entrada:Llibre l, Session s 
	 * - Paràmeters d'eixida: int identificador
	 * 
	 */
	
	public static int CrearLlibre(Llibre l, Session s) {
		s.beginTransaction();
		Serializable id = s.save(l);
		s.getTransaction().commit();
		int identificador = l.getId();
		return identificador;
	}

	/*
	 * - Mètode: RecuperarLlibres
	 * - Descripció: torna una llista amb tots els llibres de la biblioteca. 
	 * - Paràmeters d'entrada: Session s 
	 * - Paràmeters d'eixida: ArrayList<Llibre> ll
	 * 
	 */
	
	public static ArrayList<Llibre> RecuperarLlibres(Session s) {
		s.beginTransaction();
		List llibres = new ArrayList();
		llibres = s.createQuery("FROM Llibre").list();
		s.getTransaction().commit();
		ArrayList<Llibre> ll = (ArrayList<Llibre>) llibres;
		return ll;

	}


	/*
	 * - Mètode: RecuperarLLibre 
	 * - Descripció: torna un objecte llibre a partir d’un identificador. 
	 * - Paràmeters d'entrada: int id, Session s. 
	 * - Paràmeters d'eixida: Llibre l
	 * 
	 */
	
	public static Llibre RecuperarLLibre(int id, Session s) {
		s.beginTransaction();
		Llibre l = (Llibre) s.get(Llibre.class, id);
		s.getTransaction().commit();

		return l;
	}
	
	/*
	 * - Mètode: ActualitzaLLibre 
	 * - Descripció: actualitza (modifica) la informació d’un objecte 
	 *   llibre al xml a partir d’un identificador. 
	 * - Paràmeters d'entrada: int id, Session s, Llibre l
	 * - Paràmeters d'eixida: Ningún paràmeter d'eixida.
	 */

	public static void ActualitzaLLibre(Session s, int id, Llibre l) {
		s.beginTransaction();
		Llibre llibre = (Llibre) s.load(Llibre.class, id);
		llibre.setTitol(l.titol);
		llibre.setAutor(l.autor);
		llibre.setAny_publicacio(l.any_publicacio);
		llibre.setAny_naixement(l.any_naixement);
		llibre.setEditorial(l.editorial);
		llibre.setNum_pags(l.num_pags);
		s.update(llibre);
		s.getTransaction().commit();
	}
	

	/*
	 * - Mètode: BorrarLlibre 
	 * - Descripció: borra un objecte llibre del xml a partir d’un identificador. 
	 * - Paràmeters d'entrada: int id 
	 * - Paràmeters d'eixida: Ningún paràmeter d'eixida.
	 * 
	 */

	public static void BorrarLlibre(int id, SessionFactory sf) {
		Session s = sf.openSession();
		s.beginTransaction();
		Llibre llibre = new Llibre();
		llibre.setId(id);
		s.delete(llibre);
		s.getTransaction().commit();
		s.close();
	}

}
