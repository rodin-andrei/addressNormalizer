package in.arod.addressNormalizer.model.city;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity(name = "alternative_city_type")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlternativeCityType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "original_city_type_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private OriginalCityType originalCityType;

    @Column(unique = true)
    private String title;
}
