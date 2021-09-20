package in.arod.addressNormalizer.controller;

import in.arod.addressNormalizer.model.OriginalAddress;
import in.arod.addressNormalizer.model.city.AlternativeCityName;
import in.arod.addressNormalizer.model.city.OriginalCityName;
import in.arod.addressNormalizer.repository.OriginalAddressRepository;
import in.arod.addressNormalizer.repository.city.AlternativeCityNameRepository;
import in.arod.addressNormalizer.repository.city.OriginalCityNameRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(path = "api")
public class AddressController {
    private final OriginalAddressRepository originalAddressRepository;
    private final OriginalCityNameRepository originalCityNameRepository;
    private final AlternativeCityNameRepository alternativeCityNameRepository;

    @GetMapping(path = "getAddresses")
    public Page<OriginalAddress> getAddresses(@RequestParam int pageNumber, @RequestParam int sizeNumber) {
        System.out.println("6");
        Page<OriginalAddress> all = originalAddressRepository.findAll(PageRequest.of(pageNumber, sizeNumber));
        return all;
    }

    @GetMapping(path = "getDictionaryCityNames")
    public Page<OriginalCityName> getDictionaryCities(@RequestParam int pageNumber, @RequestParam int sizeNumber) {
        System.out.println("5");
        Page<OriginalCityName> all = originalCityNameRepository.findAll(PageRequest.of(pageNumber, sizeNumber));
        return all;
    }

    @GetMapping(path = "deleteAlternativeCityName")
    public void deleteAlterantiveCityName(@RequestParam long id) {
        System.out.println("4");
        alternativeCityNameRepository.deleteById(id);
        log.info("deleteAlternativeCityName: " + id);
    }

    @SneakyThrows
    @GetMapping(path = "removeOriginalCityName")
    public void removeOriginalCityName(@RequestParam long id) {
        System.out.println("3");
        originalCityNameRepository.deleteById(id);
        log.info("removeOriginalCityName: " + id);

    }

    @SneakyThrows
    @GetMapping(path = "createAlternativeCityName")
    public AlternativeCityName createAlternativeCityName(@RequestParam String title, @RequestParam long id) {
        System.out.println("2");
        OriginalCityName found = this.originalCityNameRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found original city by id " + id));

        AlternativeCityName s = AlternativeCityName.builder()
                .title(title)
                .originalCityName(found)
                .build();

        AlternativeCityName save = alternativeCityNameRepository.save(s);
        log.info("createAlternativeCityName: " + id + "(title: " + save.getTitle() + ")");
        return save;

    }

    @GetMapping(path = "createOriginalCityNameFromAlternativeCityName")
    public OriginalCityName createOriginalCityNameFromAlternativeCityName(@RequestParam long id) {
        AlternativeCityName alternativeCityName = alternativeCityNameRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found alternative city by id " + id));

        OriginalCityName originalCityName = new OriginalCityName();
        originalCityName.setTitle(alternativeCityName.getTitle());

        OriginalCityName save = originalCityNameRepository.save(originalCityName);
        alternativeCityNameRepository.deleteById(alternativeCityName.getId());
        log.info("createOriginalCityNameFromAlternativeCityName: " + save.getId() + "(title: " + save.getTitle() + ")");
        return save;
    }


}
