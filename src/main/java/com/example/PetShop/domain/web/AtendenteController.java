package com.example.PetShop.domain.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PetShop.domain.entity.Atendente;
import com.example.PetShop.domain.service.AtendenteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/atendentes")
public class AtendenteController {
    
    private final AtendenteService atendenteService;
    
    public AtendenteController(AtendenteService atendenteService) {
        this.atendenteService = atendenteService;
    }

    @GetMapping("/listaAtendentes")
    public ResponseEntity<List<Atendente>> listarTodos() {
        List<Atendente> atendentes = atendenteService.listarTodos();
        return ResponseEntity.ok(atendentes);
    }
    
    @GetMapping("/{id}")
    public Atendente getAtendenteById(@PathVariable Long id) {
        return atendenteService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<Atendente> criarAtendente(@RequestBody Atendente atendente) {
        Atendente novoAtendente = atendenteService.salvar(atendente);
        return ResponseEntity.ok(novoAtendente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Atendente> atualizarAtendente(@PathVariable Long id, @RequestBody Atendente atendente) {
        Atendente atualizado = atendenteService.atualizar(id, atendente);
        return ResponseEntity.ok(atualizado);
    }

    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAtendente(@PathVariable Long id) {
        atendenteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
    
}
