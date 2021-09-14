package in.arod.addressNormalizer.repository;

import in.arod.addressNormalizer.model.OriginalAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface OriginalAddressRepository extends JpaRepository<OriginalAddress, Long>  {
    @Query("select TypeStreet from  OriginalAddress  GROUP BY TypeStreet")
    Set<String> findUniqueStreetTypes();
}
