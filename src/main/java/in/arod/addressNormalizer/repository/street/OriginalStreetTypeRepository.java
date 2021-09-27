package in.arod.addressNormalizer.repository.street;

import in.arod.addressNormalizer.controller.dto.OriginaObjectDto;
import in.arod.addressNormalizer.model.street.OriginalStreetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OriginalStreetTypeRepository extends JpaRepository<OriginalStreetType, Long> {
    @Query(nativeQuery = true, value = "SELECT id, title from original_street_type")
    List<OriginaObjectDto> getUniqueOriginalStreetTypes();
}
