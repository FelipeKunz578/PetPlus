package com.example.PetShop.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private Double valor;
    // duração pode ser opcional — permite valor nulo
    @Column(nullable = true)
    private String duracao;
    private String observacoes;
    // coluna para armazenar duração em minutos (não nula no banco)
    @Column(name = "duracao_minutos", nullable = false)
    private Integer duracaoMinutos;
}
