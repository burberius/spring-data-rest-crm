package net.troja.application;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import net.troja.application.model.Appointment;
import net.troja.application.model.Customer;
import net.troja.application.repository.AppointmentRepository;
import net.troja.application.repository.CustomerRepository;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.client.Traverson;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AbstractUserStoryTest {
    protected final ParameterizedTypeReference<Resource<Customer>> refCustomer = new ParameterizedTypeReference<Resource<Customer>>() {
    };
    protected final ParameterizedTypeReference<Resources<Resource<Appointment>>> refAppointmentList = new ParameterizedTypeReference<Resources<Resource<Appointment>>>() {
    };

    @Autowired
    protected TestRestTemplate restTemplate;

    @Autowired
    protected CustomerRepository customerRepo;

    @Autowired
    protected AppointmentRepository appointmentRepo;

    @Autowired
    protected TestDataGenerator testDataGenerator;

    @LocalServerPort
    protected int port;

    protected Traverson traverson;

    @Before
    public void setUp() throws URISyntaxException {
        appointmentRepo.deleteAll();
        customerRepo.deleteAll();
        testDataGenerator.fill();
        traverson = new Traverson(new URI("http://localhost:" + port + "/"), MediaTypes.HAL_JSON);
    }

    protected Resource<Customer> getCustomer() {
        final Map<String, Object> names = new HashMap<>();
        names.put("firstName", "Donald");
        names.put("lastName", "Duck");
        final Resource<Customer> customer = traverson.follow("customer", "search", "findByFirstNameAndLastName").withTemplateParameters(names).toObject(refCustomer);

        assertThat(customer.getContent(), notNullValue());
        return customer;
    }
}
