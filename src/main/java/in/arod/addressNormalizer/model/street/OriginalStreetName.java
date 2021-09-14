package in.arod.addressNormalizer.model.street;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class OriginalStreetName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "originalStreetName")
    private Set<AlternativeStreetName> alternativeStreetNames;
}
