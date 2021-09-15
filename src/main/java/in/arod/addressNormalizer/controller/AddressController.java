package in.arod.addressNormalizer.controller;

import in.arod.addressNormalizer.model.OriginalAddress;
import in.arod.addressNormalizer.repository.OriginalAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AddressController {
    private final OriginalAddressRepository originalAddressRepository;

    @GetMapping(path = "getAdresses")
    public Page<OriginalAddress> getAddresses(@RequestParam int pageNumber, @RequestParam int sizeNumber) {
        Page<OriginalAddress> all = originalAddressRepository.findAll(PageRequest.of(pageNumber, sizeNumber));
        return all;
    }
}
