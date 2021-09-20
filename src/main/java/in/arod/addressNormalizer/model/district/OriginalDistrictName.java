package in.arod.addressNormalizer.model.district;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class OriginalDistrictName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "originalDistrictName")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<AlternativeDistrictName> alternativeDistrictNames;
}
