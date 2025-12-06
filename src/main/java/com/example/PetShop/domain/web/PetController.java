package com.example.PetShop.domain.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.PetShop.domain.entity.Pet;
import com.example.PetShop.domain.repository.TutorRepository;
import com.example.PetShop.domain.service.PetService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    private final PetService petService;
    private final TutorRepository tutorRepository;

    public PetController(PetService petService, TutorRepository tutorRepository) {
        this.petService = petService;
        this.tutorRepository = tutorRepository;
    }

    @GetMapping
    public ResponseEntity <List<Pet>> listarTodos() {
        List<Pet> pets = petService.listarTodos();
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> buscarPorId(@PathVariable Long id) {
        return petService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Value("${upload.path}")
    private String uploadPath;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Pet> salvar(
            @RequestParam String nome,
            @RequestParam String especie,
            @RequestParam(required = false) String raca,
            @RequestParam(required = false) String dataNascimento,
            @RequestParam Long tutorId,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) throws IOException {
        var tutor = tutorRepository.findById(tutorId)
                .orElseThrow(() -> new RuntimeException("Tutor não encontrado"));

        Pet pet = new Pet();
        pet.setNome(nome);
        pet.setEspecie(especie);
        pet.setRaca(raca);
        if (dataNascimento != null && !dataNascimento.isBlank()) {
            try {
                pet.setDataNascimento(LocalDate.parse(dataNascimento));
            } catch (Exception ex) {
                // ignore invalid date
            }
        }
        pet.setTutor(tutor);

        if (file != null && !file.isEmpty()) {
            Path dir = Paths.get(uploadPath != null ? uploadPath : "");
            if (!Files.exists(dir)) Files.createDirectories(dir);
            String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path target = dir.resolve(filename);
            Files.copy(file.getInputStream(), target);
            pet.setFoto(filename);
        }

        Pet novoPet = petService.salvar(pet);
        return ResponseEntity.ok(novoPet);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Pet> atualizar(
            @PathVariable Long id,
            @RequestParam String nome,
            @RequestParam String especie,
            @RequestParam(required = false) String raca,
            @RequestParam(required = false) String dataNascimento,
            @RequestParam Long tutorId,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) throws IOException {
        var tutor = tutorRepository.findById(tutorId)
                .orElseThrow(() -> new RuntimeException("Tutor não encontrado"));

        Pet petAtualizado = new Pet();
        petAtualizado.setNome(nome);
        petAtualizado.setEspecie(especie);
        petAtualizado.setRaca(raca);
        if (dataNascimento != null && !dataNascimento.isBlank()) {
            try {
                petAtualizado.setDataNascimento(LocalDate.parse(dataNascimento));
            } catch (Exception ex) {
                // ignore invalid date
            }
        }
        petAtualizado.setTutor(tutor);

        if (file != null && !file.isEmpty()) {
            Path dir = Paths.get(uploadPath != null ? uploadPath : "");
            if (!Files.exists(dir)) Files.createDirectories(dir);
            String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path target = dir.resolve(filename);
            Files.copy(file.getInputStream(), target);
            petAtualizado.setFoto(filename);
        }

        Pet atualizado = petService.atualizar(id, petAtualizado);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        petService.deletar(id);
        return ResponseEntity.noContent().build();
    }
    
    
    
}
