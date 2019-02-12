package com.tts.h2example.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.tts.h2example.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
}
