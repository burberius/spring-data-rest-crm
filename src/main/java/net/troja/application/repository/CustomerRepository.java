package net.troja.application.repository;

import net.troja.application.model.Customer;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "customer", path = "customer")
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
    Customer findByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
