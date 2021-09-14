package in.arod.addressNormalizer.model.city;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class AlternativeCityType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "original_city_type_id", nullable = false)
    private OriginalCityType originalCityType;
    private String title;
}
