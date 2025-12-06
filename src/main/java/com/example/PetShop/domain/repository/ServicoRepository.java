package com.example.PetShop.domain.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PetShop.domain.entity.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long>{

    
}
