package in.arod.addressNormalizer.model.street;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class OriginalStreetType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "originalStreetType")
    private Set<AlternativeStreetType> alternativeStreetTypes;
}
