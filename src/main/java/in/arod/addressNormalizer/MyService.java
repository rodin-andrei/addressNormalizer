package in.arod.addressNormalizer;

import in.arod.addressNormalizer.repository.OriginalAddressRepository;
import in.arod.addressNormalizer.repository.street.OriginalStreetTypeRepository;
import in.arod.addressNormalizer.service.impl.PairsAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyService {
    private final OriginalAddressRepository originalAddressRepository;
    private final OriginalStreetTypeRepository originalStreetTypeRepository;
    private final PairsAlgorithm pairsAlgoritm;


    @EventListener
    public void test(ContextStartedEvent contextStartedEvent) {
        List<String> streetTypes = originalAddressRepository.findUniqueCities();
        for (int i = 0; i < streetTypes.size(); i++) {
            for (int j = i + 1; j < streetTypes.size(); j++) {
                try {
                    double v = pairsAlgoritm.compare(streetTypes.get(i), streetTypes.get(j));
                    if (v > 0.80)
                        System.out.printf("%30s  %30s  %f \n", streetTypes.get(i), streetTypes.get(j), v);
                } catch (Exception e) {
//                    System.err.println(streetTypes.get(i) + "   " + streetTypes.get(j));
                }
            }
        }
    }
}
