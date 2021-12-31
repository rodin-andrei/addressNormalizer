package in.arod.addressNormalizer.controller;

import in.arod.addressNormalizer.controller.dto.OriginaObjectDto;
import in.arod.addressNormalizer.model.Addresses;
import in.arod.addressNormalizer.model.AddressesBackup;
import in.arod.addressNormalizer.model.CorrectTable;
import in.arod.addressNormalizer.model.city.AlternativeCityName;
import in.arod.addressNormalizer.model.city.AlternativeCityType;
import in.arod.addressNormalizer.model.city.OriginalCityName;
import in.arod.addressNormalizer.model.city.OriginalCityType;
import in.arod.addressNormalizer.model.street.AlternativeStreetName;
import in.arod.addressNormalizer.model.street.AlternativeStreetType;
import in.arod.addressNormalizer.model.street.OriginalStreetName;
import in.arod.addressNormalizer.model.street.OriginalStreetType;
import in.arod.addressNormalizer.repository.OriginalAddressBackupRepository;
import in.arod.addressNormalizer.repository.OriginalAddressRepository;
import in.arod.addressNormalizer.repository.city.AlternativeCityNameRepository;
import in.arod.addressNormalizer.repository.city.AlternativeCityTypeRepository;
import in.arod.addressNormalizer.repository.city.OriginalCityNameRepository;
import in.arod.addressNormalizer.repository.city.OriginalCityTypeRepository;
import in.arod.addressNormalizer.repository.street.AlternativeStreetNameRepository;
import in.arod.addressNormalizer.repository.street.AlternativeStreetTypeRepository;
import in.arod.addressNormalizer.repository.street.OriginalStreetNameRepository;
import in.arod.addressNormalizer.repository.street.OriginalStreetTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(path = "api")
public class AddressController {
    private final OriginalAddressRepository originalAddressRepository;
    private final OriginalCityNameRepository originalCityNameRepository;
    private final AlternativeCityNameRepository alternativeCityNameRepository;
    private final OriginalStreetNameRepository originalStreetNameRepository;
    private final AlternativeStreetNameRepository alternativeStreetNameRepository;
    private final AlternativeCityTypeRepository alternativeCityTypeRepository;
    private final OriginalCityTypeRepository originalCityTypeRepository;
    private final OriginalStreetTypeRepository originalStreetTypeRepository;
    private final AlternativeStreetTypeRepository alternativeStreetTypeRepository;
    private final OriginalAddressBackupRepository originalAddressBackupRepository;

    @GetMapping(path = "getAddresses")
    public Page<CorrectTable> getAddresses(@RequestParam int pageNumber, @RequestParam int sizeNumber,
                                           @RequestParam String filter_id,
                                           @RequestParam String filter_post_Code,
                                           @RequestParam String filter_district,
                                           @RequestParam String filter_house,
                                           @RequestParam String filter_flat,
                                           @RequestParam String filter_statut_Adresa,
                                           @RequestParam String filter_phone_Number,
                                           @RequestParam String filter_statut_Telefon,
                                           @RequestParam String filter_proprietar_Telefon,
                                           @RequestParam String filter_correct_City_Type,
                                           @RequestParam String filter_correct_City_Name,
                                           @RequestParam String filter_correct_Street_Name,
                                           @RequestParam String filter_correct_Street_Type) {

        return originalAddressRepository.findCorrectTableWithPagination(filter_id,
                filter_post_Code,
                filter_district,
                filter_correct_City_Name,
                filter_correct_City_Type,
                filter_correct_Street_Name,
                filter_correct_Street_Type,
                filter_house,
                filter_flat,
                PageRequest.of(pageNumber, sizeNumber, Sort.by("id")));
    }

    @GetMapping(path = "getOriginalCityNames")
    public Page<OriginalCityName> getOriginalCityNames(@RequestParam int pageNumber, @RequestParam int sizeNumber) {
        return originalCityNameRepository.findAll(PageRequest.of(pageNumber, sizeNumber));
    }

    @GetMapping(path = "getUniqueOriginalCityNames")
    public List<OriginaObjectDto> getUniqueOriginalCityNames() {
        return this.originalCityNameRepository.getUniqueOriginalCityNames();
    }

    @GetMapping(path = "removeAlternativeCityName")
    public void removeAlternativeCityName(@RequestParam long id) {
        alternativeCityNameRepository.deleteById(id);
        log.info("removeAlternativeCityName: " + id);
    }

    @SneakyThrows
    @GetMapping(path = "removeOriginalCityName")
    public void removeOriginalCityName(@RequestParam long id) {
        originalCityNameRepository.deleteById(id);
        log.info("removeOriginalCityName: " + id);
    }

    @GetMapping(path = "createNewOriginalCityName")
    public OriginalCityName createNewCity(@RequestParam String title) {
        OriginalCityName s = new OriginalCityName();
        s.setTitle(title);
        return this.originalCityNameRepository.save(s);
    }

    @SneakyThrows
    @GetMapping(path = "createAlternativeCityName")
    public AlternativeCityName createAlternativeCityName(@RequestParam String title, @RequestParam long originalCityNameId) {
        OriginalCityName found = this.originalCityNameRepository
                .findById(originalCityNameId)
                .orElseThrow(() -> new IllegalArgumentException("Not found original city by id " + originalCityNameId));

        AlternativeCityName s = AlternativeCityName.builder()
                .title(title)
                .originalCityName(found)
                .build();

        AlternativeCityName save = alternativeCityNameRepository.save(s);
        log.info("createAlternativeCityName: " + originalCityNameId + "(title: " + save.getTitle() + ")");
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

    @GetMapping(path = "makeOriginalCityName")
    public OriginalCityName makeOriginalCityName(@RequestParam long id) {
        AlternativeCityName alternativeCityName = alternativeCityNameRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found alternative city by id " + id));

        OriginalCityName originalCityName = alternativeCityName.getOriginalCityName();
        String oldOriginalCityNameTitle = originalCityName.getTitle();
        originalCityName.setTitle(alternativeCityName.getTitle());
        alternativeCityName.setTitle(oldOriginalCityNameTitle);
        alternativeCityNameRepository.save(alternativeCityName);
        OriginalCityName save = originalCityNameRepository.save(originalCityName);
        log.info("makeOriginalCityName: " + save.getId() + "(title: " + save.getTitle() + ")");
        return save;
    }

    @GetMapping(path = "findUnidentifiedCityNames")
    public List<String> findUnidentifiedCityNames() {
        return originalAddressRepository.findUnidentifiedCityNames();
    }


    @GetMapping(path = "getOriginalCityTypes")
    public Page<OriginalCityType> getOriginalCityTypes(@RequestParam int pageNumber, @RequestParam int sizeNumber) {
        return originalCityTypeRepository.findAll(PageRequest.of(pageNumber, sizeNumber));
    }

    @GetMapping(path = "getUniqueOriginalCityTypes")
    public List<OriginaObjectDto> getUniqueOriginalCityTypes() {
        return this.originalCityTypeRepository.getUniqueOriginalCityTypes();
    }

    @GetMapping(path = "removeAlternativeCityType")
    public void removeAlternativeCityType(@RequestParam long id) {
        alternativeCityTypeRepository.deleteById(id);
        log.info("removeAlternativeCityType: " + id);
    }

    @SneakyThrows
    @GetMapping(path = "removeOriginalCityType")
    public void removeOriginalCityType(@RequestParam long id) {
        originalCityTypeRepository.deleteById(id);
        log.info("removeOriginalCityType: " + id);
    }

    @GetMapping(path = "createNewOriginalCityType")
    public OriginalCityType createNewCityType(@RequestParam String title) {
        OriginalCityType s = new OriginalCityType();
        s.setTitle(title);
        return this.originalCityTypeRepository.save(s);
    }

    @SneakyThrows
    @GetMapping(path = "createAlternativeCityType")
    public AlternativeCityType createAlternativeCityType(@RequestParam String title, @RequestParam long originalCityTypeId) {
        OriginalCityType found = this.originalCityTypeRepository
                .findById(originalCityTypeId)
                .orElseThrow(() -> new IllegalArgumentException("Not found original city by id " + originalCityTypeId));

        AlternativeCityType s = AlternativeCityType.builder()
                .title(title)
                .originalCityType(found)
                .build();

        AlternativeCityType save = alternativeCityTypeRepository.save(s);
        log.info("createAlternativeCityType: " + originalCityTypeId + "(title: " + save.getTitle() + ")");
        return save;

    }

    @GetMapping(path = "createOriginalCityTypeFromAlternativeCityType")
    public OriginalCityType createOriginalCityTypeFromAlternativeCityType(@RequestParam long id) {
        AlternativeCityType alternativeCityType = alternativeCityTypeRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found alternative city by id " + id));

        OriginalCityType originalCityType = new OriginalCityType();
        originalCityType.setTitle(alternativeCityType.getTitle());

        OriginalCityType save = originalCityTypeRepository.save(originalCityType);
        alternativeCityTypeRepository.deleteById(alternativeCityType.getId());
        log.info("createOriginalCityTypeFromAlternativeCityType: " + save.getId() + "(title: " + save.getTitle() + ")");
        return save;
    }

    @GetMapping(path = "makeOriginalCityType")
    public OriginalCityType makeOriginalCityType(@RequestParam long id) {
        AlternativeCityType alternativeCityType = alternativeCityTypeRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found alternative city by id " + id));

        OriginalCityType originalCityType = alternativeCityType.getOriginalCityType();
        String oldOriginalCityTypeTitle = originalCityType.getTitle();
        originalCityType.setTitle(alternativeCityType.getTitle());
        alternativeCityType.setTitle(oldOriginalCityTypeTitle);
        alternativeCityTypeRepository.save(alternativeCityType);
        OriginalCityType save = originalCityTypeRepository.save(originalCityType);
        log.info("makeOriginalCityType: " + save.getId() + "(title: " + save.getTitle() + ")");
        return save;
    }

    @GetMapping(path = "findUnidentifiedCityTypes")
    public List<String> findUnidentifiedCityTypes() {
        return originalAddressRepository.findUnidentifiedCityTypes();
    }


    @GetMapping(path = "getOriginalStreetNames")
    public Page<OriginalStreetName> getOriginalStreetNames(@RequestParam int pageNumber, @RequestParam int sizeNumber) {
        return originalStreetNameRepository.findAll(PageRequest.of(pageNumber, sizeNumber));
    }

    @GetMapping(path = "getUniqueOriginalStreetNames")
    public List<OriginaObjectDto> getUniqueOriginalStreetNames() {
        return this.originalStreetNameRepository.getUniqueOriginalStreetNames();
    }

    @GetMapping(path = "removeAlternativeStreetName")
    public void removeAlternativeStreetName(@RequestParam long id) {
        alternativeStreetNameRepository.deleteById(id);
        log.info("removeAlternativeStreetName: " + id);
    }

    @SneakyThrows
    @GetMapping(path = "removeOriginalStreetName")
    public void removeOriginalStreetName(@RequestParam long id) {
        originalStreetNameRepository.deleteById(id);
        log.info("removeOriginalStreetName: " + id);
    }

    @GetMapping(path = "createNewOriginalStreetName")
    public OriginalStreetName createNewStreet(@RequestParam String title) {
        OriginalStreetName s = new OriginalStreetName();
        s.setTitle(title);
        return this.originalStreetNameRepository.save(s);
    }

    @SneakyThrows
    @GetMapping(path = "createAlternativeStreetName")
    public AlternativeStreetName createAlternativeStreetName(@RequestParam String title, @RequestParam long originalStreetNameId) {
        OriginalStreetName found = this.originalStreetNameRepository
                .findById(originalStreetNameId)
                .orElseThrow(() -> new IllegalArgumentException("Not found original city by id " + originalStreetNameId));

        AlternativeStreetName s = AlternativeStreetName.builder()
                .title(title)
                .originalStreetName(found)
                .build();

        AlternativeStreetName save = alternativeStreetNameRepository.save(s);
        log.info("createAlternativeStreetName: " + originalStreetNameId + "(title: " + save.getTitle() + ")");
        return save;

    }

    @GetMapping(path = "createOriginalStreetNameFromAlternativeStreetName")
    public OriginalStreetName createOriginalStreetNameFromAlternativeStreetName(@RequestParam long id) {
        AlternativeStreetName alternativeStreetName = alternativeStreetNameRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found alternative city by id " + id));

        OriginalStreetName originalStreetName = new OriginalStreetName();
        originalStreetName.setTitle(alternativeStreetName.getTitle());

        OriginalStreetName save = originalStreetNameRepository.save(originalStreetName);
        alternativeStreetNameRepository.deleteById(alternativeStreetName.getId());
        log.info("createOriginalStreetNameFromAlternativeStreetName: " + save.getId() + "(title: " + save.getTitle() + ")");
        return save;
    }

    @GetMapping(path = "makeOriginalStreetName")
    public OriginalStreetName makeOriginalStreetName(@RequestParam long id) {
        AlternativeStreetName alternativeStreetName = alternativeStreetNameRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found alternative city by id " + id));

        OriginalStreetName originalStreetName = alternativeStreetName.getOriginalStreetName();
        String oldOriginalStreetNameTitle = originalStreetName.getTitle();
        originalStreetName.setTitle(alternativeStreetName.getTitle());
        alternativeStreetName.setTitle(oldOriginalStreetNameTitle);
        alternativeStreetNameRepository.save(alternativeStreetName);
        OriginalStreetName save = originalStreetNameRepository.save(originalStreetName);
        log.info("makeOriginalStreetName: " + save.getId() + "(title: " + save.getTitle() + ")");
        return save;
    }

    @GetMapping(path = "findUnidentifiedStreetNames")
    public List<String> findUnidentifiedStreetNames() {
        return originalAddressRepository.findUnidentifiedStreetNames();
    }


    @GetMapping(path = "getOriginalStreetTypes")
    public Page<OriginalStreetType> getOriginalStreetTypes(@RequestParam int pageNumber, @RequestParam int sizeNumber) {
        return originalStreetTypeRepository.findAll(PageRequest.of(pageNumber, sizeNumber));
    }

    @GetMapping(path = "getUniqueOriginalStreetTypes")
    public List<OriginaObjectDto> getUniqueOriginalStreetTypes() {
        return this.originalStreetTypeRepository.getUniqueOriginalStreetTypes();
    }

    @GetMapping(path = "removeAlternativeStreetType")
    public void removeAlternativeStreetType(@RequestParam long id) {
        alternativeStreetTypeRepository.deleteById(id);
        log.info("removeAlternativeStreetType: " + id);
    }

    @SneakyThrows
    @GetMapping(path = "removeOriginalStreetType")
    public void removeOriginalStreetType(@RequestParam long id) {
        originalStreetTypeRepository.deleteById(id);
        log.info("removeOriginalStreetType: " + id);
    }

    @GetMapping(path = "createNewOriginalStreetType")
    public OriginalStreetType createNewStreetType(@RequestParam String title) {
        OriginalStreetType s = new OriginalStreetType();
        s.setTitle(title);
        return this.originalStreetTypeRepository.save(s);
    }

    @SneakyThrows
    @GetMapping(path = "createAlternativeStreetType")
    public AlternativeStreetType createAlternativeStreetType(@RequestParam String title, @RequestParam long originalStreetTypeId) {
        OriginalStreetType found = this.originalStreetTypeRepository
                .findById(originalStreetTypeId)
                .orElseThrow(() -> new IllegalArgumentException("Not found original city by id " + originalStreetTypeId));

        AlternativeStreetType s = AlternativeStreetType.builder()
                .title(title)
                .originalStreetType(found)
                .build();

        AlternativeStreetType save = alternativeStreetTypeRepository.save(s);
        log.info("createAlternativeStreetType: " + originalStreetTypeId + "(title: " + save.getTitle() + ")");
        return save;

    }

    @GetMapping(path = "createOriginalStreetTypeFromAlternativeStreetType")
    public OriginalStreetType createOriginalStreetTypeFromAlternativeStreetType(@RequestParam long id) {
        AlternativeStreetType alternativeStreetType = alternativeStreetTypeRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found alternative city by id " + id));

        OriginalStreetType originalStreetType = new OriginalStreetType();
        originalStreetType.setTitle(alternativeStreetType.getTitle());

        OriginalStreetType save = originalStreetTypeRepository.save(originalStreetType);
        alternativeStreetTypeRepository.deleteById(alternativeStreetType.getId());
        log.info("createOriginalStreetTypeFromAlternativeStreetType: " + save.getId() + "(title: " + save.getTitle() + ")");
        return save;
    }

    @GetMapping(path = "makeOriginalStreetType")
    public OriginalStreetType makeOriginalStreetType(@RequestParam long id) {
        AlternativeStreetType alternativeStreetType = alternativeStreetTypeRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found alternative city by id " + id));

        OriginalStreetType originalStreetType = alternativeStreetType.getOriginalStreetType();
        String oldOriginalStreetTypeTitle = originalStreetType.getTitle();
        originalStreetType.setTitle(alternativeStreetType.getTitle());
        alternativeStreetType.setTitle(oldOriginalStreetTypeTitle);
        alternativeStreetTypeRepository.save(alternativeStreetType);
        OriginalStreetType save = originalStreetTypeRepository.save(originalStreetType);
        log.info("makeOriginalStreetType: " + save.getId() + "(title: " + save.getTitle() + ")");
        return save;
    }

    @GetMapping(path = "findUnidentifiedStreetTypes")
    public List<String> findUnidentifiedStreetTypes() {
        return originalAddressRepository.findUnidentifiedStreetTypes();
    }

    @GetMapping(path = "updateAddress")
    public void updateAddress(@RequestParam long id, @RequestParam String post_Code, @RequestParam String district,
                              @RequestParam String correct_City_Type, @RequestParam String city,
                              @RequestParam String correct_Street_Type, @RequestParam String street,
                              @RequestParam String house, @RequestParam String flat) {

        if (originalAddressBackupRepository.findById(id).isEmpty()) {
            originalAddressBackupRepository.save(new AddressesBackup(id, post_Code, district, correct_City_Type, city,
                    correct_Street_Type, street, house, flat));
        }
        originalAddressRepository.save(new Addresses(id, post_Code, district, correct_City_Type, city,
                correct_Street_Type, street, house, flat));
    }

    @GetMapping(path = "backupAddress")
    public Optional<AddressesBackup> backupAddress(@RequestParam long id) {
        if (originalAddressBackupRepository.findById(id).isPresent()) {
            return originalAddressBackupRepository.findById(id);
        }
        return null;
    }
}
