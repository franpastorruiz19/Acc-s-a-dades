package AE6;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class Model_AE6 {

	
	/*
	 * - M�tode: CrearConexi� 
	 * - Descripci�: Crea una conexi� amb la base de dades en MongoDB. 
	 * - Par�meters d'entrada: Ning�n par�meter d'entrada.
	 * - Par�meters d'eixida: MongoCollection colecci�.
	 * 
	 */
	
	public static MongoCollection CrearConexi�() {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("Biblioteca");
		MongoCollection<Document> colecci� = database.getCollection("Llibres");
		return colecci�;
	}

}
