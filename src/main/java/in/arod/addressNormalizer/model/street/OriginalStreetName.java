package in.arod.addressNormalizer.model.street;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "original_street_name")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class OriginalStreetName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "originalStreetName")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<AlternativeStreetName> alternativeStreetNames;
}
