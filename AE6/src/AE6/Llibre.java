package AE6;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;

public class Llibre {
	private String id;
	private String titol;

	private String autor;
	private String any_publicacio;
	private String any_naixement;

	private String editorial;
	private String num_pags;

	public Llibre() {
	}

	/*
	 * - M�tode: Llibre
	 * - Descripci�: Constructor de la clase Llibre. 
	 * - Par�meters d'entrada: String i, t, a, an, ap, ed, p.
	 * - Par�meters d'eixida: Ning�n par�meter d'eixida.
	 * 
	 */
	
	public Llibre(String i, String t, String a, String an, String ap, String ed, String p) {
		id = i;
		titol = t;
		autor = a;
		any_publicacio = ap;
		any_naixement = an;
		editorial = ed;
		num_pags = p;

	}
	/*
	 * - M�todes: get 
	 * - Descripci�: Retornen els valors de les variables de la clase Llibre. 
	 * - Par�meters d'entrada: Ning�n par�meter d'entrada. 
	 * - Par�meters d'eixida: String titol, autor, editorial, id, any_publicacio, any_naixement, num_pags.
	 * 
	 */

	public String getId() {
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
	 * - M�tode: set 
	 * - Descripci�: Afegix un nou valor a les variables de la clase Llibre. 
	 * - Par�meters d'entrada: String titol, autor, editorial, id,any_publicacio, any_naixement, num_pags.
	 * - Par�meters d'eixida: Ning�n par�meter d'eixida.
	 * 
	 */

	public void setId(String id) {
		this.id = id;
	}

	public void setTitol(String titol) {
		this.titol = titol;
	}

	public void setAny_publicacio(String any_publicacio) {
		this.any_publicacio = any_publicacio;
	}

	public void setAny_naixement(String any_naixement) {
		this.any_naixement = any_naixement;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public void setNum_pags(String num_pags) {
		this.num_pags = num_pags;
	}

	/*
	 * - M�tode: ControlarId 
	 * - Descripci�: Recorre la llista de llibres y retorna el ultim id+1 seguent aixina el primer disponible. 
	 * - Par�meters d'entrada: ArrayList <Llibre> l.
	 * - Par�meters d'eixida: String ui.
	 * 
	 */
	
	public static String ControlarId(ArrayList<Llibre> l) {
		int ultim = 0;
		for (int i = 0; i < l.size(); i++) {
			if (ultim < Integer.parseInt(l.get(i).getId())) {
				ultim = Integer.parseInt(l.get(i).getId());
			}
		}
		ultim += 1;
		String ui = String.valueOf(ultim);
		return ui;

	}

	/*
	 * - M�tode: CrearLlibre 
	 * - Descripci�: Junt amb els llibres que ja habien en la biblioteca crea un nou llibre a la
	 *   base de dades a partir de les dades proporcionades per l�usuari. 
	 * - Par�meters d'entrada:Llibre l, MongoCollection c 
	 * - Par�meters d'eixida: Ning�n par�meter d'eixida.
	 * 
	 */

	public static void CrearLlibre(Llibre l, MongoCollection c) {
		Document doc = new Document();
		doc.append("id", l.id);
		doc.append("titol", l.titol);
		doc.append("autor", l.autor);
		doc.append("any_naixement", l.any_naixement);
		doc.append("any_publicacio", l.any_publicacio);
		doc.append("editorial", l.editorial);
		doc.append("num_pags", l.num_pags);
		c.insertOne(doc);

	}

	/*
	 * - M�tode: RecuperarLlibres 
	 * - Descripci�: torna una llista amb tots els llibres de la biblioteca. 
	 * - Par�meters d'entrada: MongoCollection c 
	 * - Par�meters d'eixida: ArrayList<Llibre> llista
	 * 
	 */

	public static ArrayList<Llibre> RecuperarLlibres(MongoCollection c) {
		MongoCursor<Document> cursor = c.find().iterator();
		ArrayList<Llibre> llista = new ArrayList();
		while (cursor.hasNext()) {
			JSONObject obj = new JSONObject(cursor.next().toJson());
			String i = obj.getString("id");
			String t = obj.getString("titol");
			String a = obj.getString("autor");
			String an = obj.getString("any_naixement");
			String ap = obj.getString("any_publicacio");
			String e = obj.getString("editorial");
			String np = obj.getString("num_pags");
			Llibre l = new Llibre(i, t, a, an, ap, e, np);
			llista.add(l);
		}
		return llista;
	}

	/*
	 * - M�tode: RecuperarLLibre 
	 * - Descripci�: torna un objecte llibre a partir d�un identificador. 
	 * - Par�meters d'entrada: String id, MongoCollection c. 
	 * - Par�meters d'eixida: Llibre l
	 * 
	 */

	public static Llibre RecuperarLLibre(String id, MongoCollection c) {

		MongoCursor<Document> cursor = c.find(eq("id", id)).iterator();
		Llibre l = new Llibre();
		while (cursor.hasNext()) {
			JSONObject obj = new JSONObject(cursor.next().toJson());
			String i = obj.getString("id");
			String t = obj.getString("titol");
			String a = obj.getString("autor");
			String an = obj.getString("any_naixement");
			String ap = obj.getString("any_publicacio");
			String e = obj.getString("editorial");
			String np = obj.getString("num_pags");
			l = new Llibre(i, t, a, an, ap, e, np);
		}
		return l;
	}

	/*
	 * - M�tode: ActualitzaLLibre 
	 * - Descripci�: actualitza (modifica) la informaci� d�un objecte llibre a partir d�un identificador. 
	 * - Par�meters d'entrada: String id, MongoCollection c, Llibre l 
	 * - Par�meters d'eixida: Ning�n par�meter d'eixida.
	 */

	public static void ActualitzaLLibre(MongoCollection c, String id, Llibre l) {

		c.updateOne(eq("id", id), new Document("$set", new Document("titol", l.titol)));
		c.updateOne(eq("id", id), new Document("$set", new Document("autor", l.autor)));
		c.updateOne(eq("id", id), new Document("$set", new Document("any_naixement", l.any_naixement)));
		c.updateOne(eq("id", id), new Document("$set", new Document("any_publicacio", l.any_publicacio)));
		c.updateOne(eq("id", id), new Document("$set", new Document("editorial", l.editorial)));
		c.updateOne(eq("id", id), new Document("$set", new Document("num_pags", l.num_pags)));
	}

	/*
	 * - M�tode: BorrarLlibre 
	 * - Descripci�: borra un objecte llibre de la base de dades a partir d�un identificador. 
	 * - Par�meters d'entrada: String id, MongoCollection c 
	 * - Par�meters d'eixida: Ning�n par�meter d'eixida.
	 * 
	 */

	public static void BorrarLlibre(String id, MongoCollection c) {
		c.deleteOne(eq("id", id));

	}
}
