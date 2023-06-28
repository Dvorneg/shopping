package ru.den.shopping.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FamilyDTO {

    @Size(min = 2, max = 200)
    private String name;
}
