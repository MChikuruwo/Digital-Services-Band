package com.digitalservices.Digital.Services.Customer.services;

import com.digitalservices.Digital.Services.Customer.dao.AmountsRepository;
import com.digitalservices.Digital.Services.Customer.models.Amounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AmountsServiceImpl implements AmountsService {

    private final AmountsRepository amountsRepository;

    @Autowired
    public AmountsServiceImpl(AmountsRepository amountsRepository){
        this.amountsRepository = amountsRepository;
    }

    @Override
    public String add(Amounts amounts) {
        amountsRepository.save(amounts);
        return "Amounts Added Successfully";
    }

    @Override
    public String update(Amounts amounts) {
        Optional<Amounts> detailsFromDatabase = amountsRepository.findById(amounts.getId());
        if (!detailsFromDatabase.isPresent()) throw new EntityNotFoundException("Amounts  do not exist");
        // Use Loan type to pick one
        amounts.setAmount(detailsFromDatabase.get().getAmount());
        amountsRepository.save(amounts);
        return "Amounts  with ID " + amounts.getId() + " has been updated";      }

    @Override
    public String delete(Long id) {
        Optional<Amounts> detailsToDelete = amountsRepository.findById(id);
        if (!detailsToDelete.isPresent()){
            throw new EntityNotFoundException("Amounts with ID " + id + " do not exist");
        }
        amountsRepository.deleteById(id);
        return "Amounts request has been deleted successfully";      }

    @Override
    public List<Amounts> getAll() {
        List<Amounts> amounts = amountsRepository.findAll();
        if (amounts.isEmpty()){
            throw new EntityNotFoundException("Amounts " + " not found");
        }
        return amounts;    }

    @Override
    public Amounts getOne(Long id) {
        Optional<Amounts> amounts = amountsRepository.findById(id);
        if (!amounts.isPresent()){
            throw new EntityNotFoundException("Loan Type with the ID " + id + " does not exist");
        }
        return amounts.get();     }

    @Override
    public Amounts findByAmount(String amount) {
        Amounts amounts = amountsRepository.findByAmounts(amount);
        if (amounts == null){
            throw new EntityNotFoundException("Amounts  "
                    .concat(amount).concat(" not found"));
        }
        return amounts;    }
}
