package com.digitalservices.Digital.Services.Customer.services;

import com.digitalservices.Digital.Services.Customer.dao.ServicesOfferedRepository;
import com.digitalservices.Digital.Services.Customer.models.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServicesOfferedServiceImpl implements ServiceOfferedService{
    private final ServicesOfferedRepository servicesOfferedRepository;

    @Autowired
    public ServicesOfferedServiceImpl(ServicesOfferedRepository servicesOfferedRepository) {
        this.servicesOfferedRepository = servicesOfferedRepository;
    }

    @Override
    public String add(Services services) {
        servicesOfferedRepository.save(services);
        return "Service has been successfully added";    }

    @Override
    public String update(Services services) {
        Optional<Services> detailsFromDatabase = servicesOfferedRepository.findById(services.getId());
        if (!detailsFromDatabase.isPresent()) throw new EntityNotFoundException("Service  does not exist");
        // Use Loan type to pick one
        services.setServiceType(detailsFromDatabase.get().getServiceType());
        servicesOfferedRepository.save(services);
        return "Service with ID " + services.getId() + " has been updated";    }

    @Override
    public String delete(Long id) {
        Optional<Services> detailsToDelete = servicesOfferedRepository.findById(id);
        if (!detailsToDelete.isPresent()){
            throw new EntityNotFoundException("Service with ID " + id + " does not exist");
        }
        servicesOfferedRepository.deleteById(id);
        return "Service has been deleted successfully";    }

    @Override
    public List<Services> getAll() {
        List<Services> services = servicesOfferedRepository.findAll();
        if (services.isEmpty()){
            throw new EntityNotFoundException("Services " + " not found");
        }
        return services;     }

    @Override
    public Services getOne(Long id) {
        Optional<Services> services = servicesOfferedRepository.findById(id);
        if (!services.isPresent()){
            throw new EntityNotFoundException("Service with the ID " + id + " does not exist");
        }
        return services.get();    }

    @Override
    public Services findByType(String type) {
        Services services = servicesOfferedRepository.findByServiceType(type);
        if (services == null){
            throw new EntityNotFoundException("Service  "
                    .concat(type).concat(" not found"));
        }
        return services;
    }
}
