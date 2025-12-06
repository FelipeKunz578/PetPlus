package com.example.PetShop.domain.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PetShop.domain.entity.Atendente;

@Repository
public interface AtendenteRepository extends JpaRepository<Atendente, Long> {
    Optional<Atendente> findByEmail(String email);
}
