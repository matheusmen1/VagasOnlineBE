package com.unoeste.vagasonlineBE.util;

import com.mongodb.client.*;
import org.bson.Document;

public class Conexao
{
    private static MongoDatabase database = null;
    private static MongoClient mongoClient = null;
//    public static MongoCollection<Document> getCollection(String colecao)
//    {
//        if (mongoClient == null)
//        {
//            String connectionString = "mongodb://localhost:27017";
//            mongoClient = MongoClients.create(connectionString);
//            database = mongoClient.getDatabase("vagas_online");
//        }
//        MongoCollection<Document> collection = database.getCollection(colecao);
//        return collection;
//    }

    //retorno a colletion de uma database específica
    public static MongoCollection<Document> getCollection(String dbName, String collectionName) {
        String connectionString = "mongodb://localhost:27017";
        mongoClient = MongoClients.create(connectionString);
        database = mongoClient.getDatabase(dbName);
        return database.getCollection(collectionName);
    }

    //retorno a database passa por parâmetro
    public static MongoDatabase getDB(String dbName) {
        String connectionString = "mongodb://localhost:27017";
        mongoClient = MongoClients.create(connectionString);
        database = mongoClient.getDatabase(dbName);
        return database;
    }

    //retorno o cliente com a conexão especificada
    public static MongoClient getClient(String connectionString) {
        mongoClient = MongoClients.create(connectionString);
        return mongoClient;
    }

}
