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
	 * - Mètode: CrearConexió 
	 * - Descripció: Crea una conexió amb la base de dades en MongoDB. 
	 * - Paràmeters d'entrada: Ningún paràmeter d'entrada.
	 * - Paràmeters d'eixida: MongoCollection colecció.
	 * 
	 */
	
	public static MongoCollection CrearConexió() {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("Biblioteca");
		MongoCollection<Document> colecció = database.getCollection("Llibres");
		return colecció;
	}

}
