package io.zipcoder.repository;

import io.zipcoder.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User,Long>{
    User findByUsername(String username);
    User findByFirstName(String firstname);

    @Query(value="select u from User u WHERE LOWER(u.firstName) = LOWER(:firstName)")
    Iterable<User> findUserQuery(@Param("firstName") String firstName);
}
