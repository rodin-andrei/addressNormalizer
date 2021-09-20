package in.arod.addressNormalizer.repository;

import in.arod.addressNormalizer.model.OriginalAddress;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OriginalAddressRepository extends PagingAndSortingRepository<OriginalAddress, Long> {
    @Query("select TypeStreet from  OriginalAddress  GROUP BY TypeStreet")
    List<String> findUniqueStreetTypes();

    @Query("select City from  OriginalAddress  GROUP BY City")
    List<String> findUniqueCities();
}
