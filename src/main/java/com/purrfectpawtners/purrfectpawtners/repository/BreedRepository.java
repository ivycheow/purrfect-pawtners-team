package com.purrfectpawtners.purrfectpawtners.repository;

import com.purrfectpawtners.purrfectpawtners.model.Breed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BreedRepository extends JpaRepository<Breed, Integer> {
    public List<Breed> findByType(Breed.Type type);
}
