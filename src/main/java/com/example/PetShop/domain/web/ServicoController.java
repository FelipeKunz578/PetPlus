package com.example.PetShop.domain.web;

import com.example.PetShop.domain.entity.Servico;
import com.example.PetShop.domain.service.ServicoService;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/servicos")
public class ServicoController {

	  private final ServicoService ServicoService;

    public ServicoController(ServicoService servicoService) {
        this.ServicoService = servicoService;
    }

    @GetMapping("/listaServico")
    public ResponseEntity<List<Servico>> listarTodos() {
        List<Servico> Servicos = ServicoService.listarTodos();
        return ResponseEntity.ok(Servicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> buscarPorId(@PathVariable Long id) {
        return ServicoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Servico> salvar(@RequestBody Servico servico) {
        // validação básica para evitar erro 500 por constraints do banco
        if (servico.getDescricao() == null || servico.getDescricao().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        if (servico.getValor() == null) {
            return ResponseEntity.badRequest().build();
        }

        Servico novoServico = ServicoService.salvar(servico);
        return ResponseEntity.ok(novoServico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> atualizar(@PathVariable Long id, @RequestBody Servico servico) {
        if (servico.getDescricao() == null || servico.getDescricao().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        if (servico.getValor() == null) {
            return ResponseEntity.badRequest().build();
        }

        Servico Atualizado = ServicoService.atualizar(id, servico);
        return ResponseEntity.ok(Atualizado);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        ServicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
