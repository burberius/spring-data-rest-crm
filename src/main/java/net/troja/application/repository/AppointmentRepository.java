package net.troja.application.repository;

import java.util.List;

import net.troja.application.model.Appointment;
import net.troja.application.model.Customer;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "appointment", path = "appointment")
public interface AppointmentRepository extends PagingAndSortingRepository<Appointment, Long> {
    @Query("SELECT a FROM Appointment a WHERE a.startTime BETWEEN DATEADD('DAY', 9-DAY_OF_WEEK(CURRENT_DATE()), CURRENT_DATE()) AND DATEADD('DAY', 16-DAY_OF_WEEK(CURRENT_DATE()), CURRENT_DATE()) ")
    List<Appointment> findNextWeek();

    @Query("SELECT a FROM Appointment a WHERE a.customer = :customer AND a.startTime > NOW() ORDER BY a.startTime")
    List<Appointment> findByCustomerNext(@Param("customer") Customer customer, Pageable pageable);

    @Query("SELECT a FROM Appointment a WHERE a.customer = :customer AND a.startTime < NOW() ORDER BY a.startTime DESC")
    List<Appointment> findByCustomerOld(@Param("customer") Customer customer, Pageable pageable);
}
