package com.example.PetShop.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.PetShop.domain.entity.Pet;
import com.example.PetShop.domain.repository.PetRepository;

@Service
public class PetService {
    
    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public List<Pet> listarTodos() {
        return petRepository.findAll();
    }

    public Optional<Pet> buscarPorId(Long id) {
        return petRepository.findById(id);
    }

    public Pet salvar(Pet pet) {
        pet.setId(null);
        return petRepository.save(pet);
    }

    public Pet atualizar(Long id, Pet petAtualizado) {
        return petRepository.findById(id)
                .map(pet -> {
                    pet.setNome(petAtualizado.getNome());
                    pet.setEspecie(petAtualizado.getEspecie());
                    pet.setRaca(petAtualizado.getRaca());
                    pet.setDataNascimento(petAtualizado.getDataNascimento());
                    pet.setTutor(petAtualizado.getTutor());
                    // atualiza foto se fornecida (pode ser null)
                    if (petAtualizado.getFoto() != null) {
                        pet.setFoto(petAtualizado.getFoto());
                    }
                    return petRepository.save(pet);
                })
                .orElseThrow(() -> new RuntimeException("Pet não encontrado"));
    }

    public void deletar(Long id) {
        petRepository.deleteById(id);
    }

}
