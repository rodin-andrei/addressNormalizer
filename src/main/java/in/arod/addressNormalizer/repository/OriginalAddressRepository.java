package in.arod.addressNormalizer.repository;

import in.arod.addressNormalizer.model.OriginalAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OriginalAddressRepository extends JpaRepository<OriginalAddress, Long>  {

}
