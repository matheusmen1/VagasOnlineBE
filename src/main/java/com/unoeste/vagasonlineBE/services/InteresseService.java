package com.unoeste.vagasonlineBE.services;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.unoeste.vagasonlineBE.entities.Interesse;
import com.unoeste.vagasonlineBE.util.Conexao;
import org.bson.Document;
import org.springframework.stereotype.Service;

@Service
public class InteresseService
{
    public Interesse save(Interesse interesse)
    {
        try{
            MongoCollection<Document> collection = Conexao.getCollection("interesses");
            collection.insertOne(Document.parse(new Gson().toJson(interesse)));
            return interesse;
        }catch(Exception e){
            return null;
        }

    }
}
