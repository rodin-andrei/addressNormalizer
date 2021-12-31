package in.arod.addressNormalizer.model.street;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "original_street_type")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class OriginalStreetType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "originalStreetType")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<AlternativeStreetType> alternativeStreetTypes;
}
