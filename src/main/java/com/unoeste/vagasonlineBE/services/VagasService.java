package com.unoeste.vagasonlineBE.services;

import com.google.gson.Gson;
import com.mongodb.client.*;
import com.unoeste.vagasonlineBE.entities.Cargo;
import com.unoeste.vagasonlineBE.entities.Vaga;
import com.unoeste.vagasonlineBE.util.Conexao;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static com.mongodb.client.model.Filters.eq;

@Service
public class VagasService {
    public List<Vaga> getAll() {
        List<Vaga> vagaList = new ArrayList<>();
        try {
            MongoCollection<Document> collection = Conexao.getCollection("vagas_online", "vagas");
            MongoCursor<Document> cursor = collection.find().iterator();
            while (cursor.hasNext())
                vagaList.add(new Gson().fromJson(cursor.next().toJson(), Vaga.class));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return vagaList;
    }

    public Vaga getOne(String registro) {
        Vaga vaga = null;
        try {
            MongoCollection<Document> collection = Conexao.getCollection("vagas_online", "vagas");
            Bson filter = eq("registro", Pattern.compile(registro + "(?i)"));
            MongoCursor<Document> cursor = collection.find(filter).iterator();
            vaga = new Gson().fromJson(cursor.next().toJson(), Vaga.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return vaga;
    }

    public Vaga criar(Vaga vaga) {
        try {
            MongoCollection<Document> collection = Conexao.getCollection("vagas_online", "vagas");
            collection.insertOne(Document.parse(new Gson().toJson(vaga)));
            return vaga;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Vaga atualizar(Vaga vaga) {
        try{
            MongoCollection<Document> collection = Conexao.getCollection("vagas_online", "vagas");
            Bson filtro = eq("registro", vaga.getRegistro());
            Document vagaRecuperada = collection.find(filtro).first();

            if(vagaRecuperada != null){
                ObjectId id = vagaRecuperada.getObjectId("_id");
                Document vagaAtt = Document.parse(new Gson().toJson(vaga));
                vagaAtt.put("_id", id);
                collection.replaceOne(eq("_id", id), vagaAtt);
                return vaga;
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Vaga apagar(String registro) {
        try{
            //recupera a collection do banco mandando o nome e a collection necessária
            MongoCollection<Document> collection = Conexao.getCollection("vagas_online", "vagas");
            Bson filtro = eq("registro", registro);
            Document vagaRecuperada = collection.find(filtro).first();

            if(vagaRecuperada != null){
                //aqui então eu vou excluir a vaga especificada
                //Vaga vagaExcluida = new Vaga();
                //pegar o id da vaga que quero excluir
                ObjectId id = vagaRecuperada.getObjectId("_id");
                collection.deleteOne(eq("_id", id));

                return new Gson().fromJson(vagaRecuperada.toJson(), Vaga.class); //já retorna um objeto do tipo Vaga
                //vagaRecuperada.get("");
                //return vagaExcluida;
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null; //caso não tenha o registro para excluir
    }

}
