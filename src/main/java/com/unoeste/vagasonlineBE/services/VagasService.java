package com.unoeste.vagasonlineBE.services;

import com.google.gson.Gson;
import com.mongodb.client.*;
import com.unoeste.vagasonlineBE.entities.Cargo;
import com.unoeste.vagasonlineBE.entities.Vaga;
import com.unoeste.vagasonlineBE.util.Conexao;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static com.mongodb.client.model.Filters.eq;

@Service
public class VagasService
{
    public List<Vaga> getAll()
    {
        List<Vaga> vagaList = new ArrayList<>();
        try {
            MongoCollection<Document> collection = Conexao.getCollection("vagas");
            MongoCursor<Document> cursor = collection.find().iterator();
            while (cursor.hasNext())
                vagaList.add(new Gson().fromJson(cursor.next().toJson(),Vaga.class));
        } catch (Exception e) {
            System.out.println(e);

        }
        return vagaList;
    }

    public Vaga getOne(String registro)
    {
        Vaga vaga = null;
        try {
            MongoCollection<Document> collection = Conexao.getCollection("vagas");
            Bson filter = eq("registro", Pattern.compile(registro+"(?i)"));
            MongoCursor<Document> cursor = collection.find(filter).iterator();
            vaga = new Gson().fromJson(cursor.next().toJson(),Vaga.class);

        }catch (Exception e)
        {
            System.out.println(e);
        }
        return vaga;
    }


}
