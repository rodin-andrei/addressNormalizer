package in.arod.addressNormalizer.model.city;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class OriginalCityName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "originalCityName")
    private Set<AlternativeCityName> alternativeCityNames;
}
