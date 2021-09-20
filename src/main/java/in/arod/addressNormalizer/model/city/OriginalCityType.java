package in.arod.addressNormalizer.model.city;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class OriginalCityType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "originalCityType")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<AlternativeCityType> alternativeCityTypes;
}
