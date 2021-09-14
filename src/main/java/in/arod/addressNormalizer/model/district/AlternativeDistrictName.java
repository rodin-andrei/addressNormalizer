package in.arod.addressNormalizer.model.district;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class AlternativeDistrictName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "original_district_id", nullable = false)
    private OriginalDistrictName originalDistrictName;
    private String title;
}
