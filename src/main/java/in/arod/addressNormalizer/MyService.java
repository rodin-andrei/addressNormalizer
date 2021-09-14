package in.arod.addressNormalizer;

import in.arod.addressNormalizer.model.street.OriginalStreetType;
import in.arod.addressNormalizer.repository.OriginalAddressRepository;
import in.arod.addressNormalizer.repository.street.OriginalStreetTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class MyService {
    private final OriginalAddressRepository originalAddressRepository;
    private final OriginalStreetTypeRepository originalStreetTypeRepository;

    @EventListener
    public void test(ContextStartedEvent contextStartedEvent) {
        Set<String> streetTypes = originalAddressRepository.findUniqueStreetTypes();
        streetTypes.forEach(s -> {
            OriginalStreetType originalStreetType = new OriginalStreetType();
            originalStreetType.setTitle(s);
            originalStreetTypeRepository.save(originalStreetType);
        });
    }
}
