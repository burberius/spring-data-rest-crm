package net.troja.application;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

import net.troja.application.model.Appointment;
import net.troja.application.model.Customer;

import org.junit.Test;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.client.Hop;
import org.springframework.hateoas.client.Traverson;
import org.springframework.hateoas.client.Traverson.TraversalBuilder;

/**
 * 3. User Story: As an audiologist I want to see an overview of all
 * appointments and their ratings
 */
public class UserStory3Test extends AbstractUserStoryTest {
    @Test
    public void test() throws URISyntaxException {
        final TraversalBuilder builder = traverson.follow(Hop.rel("appointment").withParameter("sort", "startTime.asc").withParameter("size", 100));

        final Resources<Resource<Appointment>> resources = builder.toObject(refAppointmentList);

        final Collection<Resource<Appointment>> appointments = resources.getContent();
        assertThat(appointments.size(), equalTo(50));

        for (final Resource<Appointment> appointment : appointments) {
            final Link link = appointment.getLink("customer");
            final Resource<Customer> customer = new Traverson(new URI(link.getHref()), MediaTypes.HAL_JSON).follow().toObject(refCustomer);
            assertThat(customer.getContent(), notNullValue());
            assertThat(appointment.getContent(), notNullValue());
        }
    }
}
