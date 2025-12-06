package com.example.PetShop.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.PetShop.domain.entity.Atendente;
import com.example.PetShop.domain.repository.AtendenteRepository;

@Service
public class AtendenteService {
    
    private final AtendenteRepository atendenteRepository;

    public AtendenteService(AtendenteRepository atendenteRepository) {
        this.atendenteRepository = atendenteRepository;
    }

    public List<Atendente> listarTodos() {
        return atendenteRepository.findAll();
    }
    
    public Atendente buscarPorId(Long id) {
        return atendenteRepository.findById(id).orElse(null);
    }

    public Atendente salvar(Atendente atendente) {
        atendente.setId(null);
        return atendenteRepository.save(atendente);
    }

    public Atendente atualizar(Long id, Atendente atendenteAtualizado) {
        return atendenteRepository.findById(id)
                .map(atendente -> {
                    atendente.setEmail(atendenteAtualizado.getEmail());
                    atendente.setSenha(atendenteAtualizado.getSenha());
                    return atendenteRepository.save(atendente);
                })
                .orElseThrow(() -> new RuntimeException("Atendente não encontrado"));
    }

    public void deletar(Long id) {
        atendenteRepository.deleteById(id);
    }


}
