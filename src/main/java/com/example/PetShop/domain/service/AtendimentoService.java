package com.example.PetShop.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.PetShop.domain.entity.Atendimento;
import com.example.PetShop.domain.repository.AtendimentoRepository;

@Service
public class AtendimentoService {

    private final AtendimentoRepository atendimentoRepository;

    public AtendimentoService(AtendimentoRepository atendimentoRepository) {
        this.atendimentoRepository = atendimentoRepository;
    }

    public List<Atendimento> listarTodos() {
        return atendimentoRepository.findAll();
    }

    public Optional<Atendimento> buscarPorId(Long id) {
        return atendimentoRepository.findById(id);
    }

    public Atendimento salvar(Atendimento atendimento) {
        atendimento.setId(null);
        return atendimentoRepository.save(atendimento);
    }

    public Atendimento atualizar(Long id, Atendimento atendimentoAtualizado) {
        return atendimentoRepository.findById(id)
                .map(atendimento -> {
                    atendimento.setData(atendimentoAtualizado.getData());
                    atendimento.setPet(atendimentoAtualizado.getPet());
                    atendimento.setServico(atendimentoAtualizado.getServico());
                    atendimento.setObservacoes(atendimentoAtualizado.getObservacoes());
                    return atendimentoRepository.save(atendimento);
                })
                .orElseThrow(() -> new RuntimeException("Atendimento não encontrado"));
    }

    public void deletar(Long id) {
        atendimentoRepository.deleteById(id);
    }

    public Atendimento atualizarStatus(Atendimento atendimentoAtualizado) {
        return atendimentoRepository.findById(atendimentoAtualizado.getId())
                .map(atendimento -> {
                    atendimento.setStatus(atendimentoAtualizado.getStatus());
                    return atendimentoRepository.save(atendimento);
                })
                .orElseThrow(() -> new RuntimeException("Atendimento não encontrado"));
    }
}
