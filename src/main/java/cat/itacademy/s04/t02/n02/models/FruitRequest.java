package cat.itacademy.s04.t02.n02.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FruitRequest(
        @NotBlank String name,
        @NotNull Integer quantityKilos
) { }
