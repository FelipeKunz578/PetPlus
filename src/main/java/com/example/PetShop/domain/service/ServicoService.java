package com.example.PetShop.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.PetShop.domain.entity.Servico;
import com.example.PetShop.domain.repository.ServicoRepository;

@Service
public class ServicoService {
    
  
    private final ServicoRepository servicoRepository;

    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public List<Servico> listarTodos() {
        return servicoRepository.findAll();
    }

    public Optional<Servico> buscarPorId(Long id) {
        return servicoRepository.findById(id);
    }

    public Servico salvar(Servico servico) {
        servico.setId(null);
        // calcula duracao em minutos a partir do campo duracao (formato HH:mm)
        servico.setDuracaoMinutos(parseDuracaoToMinutes(servico.getDuracao()));
        return servicoRepository.save(servico);
    }

    public Servico atualizar(Long id, Servico servicoAtualizado) {
        return servicoRepository.findById(id)
                .map(servico -> {
                    servico.setDescricao(servicoAtualizado.getDescricao());
                    servico.setValor(servicoAtualizado.getValor());
                    servico.setDuracao(servicoAtualizado.getDuracao());
                    servico.setObservacoes(servicoAtualizado.getObservacoes());
                    // atualiza duracao_minutos também
                    servico.setDuracaoMinutos(parseDuracaoToMinutes(servicoAtualizado.getDuracao()));
                    return servicoRepository.save(servico);
                })
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
    }

    private Integer parseDuracaoToMinutes(String duracao) {
        if (duracao == null || duracao.isBlank()) return 0;
        try {
            // aceita formatos HH:mm ou mm
            if (duracao.contains(":")) {
                String[] parts = duracao.split(":");
                int h = Integer.parseInt(parts[0]);
                int m = Integer.parseInt(parts[1]);
                return h * 60 + m;
            } else {
                return Integer.parseInt(duracao);
            }
        } catch (Exception e) {
            return 0;
        }
    }

    public void deletar(Long id) {
        servicoRepository.deleteById(id);
    }

}
