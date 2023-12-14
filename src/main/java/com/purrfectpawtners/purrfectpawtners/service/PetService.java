package com.purrfectpawtners.purrfectpawtners.service;

import com.purrfectpawtners.purrfectpawtners.exception.ResourceNotFoundException;
import com.purrfectpawtners.purrfectpawtners.model.Pet;
import com.purrfectpawtners.purrfectpawtners.repository.BreedRepository;
import com.purrfectpawtners.purrfectpawtners.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final BreedRepository breedRepository;

    @Autowired
    public PetService(PetRepository petRepository, BreedRepository breedRepository){
        this.petRepository = petRepository;
        this.breedRepository = breedRepository;
    }

    public List<Pet> getAllPetsWithBreeds(){
        return petRepository.findAll();
    }

    public List<Pet> getAllPets(){
        return petRepository.findAll();
    }

    public Optional<Pet> findPetById(Integer id){
        return petRepository.findById(id);
    }

    public Pet findByPetName(String name){
        return petRepository.findByName(name);
    }

    public List<Pet> findByPetType(Pet.Type type){
        return petRepository.findByType(type);
    }

    public List<Pet> findByPetGender(Pet.Gender gender){
        return petRepository.findByGender(gender);
    }

    public List<Pet> findAllApprovedPets(){
        return petRepository.findByIsApprovedTrue();
    }

    public List<Pet> findAllNonApprovedPets(){
        return petRepository.findByIsApprovedFalse();
    }

    public Pet createPet(Pet pet) {
        return petRepository.save(pet);
    }

    public Pet updatePet(Pet pet){
        return petRepository.save(pet);
    }

    public Optional<Pet> deletePet(Integer id){
        Optional<Pet> result = petRepository.findById(id);
        if (result.isPresent()){
            petRepository.deleteById(id);
        } return result;
    }
}
