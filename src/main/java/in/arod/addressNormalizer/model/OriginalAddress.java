package in.arod.addressNormalizer.model;

import javax.persistence.*;

@Entity
public class OriginalAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PostCode")
    private String PostCode;

    @Column(name = "Distric")
    private String Distric;

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

    @Column(name = "statutADRESA")
    private String statutADRESA;

    @Column(name = "PhoneNumber")
    private String PhoneNumber;

    @Column(name = "statuttelefon")
    private String statuttelefon;

    @Column(name = "proprietartelefon")
    private String proprietartelefon;

}
