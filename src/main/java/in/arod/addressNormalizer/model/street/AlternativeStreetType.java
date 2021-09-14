package in.arod.addressNormalizer.model.street;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class AlternativeStreetType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "original_street_type_id", nullable = false)
    private OriginalStreetType originalStreetType;
    private String title;
}
