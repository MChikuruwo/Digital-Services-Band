package zw.digitalservices.Digital.Services.Customer.dao;

import zw.digitalservices.Digital.Services.Customer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRoleRepository extends JpaRepository<User, Integer> {
}
