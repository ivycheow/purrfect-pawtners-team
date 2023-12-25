package com.purrfectpawtners.purrfectpawtners.repository;

import com.purrfectpawtners.purrfectpawtners.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {
    public Pet findByName(String name);

    public List<Pet> findByType(Pet.Type type);

    public List<Pet> findByGender(Pet.Gender gender);

    public List<Pet> findByIsApprovedTrue();

    public List<Pet> findByIsApprovedFalse();
}
