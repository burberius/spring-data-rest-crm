package net.troja.application;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import net.troja.application.model.Appointment;
import net.troja.application.model.Customer;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.client.Hop;

/**
 * 6. User Story: As a customer I want to rate my last appointment
 */
public class UserStory6Test extends AbstractUserStoryTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test() {
        final Resource<Customer> customer = getCustomer();

        final Link link = customer.getLink("self");

        final Resources<Resource<Appointment>> appointments = traverson.follow("appointment", "search")
                .follow(Hop.rel("findByCustomerOld").withParameter("customer", link.getHref()).withParameter("size", 1)).toObject(refAppointmentList);

        assertThat(appointments.getContent().size(), equalTo(1));
        final Resource<Appointment> appointment = appointments.getContent().iterator().next();
        final String appLink = appointment.getLink("self").getHref();
        final Appointment app = appointment.getContent();

        final int newRating = 13;
        final Map<String, Object> values = new HashMap<String, Object>();
        values.put("rating", newRating);
        values.put("startTime", app.getStartTime().getTime());
        values.put("duration", app.getDuration());
        values.put("customer", customer.getId());
        values.put("type", app.getType());

        restTemplate.put(appLink.replace("http://localhost:" + port, ""), values);

        final long id = Long.parseLong(appLink.substring(appLink.lastIndexOf('/') + 1, appLink.length()));
        final Appointment found = appointmentRepo.findOne(id);

        assertThat(found.getRating(), equalTo(newRating));
    }
}
