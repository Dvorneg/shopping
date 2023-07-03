package ru.den.shopping.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
//@Table(name = "shopping", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date_time"}, name = "meal_unique_user_datetime_idx")})
@Table(name = "family")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(min = 2, max = 200)
    private String name;

    @ManyToMany //(mappedBy ="families")
    @JoinTable(name="user_family",
            joinColumns=@JoinColumn (name="family_id"),
            inverseJoinColumns=@JoinColumn(name="user_id"))
    private List<User> users;

    public Family(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    /*   @OneToMany(mappedBy = "owner")
    private List<Shopping> shopping;*/


}
