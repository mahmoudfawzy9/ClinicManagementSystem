package com.stc.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.clinigment.rest.api.model.adapters.LocalDateAdapter;
import org.clinigment.rest.api.model.adapters.LocalDateTimeAdapter;
import org.clinigment.rest.api.model.converters.LocalDateAttributeConverter;
import org.clinigment.rest.api.model.converters.LocalDateTimeAttributeConverter;

/**
 *
 * @author mahmoud
 */

@Entity
@Table(name = "appointment")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Appointment.findByPatientId",
                query = "SELECT a FROM Appointment a WHERE a.patientId = :patientId"),
        @NamedQuery(name = "Appointment.findByPatientIdOnDate",
                query = "SELECT a FROM Appointment a WHERE a.doctorId = :patientId AND a.date = :date")
})
@Data
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "APPOINTMENT_ID", updatable = false, nullable = false)
    @XmlID
    private Long id;

    @Column(name = "DATE", nullable = false)
    @Convert(converter = LocalDateAttributeConverter.class)
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate date;

    @Column(name = "START_TIME", nullable = false)
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime startTime;

    @Column(name = "END_TIME", nullable = false)
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime endTime;

    @Column(name = "appPATIENT_ID", updatable = false, insertable = true)
    private Long patientId;

    @Column(name = "PATIENT_NAME")
    private String patientName;

    @Column(name = "CONTACT_NUMBER")
    private String contactNumber;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "appEMPLOYEE_ID")
    private Long doctorId;

    @Column(name = "date_created")
    @CreationTimestamp
    private Date dateCreated;

    @Column(name = "last_updated")
    @UpdateTimestamp
    private Date lastUpdated;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Appointment{" + "id=" + id + ", date=" + date + ", startTime=" + startTime + ", endTime=" + endTime + ", patientId=" + patientId + ", patientName=" + patientName + ", contactNumber=" + contactNumber + ", description=" + description + ", doctorId=" + doctorId + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }

    public void update(Appointment newAppointment) {
        this.contactNumber = newAppointment.getContactNumber();
        this.createdAt = newAppointment.getCreatedAt();
        this.date = newAppointment.getDate();
        this.description = newAppointment.getDescription();
        this.doctorId = newAppointment.getDoctorId();
        this.endTime = newAppointment.getEndTime();
        this.patientId = newAppointment.getPatientId();
        this.patientName = newAppointment.getPatientName();
        this.startTime = newAppointment.getStartTime();
        this.updatedAt = newAppointment.getUpdatedAt();
    }


}