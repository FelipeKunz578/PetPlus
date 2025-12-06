package com.example.PetShop.domain.dto;

import com.example.PetShop.domain.entity.Servico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicoDTO {
    private Long id;
    private String descricao;
    private Double valor;
    private String duracao;
    private String observacoes;

    public static ServicoDTO fromEntity(Servico s) {
        if (s == null) return null;
        return new ServicoDTO(s.getId(), s.getDescricao(), s.getValor(), s.getDuracao(), s.getObservacoes());
    }

    public Servico toEntity() {
        Servico s = new Servico();
        s.setId(this.id);
        s.setDescricao(this.descricao);
        s.setValor(this.valor);
        if (this.duracao != null) s.setDuracao(this.duracao);
        s.setObservacoes(this.observacoes);
        return s;
    }
}
