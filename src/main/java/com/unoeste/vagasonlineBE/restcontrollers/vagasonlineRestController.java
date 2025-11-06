package com.unoeste.vagasonlineBE.restcontrollers;

import com.unoeste.vagasonlineBE.entities.Cargo;
import com.unoeste.vagasonlineBE.services.CargoService;
import com.unoeste.vagasonlineBE.util.Erro;
import com.unoeste.vagasonlineBE.entities.Vaga;
import com.unoeste.vagasonlineBE.services.VagasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "apis")
public class vagasonlineRestController
{
    @Autowired
    private VagasService vagasService;
    @Autowired
    private CargoService cargoService;

    @GetMapping(value = "vagas/get-all")
    public ResponseEntity<Object> getAllVagas()
    {
        List<Vaga> vagaList = vagasService.getAll();
        if (!vagaList.isEmpty())
            return ResponseEntity.ok(vagasService.getAll());
        else
            return ResponseEntity.badRequest().body(new Erro("Nenhuma Vaga Encontrada"));
    }
    @GetMapping(value = "vagas/get-one/{registro}")
    public ResponseEntity<Object> getVagaByRegistro(@PathVariable String registro)
    {
        Vaga vaga = vagasService.getOne(registro);
        if (vaga != null)
            return ResponseEntity.ok(vaga);
        else
            return ResponseEntity.badRequest().body(new Erro("Registro de Vaga Não Encontrado"));
    }

    //get para o cargo
    @GetMapping(value = "cargo/get-all")
    public ResponseEntity<Object> getAllCargo()
    {
        List<Cargo> cargoList = cargoService.getAll();
        if (!cargoList.isEmpty())
            return ResponseEntity.ok(cargoList);
        else
            return ResponseEntity.badRequest().body((new Erro("Nenhum Cargo Encontrado")));
    }

    //post para vaga
    @PostMapping("vagas")
    public ResponseEntity<Object> criarVaga(@RequestBody Vaga vaga)
    {
        //verifica a existência da vaga
        Vaga novaVaga = vagasService.getOne(vaga.getRegistro());
        if (novaVaga == null) //ainda não existe a respectiva vaga
        {
            novaVaga = vagasService.criar(vaga);
            if (novaVaga != null)
                return ResponseEntity.ok(novaVaga);
        }

        //enviar o erro específico
        return ResponseEntity.badRequest().body(new Erro("Erro ao criar uma nova Vaga!!"));
    }

    //put para vaga
    @PutMapping("vagas")
    public ResponseEntity<Object> alterarVaga(@RequestBody Vaga vaga)
    {
        //verifica a existência da vaga
        Vaga vagaAtt = vagasService.atualizar(vaga);
        if(vagaAtt != null)
            return ResponseEntity.ok(vagaAtt);
        //enviar o erro específico
        return ResponseEntity.badRequest().body(new Erro("Erro ao editar a Vaga!!"));
    }
}
