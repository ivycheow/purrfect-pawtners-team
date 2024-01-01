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
