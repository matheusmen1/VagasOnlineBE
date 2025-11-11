package com.unoeste.vagasonlineBE.services;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.unoeste.vagasonlineBE.entities.Interesses;
import com.unoeste.vagasonlineBE.entities.Vaga;
import com.unoeste.vagasonlineBE.util.Conexao;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

import static com.mongodb.client.model.Filters.eq;

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

    public Interesses getOne(String registro) {
        Interesses interesse = null;
        try {
            MongoCollection<Document> collection = Conexao.getCollection("vagas_online", "interesses");
            Bson filter = eq("vaga.registro", Pattern.compile(registro + "(?i)"));
            MongoCursor<Document> cursor = collection.find(filter).iterator();
            interesse = new Gson().fromJson(cursor.next().toJson(), Interesses.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return interesse;
    }
}
