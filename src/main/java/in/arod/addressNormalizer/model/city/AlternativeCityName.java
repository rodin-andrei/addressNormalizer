package in.arod.addressNormalizer.model.city;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class AlternativeCityName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "original_city_id", nullable = false)
    private OriginalCityName originalCityName;
    private String title;
}
