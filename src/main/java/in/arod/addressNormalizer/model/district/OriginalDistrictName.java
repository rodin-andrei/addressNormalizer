package in.arod.addressNormalizer.model.district;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class OriginalDistrictName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "originalDistrictName")
    private Set<AlternativeDistrictName> alternativeDistrictNames;
}
