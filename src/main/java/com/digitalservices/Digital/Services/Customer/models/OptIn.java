package com.digitalservices.Digital.Services.Customer.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "opt_in", schema = "digital_services_base")
public class OptIn {
    private Long id;
    private Integer age;
    private Boolean hasBeenApproved;
    private Timestamp dateCreated;
    private Timestamp dateUpdated;
    private Amounts amountsOnSms;
    private Amounts amountsOnData;
    private Amounts amountsOnVoice;
    private Devices deviceType;
    private Bundles soughtBundle;
    private Services serviceOffered;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "has_been_approved")
    public Boolean getHasBeenApproved() {
        return hasBeenApproved;
    }

    public void setHasBeenApproved(Boolean hasBeenApproved) {
        this.hasBeenApproved = hasBeenApproved;
    }

    @Basic
    @CreationTimestamp
    @Column(name = "date_created")
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Basic
    @UpdateTimestamp
    @Column(name = "date_updated")
    public Timestamp getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Timestamp dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OptIn that = (OptIn) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(age, that.age)) return false;
        if (!Objects.equals(hasBeenApproved, that.hasBeenApproved))
            return false;
        if (!Objects.equals(dateCreated, that.dateCreated)) return false;
        if (!Objects.equals(dateUpdated, that.dateUpdated)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (hasBeenApproved != null ? hasBeenApproved.hashCode() : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        result = 31 * result + (dateUpdated != null ? dateUpdated.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "amount_on_sms", referencedColumnName = "id", nullable = false)
    public Amounts getAmountsOnSms() {
        return amountsOnSms;
    }

    public void setAmountsOnSms(Amounts amountsByAmountOnSms) {
        this.amountsOnSms = amountsByAmountOnSms;
    }

    @ManyToOne
    @JoinColumn(name = "amount_on_data", referencedColumnName = "id", nullable = false)
    public Amounts getAmountsOnData() {
        return amountsOnData;
    }

    public void setAmountsOnData(Amounts amountsByAmountOnData) {
        this.amountsOnData = amountsByAmountOnData;
    }

    @ManyToOne
    @JoinColumn(name = "amount_on_voice", referencedColumnName = "id", nullable = false)
    public Amounts getAmountsOnVoice() {
        return amountsOnVoice;
    }

    public void setAmountsOnVoice(Amounts amountsByAmountOnVoice) {
        this.amountsOnVoice = amountsByAmountOnVoice;
    }

    @ManyToOne
    @JoinColumn(name = "device_type", referencedColumnName = "id", nullable = false)
    public Devices getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Devices devicesByDeviceType) {
        this.deviceType = devicesByDeviceType;
    }

    @ManyToOne
    @JoinColumn(name = "sought_bundle", referencedColumnName = "id", nullable = false)
    public Bundles getSoughtBundle() {
        return soughtBundle;
    }

    public void setSoughtBundle(Bundles bundlesBySoughtBundle) {
        this.soughtBundle = bundlesBySoughtBundle;
    }

    @ManyToOne
    @JoinColumn(name = "service_offered", referencedColumnName = "id", nullable = false)
    public Services getServiceOffered() {
        return serviceOffered;
    }

    public void setServiceOffered(Services servicesByServiceOffered) {
        this.serviceOffered = servicesByServiceOffered;
    }
}
