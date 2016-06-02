package io.zipcoder.repository;

import io.zipcoder.domain.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address,Long>{
}
