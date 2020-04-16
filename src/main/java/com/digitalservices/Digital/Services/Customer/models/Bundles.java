package com.digitalservices.Digital.Services.Customer.models;

import javax.persistence.*;

@Entity
@Table(name = "bundles", schema = "digital_services_base")
public class Bundles {
    private Long id;
    private String bundleTypes;

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
    @Column(name = "bundle_types")
    public String getBundleTypes() {
        return bundleTypes;
    }

    public void setBundleTypes(String bundleTypes) {
        this.bundleTypes = bundleTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bundles that = (Bundles) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (bundleTypes != null ? !bundleTypes.equals(that.bundleTypes) : that.bundleTypes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (bundleTypes != null ? bundleTypes.hashCode() : 0);
        return result;
    }
}
