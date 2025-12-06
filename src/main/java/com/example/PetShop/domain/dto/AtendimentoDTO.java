package com.example.PetShop.domain.dto;

import com.example.PetShop.domain.entity.Atendimento;
import com.example.PetShop.domain.entity.Pet;
import com.example.PetShop.domain.entity.Servico;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtendimentoDTO {
	private Long id;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime data;
	private Long petId;
	private Long servicoId;
	private String observacoes;

	public static AtendimentoDTO fromEntity(Atendimento a) {
		if (a == null) return null;
		Long petId = a.getPet() != null ? a.getPet().getId() : null;
		Long servicoId = a.getServico() != null ? a.getServico().getId() : null;
		return new AtendimentoDTO(a.getId(), a.getData(), petId, servicoId, a.getObservacoes());
	}

	public Atendimento toEntity(Pet pet, Servico servico) {
		Atendimento a = new Atendimento();
		a.setId(this.id);
		a.setData(this.data);
		a.setPet(pet);
		a.setServico(servico);
		a.setObservacoes(this.observacoes);
		return a;
	}
}
