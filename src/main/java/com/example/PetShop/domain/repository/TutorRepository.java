package com.example.PetShop.domain.repository;

import org.springframework.stereotype.Repository;

import com.example.PetShop.domain.entity.Tutor;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {

    
}
