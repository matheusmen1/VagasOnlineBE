package com.unoeste.vagasonlineBE.services;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.unoeste.vagasonlineBE.entities.Interesses;
import com.unoeste.vagasonlineBE.util.Conexao;
import org.bson.Document;
import org.springframework.stereotype.Service;

@Service
public class InteresseService
{
    public Interesses save(Interesses interesses)
    {
        try{
            MongoCollection<Document> collection = Conexao.getCollection("vagas_online","interesses");
            collection.insertOne(Document.parse(new Gson().toJson(interesses)));
            return interesses;
        }catch(Exception e){
            return null;
        }

    }
}
