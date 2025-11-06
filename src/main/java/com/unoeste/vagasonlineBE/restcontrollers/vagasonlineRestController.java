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
    public ResponseEntity<Object> getVagasById(@PathVariable String registro)
    {
        Vaga vaga = vagasService.getOne(registro);
        if (vaga != null)
            return ResponseEntity.ok(vaga);
        else
            return ResponseEntity.badRequest().body(new Erro("Registro de Vaga Não Encontrado"));
    }
    @GetMapping(value = "cargo/get-all")
    public ResponseEntity<Object> getAllCargo()
    {
        List<Cargo> cargoList = cargoService.getAll();
        if (!cargoList.isEmpty())
            return ResponseEntity.ok(cargoList);
        else
            return ResponseEntity.badRequest().body((new Erro("Nenhum Cargo Encontrado")));
    }


}
