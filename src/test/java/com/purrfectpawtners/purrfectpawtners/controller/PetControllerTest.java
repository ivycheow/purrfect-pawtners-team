// package com.purrfectpawtners.purrfectpawtners.controller;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.purrfectpawtners.purrfectpawtners.model.Pet;
// import com.purrfectpawtners.purrfectpawtners.service.PetService;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;


// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;

// import static com.purrfectpawtners.purrfectpawtners.model.Pet.Gender.Female;
// import static com.purrfectpawtners.purrfectpawtners.model.Pet.Gender.Male;
// import static com.purrfectpawtners.purrfectpawtners.model.Pet.Type.Cats;
// import static com.purrfectpawtners.purrfectpawtners.model.Pet.Type.Dogs;
// import static org.hamcrest.CoreMatchers.is;
// import static org.mockito.ArgumentMatchers.*;
// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @WebMvcTest
// class PetControllerTest {

//     @Autowired
//     MockMvc mockMvc;


//     @MockBean
//     private PetService petService;

//     private Pet pet1;
//     private Pet pet2;

//     private Pet updatedPet1;

//     @Autowired
//     private ObjectMapper objectMapper;

//     @BeforeEach
//     void init(){
//         pet1 = new Pet(1, "Cookie", 2, 4, Male, "Brown", true, true, Dogs, 30, true, "Basic Obedience Training", "Gentle and playful", "../img/pet1");
//         pet2 = new Pet(2, "Luna", 3, 5, Female, "Black and White", true, false, Cats, 3, true, "N.A.", "Playful", "../img/pet2");
//         updatedPet1 = new Pet(1, "Moon", 2, 5, Male, "Brown", true, true, Dogs, 30, true, "Basic Obedience Training", "Gentle and playful", "../img/pet1");
//     }

//     @Test
//     void getAllPets() throws Exception{
//         List<Pet> result = new ArrayList<>();
//         result.add(pet1);
//         result.add(pet2);

//         when(petService.getAllPets()).thenReturn(result);
//         this.mockMvc.perform(get("/pets/all"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.size()", is(result.size())));
//     }

//     @Test
//     void getPetById() throws Exception{
//         when(petService.findPetById(any(Integer.class)))
//                 .thenReturn(Optional.ofNullable(pet1));

//         this.mockMvc.perform(get("/pets/id/{id}", 1)
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.id", is(pet1.getId())))
//                 .andExpect(jsonPath("$.name", is(pet1.getName())));
//     }

//     @Test
//     void createPet() throws Exception {
//         when(petService.createPet(any(Pet.class))).thenReturn(pet1);

//         this.mockMvc.perform(post("/pets/")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(pet1)))
//                 .andExpect(status().isCreated())
//                 .andExpect(jsonPath("$.id", is(pet1.getId())))
//                 .andExpect(jsonPath("$.name", is(pet1.getName())))
//                 .andExpect(jsonPath("$.ageYear", is(pet1.getAgeYear())))
//                 .andExpect(jsonPath("$.ageMonths", is(pet1.getAgeMonths())))
//                 .andExpect(jsonPath("$.gender", is("Male")))
//                 .andExpect(jsonPath("$.color", is(pet1.getColor())))
//                 .andExpect(jsonPath("$.isLicensed", is(pet1.getIsLicensed())))
//                 .andExpect(jsonPath("$.isApproved", is(pet1.getIsApproved())))
//                 .andExpect(jsonPath("$.type", is("Dogs")))
//                 .andExpect(jsonPath("$.breed", is(pet1.getBreed())))
//                 .andExpect(jsonPath("$.isNeutered", is(pet1.getIsNeutered())))
//                 .andExpect(jsonPath("$.training", is(pet1.getTraining())))
//                 .andExpect(jsonPath("$.temperament", is(pet1.getTemperament())))
//                 .andExpect(jsonPath("$.imagePath", is(pet1.getImagePath())));
//     }

//     @Test
//     void updatePet() throws Exception {
//         Integer petId = 1;
//         when(petService.findPetById(petId)).thenReturn(Optional.ofNullable(pet1));
//         when(petService.updatePet(pet1)).thenReturn(updatedPet1);

//         this.mockMvc.perform(put("/pets/update/{id}", 1)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(updatedPet1)))
//                 .andExpect(jsonPath("$.id", is(updatedPet1.getId())))
//                 .andExpect(jsonPath("$.name", is(updatedPet1.getName())))
//                 .andExpect(jsonPath("$.ageYear", is(updatedPet1.getAgeYear())))
//                 .andExpect(jsonPath("$.ageMonths", is(updatedPet1.getAgeMonths())))
//                 .andExpect(jsonPath("$.gender", is("Male")))
//                 .andExpect(jsonPath("$.color", is(updatedPet1.getColor())))
//                 .andExpect(jsonPath("$.isLicensed", is(updatedPet1.getIsLicensed())))
//                 .andExpect(jsonPath("$.isApproved", is(updatedPet1.getIsApproved())))
//                 .andExpect(jsonPath("$.type", is("Dogs")))
//                 .andExpect(jsonPath("$.breed", is(updatedPet1.getBreed())))
//                 .andExpect(jsonPath("$.isNeutered", is(updatedPet1.getIsNeutered())))
//                 .andExpect(jsonPath("$.training", is(updatedPet1.getTraining())))
//                 .andExpect(jsonPath("$.temperament", is(updatedPet1.getTemperament())))
//                 .andExpect(jsonPath("$.imagePath", is(updatedPet1.getImagePath())));
//     }

//     @Test
//     void deletePet() throws Exception{
//         when(petService.deletePet(any(Integer.class)))
//                 .thenReturn(Optional.ofNullable(pet2));

//         this.mockMvc.perform(delete("/pets/delete/{id}", 2)
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.id", is(pet2.getId())))
//                 .andExpect(jsonPath("$.name", is(pet2.getName())));
//     }
// }