package zw.digitalservices.Digital.Services.Customer.dao;

import zw.digitalservices.Digital.Services.Customer.models.Amounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmountsRepository extends JpaRepository<Amounts,Long> {
    Amounts findByAmount(String amounts);
}
