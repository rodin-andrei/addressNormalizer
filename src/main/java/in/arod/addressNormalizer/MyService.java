package in.arod.addressNormalizer;

import in.arod.addressNormalizer.repository.OriginalAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyService {
    private final OriginalAddressRepository originalAddressRepository;

    @EventListener
    public void test(ContextStartedEvent contextStartedEvent){
        long count = originalAddressRepository.count();
    }
}
