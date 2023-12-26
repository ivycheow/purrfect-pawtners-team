package com.purrfectpawtners.purrfectpawtners.controller;

import com.purrfectpawtners.purrfectpawtners.exception.EmptyPetListException;
import com.purrfectpawtners.purrfectpawtners.exception.ResourceNotFoundException;
import com.purrfectpawtners.purrfectpawtners.model.Breed;
import com.purrfectpawtners.purrfectpawtners.model.Pet;
import com.purrfectpawtners.purrfectpawtners.repository.BreedRepository;
import com.purrfectpawtners.purrfectpawtners.service.FileStorageService;
import com.purrfectpawtners.purrfectpawtners.service.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/pets")
public class PetController {

    private static final Logger logger = LoggerFactory.getLogger(PetController.class);

    @Autowired
    private PetService petService;

    @Autowired
    private BreedRepository breedRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping
    public List<Pet> getAllPetsWithBreeds() {
        return petService.getAllPetsWithBreeds();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Pet>> getAllPets(@RequestParam(required = false) String name)
            throws EmptyPetListException {
        List<Pet> result;
        if (name == null) {
            result = petService.getAllPets();
        } else {
            result = petService.findByPetName(name);
        }

        if (result.isEmpty()) {
            throw new EmptyPetListException("No pet(s) available.");
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        Pet result = petService.findPetById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No pet found under id: " + id));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<List<Pet>> getPetByName(@RequestParam(required = true) String name){
        List<Pet> pets = petService.findByPetName(name);
        if (pets.isEmpty()){
            throw new ResourceNotFoundException("No pets found for pet name: " + name);
        } 
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }    

    @GetMapping("/gender/{gender}")
    public ResponseEntity<List<Pet>> getPetByGender(@PathVariable("gender") Pet.Gender gender)
            throws EmptyPetListException {
        List<Pet> allPetsByGender = petService.findByPetGender(gender);
        if (allPetsByGender.isEmpty()) {
            throw new EmptyPetListException("No " + gender + " pets available.");
        }
        return ResponseEntity.ok(allPetsByGender);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Pet>> getPetByType(@PathVariable("type") Pet.Type type) throws EmptyPetListException {
        List<Pet> allPetsByType = petService.findByPetType(type);
        if (allPetsByType.isEmpty()) {
            throw new EmptyPetListException("No " + type + " list available.");
        }
        return ResponseEntity.ok(allPetsByType);
    }

    @GetMapping("/approved")
    public ResponseEntity<List<Pet>> getAllApprovedPets() throws EmptyPetListException {
        List<Pet> allApprovedPets = petService.findAllApprovedPets();
        if (allApprovedPets.isEmpty()) {
            throw new EmptyPetListException("No HDB-approved pets available.");
        }
        return ResponseEntity.ok(allApprovedPets);
    }

    @GetMapping("/non-approved")
    public ResponseEntity<List<Pet>> getAllNonApprovedPets() throws EmptyPetListException {
        List<Pet> allNonApprovedPets = petService.findAllNonApprovedPets();
        if (allNonApprovedPets.isEmpty()) {
            throw new EmptyPetListException("No non HDB-approved pets available.");
        }
        return ResponseEntity.ok(allNonApprovedPets);
    }

    @PostMapping("/")
    public ResponseEntity<?> createPet(
            @RequestParam("pawtnerName") String pawtnerName,
            @RequestParam("pawtnerAgeYear") String pawtnerAgeYear,
            @RequestParam("pawtnerAgeMonths") String pawtnerAgeMonths,
            @RequestParam("pawtnerGender") String pawtnerGender,
            @RequestParam("pawtnerColour") String pawtnerColour,
            @RequestParam("pawtnerAVSLicensed") String pawtnerAVSLicensed,
            @RequestParam("pawtnerHDBApproved") String pawtnerHDBApproved,
            @RequestParam("pawtnerSpayNeuter") String pawtnerSpayNeuter,
            @RequestParam("pawtnerTraining") String pawtnerTraining,
            @RequestParam("pawtnerTemperament") String pawtnerTemperament,
            @RequestParam("pawtnerType") String pawtnerType,
            @RequestParam("pawtnerBreed") String pawtnerBreed,
            @RequestParam("pawtnerImage") MultipartFile file) {

        System.out.println("pawtnerName: " + pawtnerName);
        System.out.println("pawtnerAgeYear: " + pawtnerAgeYear);
        System.out.println("pawtnerAgeMonths: " + pawtnerAgeMonths);
        System.out.println("pawtnerGender: " + pawtnerGender);
        System.out.println("pawtnerColour: " + pawtnerColour);
        System.out.println("pawtnerAVSLicensed: " + pawtnerAVSLicensed);
        System.out.println("pawtnerHDBApproved: " + pawtnerHDBApproved);
        System.out.println("pawtnerSpayNeuter: " + pawtnerSpayNeuter);
        System.out.println("pawtnerTraining: " + pawtnerTraining);
        System.out.println("pawtnerTemperament: " + pawtnerTemperament);
        System.out.println("pawtnerType: " + pawtnerType);
        System.out.println("pawtnerBreed: " + pawtnerBreed);
        System.out.println("pawtnerImage: " + file.getOriginalFilename());
        try {
            // Convert the string values to appropriate types
            int pawtnerAgeYearInt = Integer.parseInt(pawtnerAgeYear);
            int pawtnerAgeMonthsInt = Integer.parseInt(pawtnerAgeMonths);
            logger.info("Received pawtnerBreed: {}", pawtnerBreed);
            int pawtnerBreedInt = Integer.parseInt(pawtnerBreed);
            boolean pawtnerAVSLicensedBool = pawtnerAVSLicensed.equalsIgnoreCase("yes");
            boolean pawtnerHDBApprovedBool = pawtnerHDBApproved.equalsIgnoreCase("yes");
            boolean pawtnerSpayNeuterBool = pawtnerSpayNeuter.equalsIgnoreCase("yes");

            Pet.Gender pawtnerGenderEnum = Pet.Gender.valueOf(pawtnerGender);
            Pet.Type pawtnerTypeEnum = Pet.Type.valueOf(pawtnerType);

            String imagePath = fileStorageService.storeFile(file);

            // Check if the breed exists
            Breed breed = breedRepository.findById(pawtnerBreedInt)
                    .orElseThrow(() -> new ResourceNotFoundException("Breed not found with id: " + pawtnerBreedInt));

            // Create a new Pet object and set its properties
            Pet pet = new Pet();
            pet.setName(pawtnerName);
            pet.setAgeYear(pawtnerAgeYearInt);
            pet.setAgeMonths(pawtnerAgeMonthsInt);
            pet.setGender(pawtnerGenderEnum);
            pet.setColor(pawtnerColour);
            pet.setIsLicensed(pawtnerAVSLicensedBool);
            pet.setIsApproved(pawtnerHDBApprovedBool);
            pet.setIsNeutered(pawtnerSpayNeuterBool);
            pet.setTraining(pawtnerTraining);
            pet.setTemperament(pawtnerTemperament);
            pet.setType(pawtnerTypeEnum);
            pet.setImagePath(imagePath);
            pet.setBreed(breed);

            // Save the pet
            Pet createdPet = petService.createPet(pet);
            return new ResponseEntity<>(createdPet, HttpStatus.CREATED);

        } catch (NumberFormatException e) {
            return new ResponseEntity<>("Invalid breed ID format: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/uploads/")
                .path(fileName)
                .toUriString();

        return ResponseEntity.ok(fileDownloadUri);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable("id") Integer id, @RequestBody Pet pet) {
        Pet existingPet = petService.findPetById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found under id: " + id));

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

        try {
            existingPet = petService.updatePet(existingPet);
            return ResponseEntity.ok(existingPet);
        } catch (HandlerMethodValidationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Optional<Pet>> deletePet(@Valid @PathVariable("id") Integer id) {
        Pet deletedPet = petService.findPetById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found under id: " + id));
        return new ResponseEntity<>(petService.deletePet(id), HttpStatus.OK);
    }
}
