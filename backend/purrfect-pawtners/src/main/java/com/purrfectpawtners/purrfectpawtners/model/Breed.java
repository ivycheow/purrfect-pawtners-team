package com.purrfectpawtners.purrfectpawtners.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Breed {
    public enum Type{
        Cats, Dogs
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Breed name cannot be blank.")
    private String name;

    @NotNull(message = "Pet type must either be Cats or Dogs.")
    @Enumerated(EnumType.STRING)
    private Pet.Type type;
}
