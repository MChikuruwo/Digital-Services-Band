package zw.digitalservices.Digital.Services.Customer.services;


import zw.digitalservices.Digital.Services.Customer.models.Login;
import zw.digitalservices.Digital.Services.Customer.models.User;


import java.util.Date;
import java.util.List;

public interface LoginService {
    Login add(Login login);
    List<Login> getAll();
    Login getOne(Integer id);

    List<Login> findAllByUser(User user);
    List<Login> findAllByDate(Date date);
}
