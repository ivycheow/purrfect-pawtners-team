package com.purrfectpawtners.purrfectpawtners.controller;

import com.purrfectpawtners.purrfectpawtners.exception.EmptyPetListException;
import com.purrfectpawtners.purrfectpawtners.exception.ResourceNotFoundException;
import com.purrfectpawtners.purrfectpawtners.model.Pet;
import com.purrfectpawtners.purrfectpawtners.service.BreedService;
import com.purrfectpawtners.purrfectpawtners.service.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping
    public List<Pet> getAllPetsWithBreeds(){
        return petService.getAllPetsWithBreeds();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Pet>> getAllPets() throws EmptyPetListException {
        List<Pet> result = petService.getAllPets();
        if(result.isEmpty()){
            throw new EmptyPetListException("No pet(s) available.");
        } return ResponseEntity.ok(result);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        Pet result = petService.findPetById(id).orElseThrow(() -> new ResourceNotFoundException("No pet found under id: " + id));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Pet> getPetByName(@RequestParam(required = true) String name){
        Pet pet = petService.findByPetName(name);
        if (pet == null){
            throw new ResourceNotFoundException("No pet found for pet name: " + name);
        } return new ResponseEntity<>(pet, HttpStatus.OK);
    }

    @GetMapping("/gender/{gender}")
    public ResponseEntity<List<Pet>> getPetByGender(@PathVariable("gender") Pet.Gender gender) throws EmptyPetListException {
        List<Pet> allPetsByGender = petService.findByPetGender(gender);
        if(allPetsByGender.isEmpty()){
            throw new EmptyPetListException("No " + gender + " pets available.");
        } return ResponseEntity.ok(allPetsByGender);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Pet>> getPetByType(@PathVariable("type") Pet.Type type) throws EmptyPetListException{
        List<Pet> allPetsByType = petService.findByPetType(type);
        if(allPetsByType.isEmpty()){
            throw new EmptyPetListException("No " + type + " list available.");
        } return ResponseEntity.ok(allPetsByType);
    }

    @GetMapping("/approved")
    public ResponseEntity<List<Pet>> getAllApprovedPets() throws EmptyPetListException {
        List<Pet> allApprovedPets = petService.findAllApprovedPets();
        if (allApprovedPets.isEmpty()){
            throw new EmptyPetListException("No HDB-approved pets available.");
        } return ResponseEntity.ok(allApprovedPets);
    }

    @GetMapping("/non-approved")
    public ResponseEntity<List<Pet>> getAllNonApprovedPets() throws EmptyPetListException{
        List<Pet> allNonApprovedPets = petService.findAllNonApprovedPets();
        if(allNonApprovedPets.isEmpty()){
            throw new EmptyPetListException("No non HDB-approved pets available.");
        } return ResponseEntity.ok(allNonApprovedPets);
    }

    @PostMapping("/")
    public ResponseEntity<Pet> createPet(@Valid @RequestBody Pet pet){
        Pet createdPet = petService.createPet(pet);
        return new ResponseEntity(createdPet, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable("id") Integer id, @RequestBody Pet pet){
        Pet existingPet = petService.findPetById(id).orElseThrow(() -> new ResourceNotFoundException("Pet not found under id: " + id));

        existingPet.setId(pet.getId());
        existingPet.setName(pet.getName());
        existingPet.setAgeYear(pet.getAgeYear());
        existingPet.setAgeMonths(pet.getAgeMonths());
        existingPet.setGender(pet.getGender());
        existingPet.setColor(pet.getColor());
        existingPet.setIsLicensed(pet.getIsLicensed());
        existingPet.setIsApproved(pet.getIsApproved());
        existingPet.setType(pet.getType());
        existingPet.setBreed(pet.getBreed());
        existingPet.setIsNeutered(pet.getIsNeutered());
        existingPet.setTraining(pet.getTraining());
        existingPet.setTemperament(pet.getTemperament());
        existingPet.setImagePath(pet.getImagePath());

        try{
            existingPet = petService.updatePet(existingPet);
            return ResponseEntity.ok(existingPet);
        } catch (HandlerMethodValidationException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Optional<Pet>> deletePet(@Valid @PathVariable("id") Integer id){
        Pet deletedPet = petService.findPetById(id).orElseThrow(() -> new ResourceNotFoundException("Pet not found under id: " + id));
        return new ResponseEntity<>(petService.deletePet(id), HttpStatus.OK);
    }
}
