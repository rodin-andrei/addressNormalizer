package in.arod.addressNormalizer.repository.street;

import in.arod.addressNormalizer.controller.dto.OriginaObjectDto;
import in.arod.addressNormalizer.model.street.OriginalStreetName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OriginalStreetNameRepository extends JpaRepository<OriginalStreetName, Long> {
    @Query(nativeQuery = true, value = "SELECT id, title from original_street_name")
    List<OriginaObjectDto> getUniqueOriginalStreetNames();
}
