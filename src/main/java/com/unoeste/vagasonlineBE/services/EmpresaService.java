package com.unoeste.vagasonlineBE.services;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.unoeste.vagasonlineBE.entities.Empresa;
import com.unoeste.vagasonlineBE.util.Conexao;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpresaService {

    public List<Empresa> getAll() {
        List<Empresa> empresaList = new ArrayList<>();
        try {
            MongoCollection<Document> collection = Conexao.getCollection("vagas_online", "empresas");
            MongoCursor<Document> cursor = collection.find().iterator();
            while (cursor.hasNext())
                empresaList.add(new Gson().fromJson(cursor.next().toJson(), Empresa.class));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return empresaList;
    }
}
