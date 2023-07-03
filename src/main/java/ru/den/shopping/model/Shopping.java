package ru.den.shopping.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
//@Table(name = "shopping", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date_time"}, name = "meal_unique_user_datetime_idx")})
@Table(name = "shopping")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Shopping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(min = 2, max = 200)
    private String name;

    @Column(name = "data", nullable = false)
    @NotNull
    //@Temporal(TemporalType.TIMESTAMP) if Date
    private LocalDateTime data;

    @Column(name = "how_many")
    @Size(min = 2, max = 50)
    private String howMany;

    @Column(name = "store")
    @Size(min = 2, max = 100)
    private String store;

    //private String howMach;?

    @ManyToOne
    @JoinColumn(name = "family_id", referencedColumnName = "id")
    private Family owner;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shopping shopping = (Shopping) o;

        if (!name.equals(shopping.name)) return false;
        if (!Objects.equals(data, shopping.data)) return false;
        if (!Objects.equals(howMany, shopping.howMany)) return false;
        return Objects.equals(store, shopping.store);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (howMany != null ? howMany.hashCode() : 0);
        result = 31 * result + (store != null ? store.hashCode() : 0);
        return result;
    }
}
