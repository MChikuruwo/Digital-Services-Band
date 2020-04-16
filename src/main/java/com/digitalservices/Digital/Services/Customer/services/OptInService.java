package com.digitalservices.Digital.Services.Customer.services;

import com.digitalservices.Digital.Services.Customer.models.*;

import java.util.List;

public interface OptInService {
    String add(OptIn optIn);
    String update(OptIn optIn);
    String delete(Long id);
    List<OptIn> getAll();
    OptIn getOne(Long id);

    List<OptIn> findAllByDeviceType(Devices devices);
    List<OptIn> findAllByBundleType(Bundles bundles);
    List<OptIn> findAllByServiceType(Services services);
    List<OptIn> findAllByAmountsOnSms(Amounts Amounts);
    List<OptIn> findAllByAmountsOnVoice(Amounts Amounts);
    List<OptIn> findAllByAmountsOnData(Amounts Amounts);
    List<OptIn> findAllByHasBeenApproved(Boolean hasBeenApproved);

}
