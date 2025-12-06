package com.example.PetShop.domain.dto;

import com.example.PetShop.domain.entity.Pet;
import com.example.PetShop.domain.entity.Tutor;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetDTO {
    private Long id;
    private String nome;
    private String especie;
    private String raca;
    private LocalDate dataNascimento;
    private Long tutorId;
    private String foto;

    public static PetDTO fromEntity(Pet p) {
        if (p == null) return null;
        Long tutorId = p.getTutor() != null ? p.getTutor().getId() : null;
        return new PetDTO(p.getId(), p.getNome(), p.getEspecie(), p.getRaca(), p.getDataNascimento(), tutorId, p.getFoto());
    }

    public Pet toEntity(Tutor tutor) {
        Pet p = new Pet();
        p.setId(this.id);
        p.setNome(this.nome);
        p.setEspecie(this.especie);
        p.setRaca(this.raca);
        p.setDataNascimento(this.dataNascimento);
        p.setTutor(tutor);
        p.setFoto(this.foto);
        return p;
    }
}