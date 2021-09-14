package in.arod.addressNormalizer.repository;

import in.arod.addressNormalizer.model.OriginalAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OriginalAddressRepository extends JpaRepository<OriginalAddress, Long> {
    @Query("select TypeStreet from  OriginalAddress  GROUP BY TypeStreet")
    List<String> findUniqueStreetTypes();

    @Query("select City from  OriginalAddress  GROUP BY City")
    List<String> findUniqueCities();
}
