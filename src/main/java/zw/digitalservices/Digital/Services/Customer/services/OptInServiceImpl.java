package zw.digitalservices.Digital.Services.Customer.services;

import zw.digitalservices.Digital.Services.Customer.dao.OptInRepository;
import zw.digitalservices.Digital.Services.Customer.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OptInServiceImpl  implements OptInService{

    private final OptInRepository optInRepository;

    @Autowired
    public OptInServiceImpl(OptInRepository optInRepository) {
        this.optInRepository = optInRepository;
    }


    @Override
    public String add(OptIn optIn) {
        optInRepository.save(optIn);
        return "Opt-in request has been submitted";
    }

    @Override
    public String update(OptIn optIn) {
        Optional<OptIn> detailsFromDatabase = optInRepository.findById(optIn.getId());
        if (!detailsFromDatabase.isPresent()) throw new EntityNotFoundException("Opt-in request does not exist");
        // Carry date created timestamp
        optIn.setDateCreated(detailsFromDatabase.get().getDateCreated());
        optInRepository.save(optIn);
        return "Opt-in request with ID " + optIn.getId() + " has been updated";    }

    @Override
    public String delete(Long id) {
        Optional<OptIn> detailsToDelete = optInRepository.findById(id);
        if (!detailsToDelete.isPresent()){
            throw new EntityNotFoundException("Opt-in request with ID " + id + " does not exist");
        }
        optInRepository.deleteById(id);
        return "Opt-in request has been deleted";    }

    @Override
    public List<OptIn> getAll() {
        List<OptIn> optIns = optInRepository.findAll();
        if (optIns.isEmpty()){
            throw new EntityNotFoundException("Opt-in requests not found");
        }
        return optIns;    }

    @Override
    public OptIn getOne(Long id) {
        Optional<OptIn> optIn = optInRepository.findById(id);
        if (!optIn.isPresent()){
            throw new EntityNotFoundException("Opt-in request with the ID " + id + " does not exist");
        }
        return optIn.get();    }

    @Override
    public List<OptIn> findAllByDeviceType(Devices devices) {
        List<OptIn> optIns = optInRepository.findAllByDeviceType(devices);
        if (optIns.isEmpty()){
            throw new EntityNotFoundException("Device Types for for:" .concat(devices.getTypeName().concat(" not found")));
        }
        return optIns;    }

    @Override
    public List<OptIn> findAllByBundleType(Bundles bundles) {
        List<OptIn> optIns = optInRepository.findAllBySoughtBundle(bundles);
        if (optIns.isEmpty()){
            throw new EntityNotFoundException("Bundle Type "
                    .concat(bundles.getBundleTypes()).concat(" not found"));
        }
        return optIns;    }

    @Override
    public List<OptIn> findAllByServiceType(Services services) {
        List<OptIn> optIns = optInRepository.findAllByServiceOffered(services);
        if (optIns.isEmpty()){
            throw new EntityNotFoundException("Service Type "
                    .concat(services.getServiceType()).concat(" not found"));
        }
        return optIns;    }

    @Override
    public List<OptIn> findAllByAmountsOnSms(Amounts amounts) {
        List<OptIn> optIns = optInRepository.findAllByAmountsOnSms(amounts);
        if (optIns.isEmpty()){
            throw new EntityNotFoundException("Amount "
                    .concat(amounts.getAmount()).concat(" not found"));
        }
        return optIns;    }

    @Override
    public List<OptIn> findAllByAmountsOnData(Amounts amounts) {
        List<OptIn> optIns = optInRepository.findAllByAmountsOnData(amounts);
        if (optIns.isEmpty()){
            throw new EntityNotFoundException("Amount"
                    .concat(amounts.getAmount()).concat(" not found"));
        }
        return optIns;    }


    @Override
    public List<OptIn> findAllByAmountsOnVoice(Amounts amounts) {
        List<OptIn> optIns = optInRepository.findAllByAmountsOnVoice(amounts);
        if (optIns.isEmpty()){
            throw new EntityNotFoundException("Amount "
                    .concat(amounts.getAmount()).concat(" not found"));
        }
        return optIns;    }


    @Override
    public List<OptIn> findAllByHasBeenApproved(Boolean hasBeenApproved) {
        return optInRepository.findAllByHasBeenApproved(hasBeenApproved);
    }

}
