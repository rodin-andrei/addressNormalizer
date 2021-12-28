package in.arod.addressNormalizer.repository;

import in.arod.addressNormalizer.model.AddressesBackup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OriginalAddressBackupRepository extends JpaRepository<AddressesBackup, Long> {
}
