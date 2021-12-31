package in.arod.addressNormalizer.model.street;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity(name = "alternative_street_name")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AlternativeStreetName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "original_street_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ToString.Exclude
    private OriginalStreetName originalStreetName;

    @Column(unique = true)
    private String title;
}
