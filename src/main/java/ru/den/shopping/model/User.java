package ru.den.shopping.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
//@Table(name = "shopping", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date_time"}, name = "meal_unique_user_datetime_idx")})
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    //@NotBlank
    @Size(min = 2, max = 200)
    private String name;

    @ManyToMany
    @JoinTable(name = "user_family", joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "family_id"))
    private List<Family> families;


}
