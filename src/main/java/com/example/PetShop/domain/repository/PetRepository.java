package com.example.PetShop.domain.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PetShop.domain.entity.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long>{

    
}
