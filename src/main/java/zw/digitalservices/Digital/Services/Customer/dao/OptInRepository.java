package zw.digitalservices.Digital.Services.Customer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import zw.digitalservices.Digital.Services.Customer.models.*;

import java.util.List;

public interface OptInRepository extends JpaRepository<OptIn,Long> {
    List<OptIn> findAllByDeviceType(Devices devices);
    List<OptIn> findAllBySoughtBundle(Bundles bundles);
    List<OptIn> findAllByServiceOffered(Services services);
    List<OptIn> findAllByAmountsOnData(Amounts amounts);
    List<OptIn> findAllByAmountsOnSms(Amounts amounts);
    List<OptIn> findAllByAmountsOnVoice(Amounts amounts);
    List<OptIn> findAllByHasBeenApproved(Boolean hasBeenApproved);
    OptIn findByMobileNumber(@Param("mobileNumber") String mobileNumber);



}
