package ru.den.shopping.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShoppingDTO {

    @Size(min = 2, max = 200)
    private String name;

    @Size(min = 2, max = 50)
    private String howMany;

    @Size(min = 2, max = 100)
    private String store;
}
