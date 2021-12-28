package in.arod.addressNormalizer.repository;

import in.arod.addressNormalizer.model.CorrectTable;
import in.arod.addressNormalizer.model.Addresses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OriginalAddressRepository extends PagingAndSortingRepository<Addresses, Long> {

    @Query(nativeQuery = true, value = "select DISTINCT(title) from  original_city_name")
    List<String> findUniqueOriginalCityNames();

    @Query(nativeQuery = true,
            value = "SELECT * FROM addressesView WHERE id LIKE ?1 " +
                    "AND post_code LIKE ?2 " +
                    "AND district LIKE ?3 " +
                    "AND correct_city_name LIKE ?4 " +
                    "AND correct_city_type LIKE ?5 " +
                    "AND correct_street_name LIKE ?6 " +
                    "AND correct_street_type LIKE ?7 " +
                    "AND house LIKE ?8 " +
                    "AND flat LIKE ?9 ",
            countQuery = "SELECT count(*) FROM addressesView WHERE id LIKE ?1 " +
                    "AND post_code LIKE ?2 " +
                    "AND district LIKE ?3 " +
                    "AND correct_city_name LIKE ?4 " +
                    "AND correct_city_type LIKE ?5 " +
                    "AND correct_street_name LIKE ?6 " +
                    "AND correct_street_type LIKE ?7 " +
                    "AND house LIKE ?8 " +
                    "AND flat LIKE ?9 ")
    Page<CorrectTable> findCorrectTableWithPagination(String id,
                                                      String post_code,
                                                      String district,
                                                      String correct_city_name,
                                                      String correct_city_type,
                                                      String correct_street_name,
                                                      String correct_street_type,
                                                      String house,
                                                      String flat,
                                                      Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM alternativeCityName ")
    List<String> findUnidentifiedCityNames();

    @Query(nativeQuery = true, value = "SELECT * FROM alternativeStreetName ")
    List<String> findUnidentifiedStreetNames();


    @Query(nativeQuery = true, value = "SELECT * FROM alternativeCityType ")
    List<String> findUnidentifiedCityTypes();

    @Query(nativeQuery = true, value = "SELECT * FROM alternativeStreetType")
    List<String> findUnidentifiedStreetTypes();
}
