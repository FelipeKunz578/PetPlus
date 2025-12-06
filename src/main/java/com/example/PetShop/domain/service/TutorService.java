package com.example.PetShop.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.PetShop.domain.entity.Tutor;
import com.example.PetShop.domain.repository.TutorRepository;

@Service
public class TutorService {
    private final TutorRepository tutorRepository;

    public TutorService(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    public List<Tutor> listarTodos() {
        return tutorRepository.findAll();
    }

    public Optional<Tutor> buscarPorId(Long id) {
        return tutorRepository.findById(id);
    }

    public Tutor salvar(Tutor tutor) {
        tutor.setId(null);
        return tutorRepository.save(tutor);
    }

    public Tutor atualizar(Long id, Tutor tutorAtualizado) {
        return tutorRepository.findById(id)
                .map(tutor -> {
                    tutor.setNome(tutorAtualizado.getNome());
                    tutor.setTelefone(tutorAtualizado.getTelefone());
                    tutor.setEmail(tutorAtualizado.getEmail());
                    return tutorRepository.save(tutor);
                })
                .orElseThrow(() -> new RuntimeException("Tutor não encontrado"));
    }

    public void deletar(Long id) {
        tutorRepository.deleteById(id);
    }
}
