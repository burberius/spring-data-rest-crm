package net.troja.application;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TimeZone;

import net.troja.application.model.Appointment;
import net.troja.application.model.Customer;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * 2. User Story: As an audiologist I want to create appointments with a
 * customer
 */
public class UserStory2Test extends AbstractUserStoryTest {
    @Override
    @Before
    public void setUp() throws URISyntaxException {
        appointmentRepo.deleteAll();
        customerRepo.deleteAll();
    }

    @Test
    public void test() {
        final Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.add(Calendar.DAY_OF_MONTH, 5);
        assertThat(appointmentRepo.count(), equalTo(0l));

        final ResponseEntity<Customer> customerResult = restTemplate.postForEntity("/customer", new Customer("Donald", "Duck", "03012341"), Customer.class);

        final Appointment expected = new Appointment(customerResult.getBody(), "Measurement", calendar.getTime(), 60);
        expected.setId(1l);
        expected.getCustomer().setId(1l);

        final Map<String, Object> appointment = new HashMap<String, Object>();
        appointment.put("type", expected.getType());
        appointment.put("startTime", expected.getStartTime().getTime());
        appointment.put("duration", expected.getDuration());
        appointment.put("customer", customerResult.getHeaders().getLocation());

        final ResponseEntity<Void> result = restTemplate.postForEntity("/appointment", appointment, Void.class);

        assertThat(result.getStatusCode(), equalTo(HttpStatus.CREATED));

        assertThat(appointmentRepo.count(), equalTo(1l));

        final Iterator<Appointment> iterator = appointmentRepo.findAll().iterator();
        assertThat(iterator.hasNext(), equalTo(true));
        final Appointment resultAppointment = iterator.next();
        assertThat(resultAppointment.getCustomer(), notNullValue());
        assertThat(resultAppointment.getStartTime().getTime(), equalTo(expected.getStartTime().getTime()));
        // ...
    }
}
