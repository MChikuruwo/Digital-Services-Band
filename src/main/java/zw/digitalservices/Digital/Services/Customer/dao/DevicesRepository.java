package zw.digitalservices.Digital.Services.Customer.dao;

import zw.digitalservices.Digital.Services.Customer.models.Devices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DevicesRepository extends JpaRepository<Devices,Long> {
    Devices findByTypeName(String type);
}
