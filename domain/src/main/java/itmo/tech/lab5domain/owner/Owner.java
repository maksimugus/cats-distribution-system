package itmo.tech.lab5domain.owner;

import itmo.tech.lab5domain.cat.Cat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "owners")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(
            name = "date_of_birth",
            nullable = false,
            updatable = false
    )
    private LocalDate birthDate;

    @OneToMany(
            mappedBy = "owner",
            fetch = FetchType.EAGER
    )
    private List<Cat> cats;
}
