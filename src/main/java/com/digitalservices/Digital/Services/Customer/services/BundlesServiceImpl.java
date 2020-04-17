package com.digitalservices.Digital.Services.Customer.services;

import com.digitalservices.Digital.Services.Customer.dao.BundlesRepository;
import com.digitalservices.Digital.Services.Customer.models.Bundles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BundlesServiceImpl implements BundlesService {

    private final BundlesRepository bundlesRepository;

        @Autowired
        public BundlesServiceImpl(BundlesRepository bundlesRepository) {
            this.bundlesRepository = bundlesRepository;
        }

        @Override
        public String add(Bundles bundles) {
            bundlesRepository.save(bundles);
            return "Bundles request has been successfully added";    }

        @Override
        public String update(Bundles bundles) {
            Optional<Bundles> detailsFromDatabase = bundlesRepository.findById(bundles.getId());
            if (!detailsFromDatabase.isPresent()) throw new EntityNotFoundException("Bundle does not exist");
            // Use Bundle to pick one
            bundles.setBundleTypes(detailsFromDatabase.get().getBundleTypes());
            bundlesRepository.save(bundles);
            return "Loan Type  with ID " + bundles.getId() + " has been updated";    }

        @Override
        public String delete(Long id) {
            Optional<Bundles> detailsToDelete = bundlesRepository.findById(id);
            if (!detailsToDelete.isPresent()){
                throw new EntityNotFoundException("Bundle with ID " + id + " does not exist");
            }
            bundlesRepository.deleteById(id);
            return "Bundle  has been deleted successfully";    }

        @Override
        public List<Bundles> getAll() {
            List<Bundles> bundles = bundlesRepository.findAll();
            if (bundles.isEmpty()){
                throw new EntityNotFoundException("Bundle " + " not found");
            }
            return bundles;     }

        @Override
        public Bundles getOne(Long id) {
            Optional<Bundles> bundles = bundlesRepository.findById(id);
            if (!bundles.isPresent()){
                throw new EntityNotFoundException("Bundle with the ID " + id + " does not exist");
            }
            return bundles.get();    }

        @Override
        public Bundles findByType(String type) {
            Bundles bundles = bundlesRepository.findByBundleTypes(type);
            if (bundles == null){
                throw new EntityNotFoundException("Bundle "
                        .concat(type).concat(" not found"));
            }
            return bundles;
        }
}
