package com.example.PetShop.domain.dto;

import com.example.PetShop.domain.entity.Tutor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TutorDTO {
	private Long id;
	private String nome;
	private String cpf;
	private String telefone;
	private String email;

	public static TutorDTO fromEntity(Tutor t) {
		if (t == null) return null;
		return new TutorDTO(t.getId(), t.getNome(), t.getCpf(), t.getTelefone(), t.getEmail());
	}

	public Tutor toEntity() {
		Tutor t = new Tutor();
		t.setId(this.id);
		t.setNome(this.nome);
		t.setCpf(this.cpf);
		t.setTelefone(this.telefone);
		t.setEmail(this.email);
		return t;
	}
}
