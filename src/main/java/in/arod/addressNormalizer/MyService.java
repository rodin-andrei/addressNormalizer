package in.arod.addressNormalizer;

import in.arod.addressNormalizer.model.CorrectTable;
import in.arod.addressNormalizer.repository.OriginalAddressRepository;
import in.arod.addressNormalizer.service.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
//        Page<CorrectTable> r11 = originalAddressRepository.findCorrectTableWithPagination("Chi%", "%", PageRequest.of(0,10));
//        System.out.println(r11);
//        CorrectTable t = r11.getContent().get(0);
//        String city = t.getCITY();
//        String correct_city_name = t.getCORRECT_CITY_NAME();
//        String correct_city_type = t.getCORRECT_CITY_TYPE();
//        String correct_street_type = t.getCORRECT_STREET_TYPE();
//        String correct_street_name = t.getCORRECT_STREET_NAME();
//        String district = t.getDISTRICT();
//        String flat = t.getFLAT();
//        String house = t.getHOUSE();
//        long id = t.getID();
//        String phone_number = t.getPHONE_NUMBER();
//        long post_code = t.getPOST_CODE();

//        List<String> streetTypes = originalAddressRepository.findUniqueCities();
//        for (int i = 0; i < streetTypes.size(); i++) {
//            for (int j = i + 1; j < streetTypes.size(); j++) {
//                String s1 = streetTypes.get(i);
//                String s2 = streetTypes.get(j);
//                activeAlgorithms.forEach(algoName -> {
//                    try {
//                        Algorithm algorithm = algorithms.get(algoName);
//                        float compareResult = algorithm.compare(s1, s2);
//                        if (compareResult <= algorithm.getMax() && compareResult >= algorithm.getMin())
//                            System.out.printf("%30s %30s  %30s  %f \n", algoName, s1, s2, compareResult);
//                    } catch (Exception e) {
////                        System.err.printf("%30s %25s  %25s  \n", algoName, s1, s2);
//                    }
//                });
//            }
//        }
    }
}
