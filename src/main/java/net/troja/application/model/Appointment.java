package net.troja.application.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private Customer customer;
    private String type;
    private Date startTime;
    private int duration;
    private int rating;

    public Appointment() {
    }

    public Appointment(final Customer customer, final String type, final Date startTime, final int duration) {
        super();
        this.customer = customer;
        this.type = type;
        this.startTime = startTime;
        this.duration = duration;
    }

    public Appointment(final Customer customer, final String type, final Date startTime, final int duration, final int rating) {
        super();
        this.customer = customer;
        this.type = type;
        this.startTime = startTime;
        this.duration = duration;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(final Date startTime) {
        this.startTime = startTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(final int duration) {
        this.duration = duration;
    }

    /**
     * Get the rating of this appointment
     * 
     * @return 1 = horrible, 10 = perfect
     */
    public int getRating() {
        return rating;
    }

    /**
     * Set the rating for this appointment
     * 
     * @param rating
     *            1 = horrible, 10 = perfect
     */
    public void setRating(final int rating) {
        this.rating = rating;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((customer == null) ? 0 : customer.hashCode());
        result = (prime * result) + duration;
        result = (prime * result) + (int) (id ^ (id >>> 32));
        result = (prime * result) + rating;
        result = (prime * result) + ((startTime == null) ? 0 : startTime.hashCode());
        result = (prime * result) + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Appointment other = (Appointment) obj;
        if (customer == null) {
            if (other.customer != null) {
                return false;
            }
        } else if (!customer.equals(other.customer)) {
            return false;
        }
        if (duration != other.duration) {
            return false;
        }
        if (id != other.id) {
            return false;
        }
        if (rating != other.rating) {
            return false;
        }
        if (startTime == null) {
            if (other.startTime != null) {
                return false;
            }
        } else if (startTime.getTime() != other.startTime.getTime()) {
            return false;
        }
        if (type == null) {
            if (other.type != null) {
                return false;
            }
        } else if (!type.equals(other.type)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Appointment [id=" + id + ", customer=" + customer + ", type=" + type + ", startTime=" + startTime + ", duration=" + duration + ", rating=" + rating + "]";
    }
}
