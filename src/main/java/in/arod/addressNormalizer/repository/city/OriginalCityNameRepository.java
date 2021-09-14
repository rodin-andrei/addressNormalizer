package in.arod.addressNormalizer.repository.city;

import in.arod.addressNormalizer.model.city.AlternativeCityName;
import in.arod.addressNormalizer.model.city.OriginalCityName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface OriginalCityNameRepository extends JpaRepository<OriginalCityName, Long>  {

}
