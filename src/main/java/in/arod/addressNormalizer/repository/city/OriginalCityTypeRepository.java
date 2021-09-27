package in.arod.addressNormalizer.repository.city;

import in.arod.addressNormalizer.controller.dto.OriginaObjectDto;
import in.arod.addressNormalizer.model.city.OriginalCityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OriginalCityTypeRepository extends JpaRepository<OriginalCityType, Long>  {
    @Query(nativeQuery = true, value = "SELECT id, title from original_city_type")
    List<OriginaObjectDto> getUniqueOriginalCityTypes();
}
