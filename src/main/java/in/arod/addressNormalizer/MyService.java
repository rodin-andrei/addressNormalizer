package in.arod.addressNormalizer;

import in.arod.addressNormalizer.repository.OriginalAddressRepository;
import in.arod.addressNormalizer.service.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MyService {
    private final OriginalAddressRepository originalAddressRepository;
    private final Map<String, Algorithm> algorithms;
    @Value("#{'${algorithmes.active}'.split(',')}")
    private List<String> activeAlgorithms;


    @EventListener
    public void test(ContextStartedEvent contextStartedEvent) {
        List<String> streetTypes = originalAddressRepository.findUniqueCities();
        for (int i = 0; i < streetTypes.size(); i++) {
            for (int j = i + 1; j < streetTypes.size(); j++) {
                String s1 = streetTypes.get(i);
                String s2 = streetTypes.get(j);
                activeAlgorithms.forEach(algoName -> {
                    try {
                        Algorithm algorithm = algorithms.get(algoName);
                        float compareResult = algorithm.compare(s1, s2);
                        System.out.printf("%30s %30s  %30s  %f \n", algoName, s1, s2, compareResult);
                    }catch (Exception e){
                        System.err.printf("%30s %25s  %25s  \n", algoName, s1, s2);
                    }
                });
                System.out.println();
            }
        }
    }
}
