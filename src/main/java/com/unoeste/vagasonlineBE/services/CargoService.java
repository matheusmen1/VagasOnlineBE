package com.unoeste.vagasonlineBE.services;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.unoeste.vagasonlineBE.entities.Cargo;
import com.unoeste.vagasonlineBE.util.Conexao;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CargoService
{

    public List<Cargo> getAll()
    {
        List<Cargo> cargos = new ArrayList<>();
        try
        {
            MongoCollection<Document> collection = Conexao.getCollection("vagas_online","cargos");
            MongoCursor<Document> cursor = collection.find().iterator();
            while (cursor.hasNext())
            {
                cargos.add(new Gson().fromJson(cursor.next().toJson(), Cargo.class));
            }
        }catch(Exception e)
        {
            System.out.println(e);
        }
       return cargos;

    }
}
