package in.arod.addressNormalizer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "addresses_backup")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressesBackup {
    @Id
    private Long ID;

    @Column(name = "PostCode")
    private String Post_Code;

    @Column(name = "District")
    private String District;

    @Column(name = "TypeCity")
    private String TypeCity;

    @Column(name = "City")
    private String City;

    @Column(name = "TypeStreet")
    private String TypeStreet;

    @Column(name = "Street")
    private String Street;

    @Column(name = "House")
    private String House;

    @Column(name = "Flat")
    private String Flat;
}
