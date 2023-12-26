package com.purrfectpawtners.purrfectpawtners.repository;

import com.purrfectpawtners.purrfectpawtners.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {
    List<Pet> findByName(String name); // Return a list of pets

    List<Pet> findByType(Pet.Type type);

    List<Pet> findByGender(Pet.Gender gender);

    List<Pet> findByIsApprovedTrue();

    List<Pet> findByIsApprovedFalse();
}
