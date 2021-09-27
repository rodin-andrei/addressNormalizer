package in.arod.addressNormalizer.repository;

import in.arod.addressNormalizer.model.CorrectTable;
import in.arod.addressNormalizer.model.OriginalAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OriginalAddressRepository extends PagingAndSortingRepository<OriginalAddress, Long> {

    @Query(nativeQuery = true, value = "select DISTINCT(title) from  original_city_name")
    List<String> findUniqueOriginalCityNames();

    @Query(nativeQuery = true,
            value = "SELECT * FROM kek WHERE id LIKE ?1 " +
                    "AND post_code LIKE ?2 " +
                    "AND district LIKE ?3 " +
                    "AND correct_city_name LIKE ?4 " +
                    "AND correct_city_type LIKE ?5 " +
                    "AND correct_street_name LIKE ?6 " +
                    "AND correct_street_type LIKE ?7 " +
                    "AND house LIKE ?8 " +
                    "AND flat LIKE ?9 " +
                    "AND statut_adresa LIKE ?10 " +
                    "AND phone_number LIKE ?11 " +
                    "AND statut_telefon LIKE ?12 " +
                    "AND proprietar_telefon LIKE ?13 ",
            countQuery = "SELECT count(*) FROM kek WHERE id LIKE ?1 " +
                    "AND post_code LIKE ?2 " +
                    "AND district LIKE ?3 " +
                    "AND correct_city_name LIKE ?4 " +
                    "AND correct_city_type LIKE ?5 " +
                    "AND correct_street_name LIKE ?6 " +
                    "AND correct_street_type LIKE ?7 " +
                    "AND house LIKE ?8 " +
                    "AND flat LIKE ?9 " +
                    "AND statut_adresa LIKE ?10 " +
                    "AND phone_number LIKE ?11 " +
                    "AND statut_telefon LIKE ?12 " +
                    "AND proprietar_telefon LIKE ?13 ")
    Page<CorrectTable> findCorrectTableWithPagination(String id,
                                                      String post_code,
                                                      String district,
                                                      String correct_city_name,
                                                      String correct_city_type,
                                                      String correct_street_name,
                                                      String correct_street_type,
                                                      String house,
                                                      String flat,
                                                      String statut_adresa,
                                                      String phone_number,
                                                      String statut_telefon,
                                                      String proprietar_telefon,
                                                      Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM unidentifiedCityName ")
    List<String> findUnidentifiedCityNames();

    @Query(nativeQuery = true, value = "SELECT * FROM unidentifiedStreetName ")
    List<String> findUnidentifiedStreetNames();


    @Query(nativeQuery = true, value = "SELECT * FROM unidentifiedCityType ")
    List<String> findUnidentifiedCityTypes();

    @Query(nativeQuery = true, value = "SELECT * FROM unidentifiedStreetType")
    List<String> findUnidentifiedStreetTypes();
}
