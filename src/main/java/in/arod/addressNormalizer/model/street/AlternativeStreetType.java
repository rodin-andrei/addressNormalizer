package in.arod.addressNormalizer.model.street;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity(name = "alternative_street_type")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AlternativeStreetType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "original_street_type_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ToString.Exclude
    private OriginalStreetType originalStreetType;

    @Column(unique = true)
    private String title;
}
