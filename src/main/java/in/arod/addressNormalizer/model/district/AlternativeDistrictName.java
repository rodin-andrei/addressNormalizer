package in.arod.addressNormalizer.model.district;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AlternativeDistrictName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "original_district_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ToString.Exclude
    private OriginalDistrictName originalDistrictName;

    @Column(unique = true)
    private String title;
}
