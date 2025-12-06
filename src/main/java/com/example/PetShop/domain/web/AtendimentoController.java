package com.example.PetShop.domain.web;

import org.springframework.web.bind.annotation.RestController;

import com.example.PetShop.domain.entity.Atendimento;
import com.example.PetShop.domain.service.AtendimentoService;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/atendimentos")
public class AtendimentoController {

    private final AtendimentoService atendimentoService;

    public AtendimentoController(AtendimentoService atendimentoService) {
        this.atendimentoService = atendimentoService;
    }

    @GetMapping("/listaAtendimentos")
    public ResponseEntity<List<Atendimento>> listarTodos() {
        List<Atendimento> atendimentos = atendimentoService.listarTodos();
        return ResponseEntity.ok(atendimentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atendimento> buscarPorId(@PathVariable Long id) {
        return atendimentoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/abertos")
    public ResponseEntity<List<Atendimento>> listarAtendimentosAbertos() {
        List<Atendimento> atendimentosAbertos = atendimentoService.listarTodos().stream()
            .filter(atendimento -> "ABERTO".equals(atendimento.getStatus()))
            .toList();
        return ResponseEntity.ok(atendimentosAbertos);
    }

    @PostMapping
    public ResponseEntity<Atendimento> salvar(@RequestBody Atendimento atendimento) {
        Atendimento novoAtendimento = atendimentoService.salvar(atendimento);
        return ResponseEntity.ok(novoAtendimento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Atendimento> atualizar(@PathVariable Long id, @RequestBody Atendimento atendimento) {
        Atendimento atualizado = atendimentoService.atualizar(id, atendimento);
        return ResponseEntity.ok(atualizado);
    }

    @PutMapping("/{id}/fechar")
    public ResponseEntity<Atendimento> atualizarStatus(@PathVariable Long id, @RequestBody Atendimento atendimento) {
        atendimento.setId(id);
        Atendimento atualizado = atendimentoService.atualizarStatus(atendimento);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        atendimentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
