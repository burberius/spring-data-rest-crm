package net.troja.application;

import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

import net.troja.application.model.Appointment;
import net.troja.application.model.Customer;
import net.troja.application.repository.AppointmentRepository;
import net.troja.application.repository.CustomerRepository;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestDataGenerator {
    public final static String TYPE_HEARTEST = "Heartest";
    public final static String TYPE_ADJUSTMENT = "Adjustment";
    public final static String TYPE_MEASURING = "Measuring";

    private final static int[] START_MINUTES = new int[] { 0, 15, 30, 45 };
    private final static int[] DURATION = new int[] { 30, 60, 90, 120 };
    private final static String[] TYPES = new String[] { TYPE_HEARTEST, TYPE_ADJUSTMENT, TYPE_MEASURING };

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private AppointmentRepository appointmentRepo;

    public void fill() {
        addCustomers();
        addAppointments();
    }

    private void addCustomers() {
        customerRepo.save(new Customer("Donald", "Duck", "030235123"));
        customerRepo.save(new Customer("Daisy", "Duck", "0302334653"));
        customerRepo.save(new Customer("Micky", "Mouse", "04482343"));
        customerRepo.save(new Customer("Franz", "Gans", "034214773"));
    }

    private void addAppointments() {
        final Random random = new Random(System.currentTimeMillis());
        final List<Customer> customers = Lists.newArrayList(customerRepo.findAll());

        final Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        final int today = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.SECOND, 0);

        // 25 in the past
        for (int count = 0; count < 25; count++) {
            calendar.set(Calendar.DAY_OF_YEAR, today - random.nextInt(14));
            calendar.set(Calendar.HOUR_OF_DAY, 9 + random.nextInt(8));
            calendar.set(Calendar.MINUTE, START_MINUTES[random.nextInt(4)]);
            final Appointment appointment = new Appointment(customers.get(random.nextInt(4)), TYPES[random.nextInt(3)], calendar.getTime(), DURATION[random.nextInt(4)],
                    random.nextInt(10) + 1);
            appointmentRepo.save(appointment);
        }
        // 25 in the future
        for (int count = 0; count < 25; count++) {
            calendar.set(Calendar.DAY_OF_YEAR, today + random.nextInt(14));
            calendar.set(Calendar.HOUR_OF_DAY, 9 + random.nextInt(8));
            calendar.set(Calendar.MINUTE, START_MINUTES[random.nextInt(4)]);
            final Appointment appointment = new Appointment(customers.get(random.nextInt(4)), TYPES[random.nextInt(3)], calendar.getTime(), DURATION[random.nextInt(4)],
                    random.nextInt(10) + 1);
            appointmentRepo.save(appointment);
        }
    }
}
