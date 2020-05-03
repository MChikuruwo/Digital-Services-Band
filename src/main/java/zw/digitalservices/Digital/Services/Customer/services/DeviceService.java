package zw.digitalservices.Digital.Services.Customer.services;

import zw.digitalservices.Digital.Services.Customer.models.Devices;

import java.util.List;

public interface DeviceService {
    String add(Devices devices);
    String update(Devices devices);
    String delete(Long id);
    List<Devices> getAll();
    Devices getOne(Long id);


    Devices findByType(String devices);
}
