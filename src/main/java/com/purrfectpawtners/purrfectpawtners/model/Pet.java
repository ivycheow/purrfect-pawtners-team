package com.purrfectpawtners.purrfectpawtners.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pet {

    public enum Gender {
        Male, Female
    }

    public enum Type {
        Cats, Dogs
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Pet name cannot be blank.")
    @Size(min = 1, max = 15, message = "Pet name must be between 1 to 15 characters.")
    @Column(unique = true)
    private String name;

    @Column(name = "ageYear")
    @NotNull(message = "Pet age(year) cannot be null.")
    @Max(value = 10, message = "Pet age(year) must not exceed 10.")
    private Integer ageYear;

    @Column(name = "ageMonths")
    @NotNull(message = "Pet age(months) cannot be null.")
    @Min(value = 1, message = "Pet age(months) must be at least 1.")
    @Max(value = 11, message = "Pet age(year) must not exceed 11.")
    private Integer ageMonths;

    @NotNull(message = "Pet gender must either be Male or Female.")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotBlank(message = "Pet color cannot be blank.")
    @Size(min = 1, max = 25, message = "Pet color must be between 1 to 25 characters.")
    private String color;

    @NotNull(message = "Pet's license status cannot be null.")
    private Boolean isLicensed;

    @NotNull(message = "Pet's HDB-approved status cannot be null.")
    private Boolean isApproved;

    @NotNull(message = "Pet type must either be Cats or Dogs.")
    @Enumerated(EnumType.STRING)
    private Type type;

    @NotNull(message = "Pet breed cannot be blank.")
    @ManyToOne
    @JoinColumn(name = "breedId", nullable = false)
    private Breed breed;

    @NotNull(message = "Pet's neuter/spay status cannot be null.")
    private Boolean isNeutered;

    @NotBlank(message = "Pet training cannot be blank.")
    @Size(min = 1, max = 25, message = "Pet training must be between 1 to 25 characters long.")
    private String training;

    @NotBlank(message = "Pet temperament cannot be blank.")
    @Size(min = 1, max = 150, message = "Pet temperament must be between 1 to 150 characters.")
    private String temperament;

    @NotBlank(message = "Pet image path cannot be blank.")
    private String imagePath;


}

//    public Pet(){
//
//    }
//
//    public Pet(int id, String name, int ageYear, int ageMonths, String gender, String color, boolean isLicensed, boolean isApproved, String type, int breed, boolean isNeutered, String training, String  temperament){
//        this.id = id;
//        this.name = name;
//        this.ageYear = ageYear;
//        this.ageMonths = ageMonths;
//        this.gender = gender;
//        this.color = color;
//        this.isLicensed = isLicensed;
//        this.isApproved = isApproved;
//        this.type = type;
//        this.breed = breed;
//        this.isNeutered = isNeutered;
//        this.training = training;
//        this.temperament = temperament;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getAgeYear() {
//        return ageYear;
//    }
//
//    public void setAgeYear(int ageYear) {
//        this.ageYear = ageYear;
//    }
//
//    public int getAgeMonths() {
//        return ageMonths;
//    }
//
//    public void setAgeMonths(int ageMonths) {
//        this.ageMonths = ageMonths;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public String getColor() {
//        return color;
//    }
//
//    public void setColor(String color) {
//        this.color = color;
//    }
//
//    public boolean isLicensed() {
//        return isLicensed;
//    }
//
//    public void setIsLicensed(boolean isLicensed) {
//        this.isLicensed = isLicensed;
//    }
//
//    public boolean isApproved() {
//        return isApproved;
//    }
//
//    public void setIsApproved(boolean isApproved) {
//        this.isApproved = isApproved;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public int getBreed() {
//        return breed;
//    }
//
//    public void setBreed(int breed) {
//        this.breed = breed;
//    }
//
//    public boolean isNeutered() {
//        return isNeutered;
//    }
//
//    public void setIsNeutered(boolean isNeutered) {
//        this.isNeutered = isNeutered;
//    }
//
//    public String getTraining() {
//        return training;
//    }
//
//    public void setTraining(String training) {
//        this.training = training;
//    }
//
//    public String getTemperament() {
//        return temperament;
//    }
//
//    public void setTemperament(String temperament) {
//        this.temperament = temperament;
//    }

