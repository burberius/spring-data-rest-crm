package net.troja.application;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URISyntaxException;

import net.troja.application.model.Appointment;

import org.junit.Test;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;

/**
 * 4. User Story: As an audiologist I want to see an overview of the next week’s
 * appointments
 */
public class UserStory4Test extends AbstractUserStoryTest {
    @Test
    public void test() throws URISyntaxException {
        final Resources<Resource<Appointment>> appointments = traverson.follow("appointment", "search", "findNextWeek").toObject(refAppointmentList);

        assertThat(appointments.getContent().size(), equalTo(appointmentRepo.findNextWeek().size()));
    }
}
