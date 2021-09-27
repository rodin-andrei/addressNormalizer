package in.arod.addressNormalizer.repository.city;

import in.arod.addressNormalizer.controller.dto.OriginaObjectDto;
import in.arod.addressNormalizer.model.city.OriginalCityName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OriginalCityNameRepository extends JpaRepository<OriginalCityName, Long> {
    @Query(nativeQuery = true, value = "SELECT id, title from original_city_name")
    List<OriginaObjectDto> getUniqueOriginalCityNames();
}
