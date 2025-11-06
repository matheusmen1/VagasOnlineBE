package com.unoeste.vagasonlineBE.util;

import com.mongodb.client.*;
import org.bson.Document;

public class Conexao
{
    private static MongoDatabase database = null;
    private static MongoClient mongoClient = null;
    public static MongoCollection<Document> getCollection(String colecao)
    {
        if (mongoClient == null)
        {
            String connectionString = "mongodb://localhost:27017";
            mongoClient = MongoClients.create(connectionString);
            database = mongoClient.getDatabase("vagas_online");
        }
        MongoCollection<Document> collection = database.getCollection(colecao);
        return collection;
    }

}
