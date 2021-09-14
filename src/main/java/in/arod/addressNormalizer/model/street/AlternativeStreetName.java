package in.arod.addressNormalizer.model.street;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class AlternativeStreetName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "original_street_id", nullable = false)
    private OriginalStreetName originalStreetName;
    private String title;
}
