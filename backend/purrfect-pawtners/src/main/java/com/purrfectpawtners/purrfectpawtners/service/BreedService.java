package com.purrfectpawtners.purrfectpawtners.service;

import com.purrfectpawtners.purrfectpawtners.model.Breed;
import com.purrfectpawtners.purrfectpawtners.repository.BreedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreedService {
    @Autowired
    private final BreedRepository breedRepository;

    public BreedService(BreedRepository breedRepository) {
        this.breedRepository = breedRepository;
    }

    public List<Breed> getAllPets(){
        return breedRepository.findAll();
    }

    public List<Breed> findByBreedType(Breed.Type type){
        return breedRepository.findByType(type);
    }
}
