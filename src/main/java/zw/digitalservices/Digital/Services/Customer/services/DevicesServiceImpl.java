package zw.digitalservices.Digital.Services.Customer.services;

import zw.digitalservices.Digital.Services.Customer.dao.DevicesRepository;
import zw.digitalservices.Digital.Services.Customer.models.Devices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DevicesServiceImpl implements DeviceService {
    private final DevicesRepository devicesRepository;

    @Autowired
    public DevicesServiceImpl(DevicesRepository devicesRepository) {
        this.devicesRepository = devicesRepository;
    }

    @Override
    public String add(Devices devices) {
        devicesRepository.save(devices);
        return "Device has been successfully added";    }

    @Override
    public String update(Devices devices) {
        Optional<Devices> detailsFromDatabase = devicesRepository.findById(devices.getId());
        if (!detailsFromDatabase.isPresent()) throw new EntityNotFoundException("Device does not exist");
        devices.setTypeName(detailsFromDatabase.get().getTypeName());
        devicesRepository.save(devices);
        return "Device  with ID " + devices.getId() + " has been updated";    }

    @Override
    public String delete(Long id) {
        Optional<Devices> detailsToDelete = devicesRepository.findById(id);
        if (!detailsToDelete.isPresent()){
            throw new EntityNotFoundException("Device with ID " + id + " does not exist");
        }
        devicesRepository.deleteById(id);
        return "Device has been deleted successfully";    }

    @Override
    public List<Devices> getAll() {
        List<Devices> devices = devicesRepository.findAll();
        if (devices.isEmpty()){
            throw new EntityNotFoundException("Devices " + " not found");
        }
        return devices;     }

    @Override
    public Devices getOne(Long id) {
        Optional<Devices> devices = devicesRepository.findById(id);
        if (!devices.isPresent()){
            throw new EntityNotFoundException("Device with the ID " + id + " does not exist");
        }
        return devices.get();    }

    @Override
    public Devices findByType(String type) {
        Devices devices = devicesRepository.findByTypeName(type);
        if (devices == null){
            throw new EntityNotFoundException("Device  "
                    .concat(type).concat(" not found"));
        }
        return devices;
    }
}