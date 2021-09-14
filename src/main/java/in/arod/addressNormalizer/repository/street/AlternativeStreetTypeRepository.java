package in.arod.addressNormalizer.repository.street;

import in.arod.addressNormalizer.model.city.AlternativeCityType;
import in.arod.addressNormalizer.model.street.AlternativeStreetType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlternativeStreetTypeRepository extends JpaRepository<AlternativeStreetType, Long>  {

}
