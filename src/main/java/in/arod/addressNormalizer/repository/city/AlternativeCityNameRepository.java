package in.arod.addressNormalizer.repository.city;

import in.arod.addressNormalizer.model.OriginalAddress;
import in.arod.addressNormalizer.model.city.AlternativeCityName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface AlternativeCityNameRepository extends JpaRepository<AlternativeCityName, Long>  {

}
