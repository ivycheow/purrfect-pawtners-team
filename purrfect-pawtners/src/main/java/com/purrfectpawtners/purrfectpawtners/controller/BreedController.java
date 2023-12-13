package com.purrfectpawtners.purrfectpawtners.controller;

import com.purrfectpawtners.purrfectpawtners.model.Breed;
import com.purrfectpawtners.purrfectpawtners.model.Pet;
import com.purrfectpawtners.purrfectpawtners.service.BreedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/breed")
public class BreedController {

    @Autowired
    private BreedService breedService;

    @GetMapping("/all")
    public ResponseEntity<List<Breed>> getAllPets(){
        List<Breed> allBreed = breedService.getAllPets();
        return ResponseEntity.ok(allBreed);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Breed>> getBreedByType(@PathVariable("type")Breed.Type type){
        List<Breed> allBreedByType = breedService.findByBreedType(type);
        return ResponseEntity.ok(allBreedByType);
    }
}
