package com.stc.Patient;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mahmoud
 */

@Entity
@Table(name = "patient_address")
@XmlRootElement
@Data
public class PatientAddress implements Serializable {

    @Id
    @Column(name = "PATIENT_ID", nullable = false, unique = true)
    @XmlID
    private Long patientId;

    @OneToOne(targetEntity = Patient.class, optional = false, orphanRemoval = true)
    @JoinColumn(name = "PATIENT_ID", referencedColumnName = "PATIENT_ID", unique = true, nullable = false, updatable = false, insertable = false)
    private Patient patient;

    @Column(name = "ADDRESS_LINE1", nullable = false, length = 255)
    private String addressLine1;

    @Column(name = "ADDRESS_LINE2", length = 255)
    private String addressLine2;

    @Column(name = "ADDRESS_LINE3", length = 255)
    private String addressLine3;

    @Column(name = "CITY_TOWN", length = 100, nullable = false)
    private String cityTown;

    @Column(name = "COUNTRY", length = 100)
    private String country;

    public PatientAddress(Patient patient,
                          String addressLine1,
                          String addressLine2,
                          String addressLine3,
                          String cityTown,
                          String country) {
        this.patientId = patient.getId();
        this.patient = patient;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
        this.cityTown = cityTown;
        this.country = country;
    }

    public PatientAddress() {
        //Empty constructor for JPA
    }

    @XmlTransient
    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    @XmlTransient
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patientId = patient.getId();
        this.patient = patient;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getCityTown() {
        return cityTown;
    }

    public void setCityTown(String cityTown) {
        this.cityTown = cityTown;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "PatientAddress{" + "patientId=" + patientId + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", addressLine3=" + addressLine3 + ", cityTown=" + cityTown + ", country=" + country + '}';
    }

    public void update(PatientAddress patientAddress) {
        this.addressLine1 = patientAddress.getAddressLine1();
        this.addressLine2 = patientAddress.getAddressLine2();
        this.addressLine3 = patientAddress.getAddressLine3();
        this.cityTown = patientAddress.getCityTown();
        this.country = patientAddress.getCountry();
    }
}