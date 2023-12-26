// package com.purrfectpawtners.purrfectpawtners.service;

// import com.purrfectpawtners.purrfectpawtners.model.Pet;
// import com.purrfectpawtners.purrfectpawtners.repository.PetRepository;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;

// import javax.swing.text.html.Option;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;

// import static com.purrfectpawtners.purrfectpawtners.model.Pet.Gender.Female;
// import static com.purrfectpawtners.purrfectpawtners.model.Pet.Gender.Male;
// import static com.purrfectpawtners.purrfectpawtners.model.Pet.Type.Cats;
// import static com.purrfectpawtners.purrfectpawtners.model.Pet.Type.Dogs;
// import static org.assertj.core.api.Assertions.assertThat;
// import static org.assertj.core.api.Assertions.extractProperty;
// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.ArgumentMatchers.anyInt;
// import static org.mockito.Mockito.*;

// @ExtendWith(MockitoExtension.class)
// class PetServiceTest {

//     @Mock
//     private PetRepository petRepository;

//     @InjectMocks
//     private PetService petService;

//     private Pet pet1, pet2;

//     @BeforeEach
//     void init(){
//         pet1 = new Pet(1, "Cookie", 2, 4, Male, "Brown", true, true, Dogs, 30, true, "Basic Obedience Training", "Gentle and playful", "../img/pet1");
//         pet2 = new Pet(2, "Luna", 3, 5, Female, "Black and White", true, false, Cats, 3, true, "N.A.", "Playful", "../img/pet2");
//     }

//     @Test
//     void getAllPets() {
//         List<Pet> list = new ArrayList<>();
//         list.add(pet1);
//         list.add(pet2);

//         when(petRepository.findAll()).thenReturn(list);
//         List<Pet> result = (List<Pet>) petService.getAllPets();

//         assertNotNull(result);
//         assertEquals(2, result.size());
//     }

//     @Test
//     void findPetById() {
//         when(petRepository.findById(any(Integer.class))).thenReturn(Optional.of(pet1));
//         Optional<Pet> currentPet = petService.findPetById(pet1.getId());

//         assertNotNull(currentPet);
//         assertThat(currentPet.isEmpty()).isNotEqualTo(true);
//         assertThat(currentPet.get().getId()).isEqualTo(1);
//     }

//     @Test
//     void createPet() {
//         when(petRepository.save(any(Pet.class))).thenReturn(pet1);
//         Pet newPet = petService.createPet(pet1);

//         assertNotNull(pet1);
//         assertThat(newPet.getId()).isEqualTo(1);
//     }

//     @Test
//     void updatePet() {
//         when(petRepository.save(any(Pet.class))).thenReturn(pet1);
//         pet1.setName("Moon");
//         Optional<Pet> currentPet = Optional.ofNullable(petService.updatePet(pet1));

//         assertTrue(currentPet.isPresent());
//         assertEquals("Moon", currentPet.get().getName());
//     }

//     @Test
//     void deletePet() {
//         Integer petId = 1;
//         when(petRepository.findById(any(Integer.class))).thenReturn(Optional.of(pet1));
//         doNothing().when(petRepository).deleteById(any(Integer.class));
//         Optional<Pet> deletedProduct = petService.deletePet(petId);

//         verify(petRepository, times(1)).deleteById(petId);
//     }
// }