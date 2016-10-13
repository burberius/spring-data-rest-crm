package net.troja.application;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.net.URISyntaxException;
import java.util.Date;

import net.troja.application.model.Appointment;
import net.troja.application.model.Customer;

import org.junit.Test;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.client.Hop;

/**
 * 5. User Story: As a customer I want to see my next appointment
 */
public class UserStory5Test extends AbstractUserStoryTest {
    @Test
    public void test() throws URISyntaxException {
        final Resource<Customer> customer = getCustomer();

        final Link link = customer.getLink("self");

        final Resources<Resource<Appointment>> appointments = traverson.follow("appointment", "search")
                .follow(Hop.rel("findByCustomerNext").withParameter("customer", link.getHref()).withParameter("size", 1)).toObject(refAppointmentList);

        assertThat(appointments.getContent().size(), equalTo(1));

        final Appointment appointment = appointments.getContent().iterator().next().getContent();
        assertThat(appointment.getStartTime().getTime(), greaterThan(new Date().getTime()));
    }
}
