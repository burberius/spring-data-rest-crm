package net.troja.application;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URISyntaxException;
import java.util.Iterator;

import net.troja.application.model.Customer;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * 1. User Story: As an audiologist I want to create my customers
 */
public class UserStory1Test extends AbstractUserStoryTest {
    @Override
    @Before
    public void setUp() throws URISyntaxException {
        appointmentRepo.deleteAll();
        customerRepo.deleteAll();
    }

    @Test
    public void test() {
        assertThat(customerRepo.count(), equalTo(0l));
        final Customer donald = new Customer("Donald", "Duck", "03012341");

        final ResponseEntity<Customer> result = restTemplate.postForEntity("/customer", donald, Customer.class);

        assertThat(result.getStatusCode(), equalTo(HttpStatus.CREATED));

        assertThat(customerRepo.count(), equalTo(1l));
        final Iterator<Customer> iterator = customerRepo.findAll().iterator();
        assertThat(iterator.hasNext(), equalTo(true));
        final Customer customer = iterator.next();
        assertThat(customer.getFirstName(), equalTo(donald.getFirstName()));
        assertThat(customer.getLastName(), equalTo(donald.getLastName()));
        assertThat(customer.getPhoneNumber(), equalTo(donald.getPhoneNumber()));
    }
}
