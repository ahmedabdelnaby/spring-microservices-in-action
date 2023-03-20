package com.optimagrowth.license.repository;

import com.optimagrowth.license.model.License;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // tells Spring Boot that this is a JPA repository class. (Optional when we extend from a CrudRepository)
public interface LicenseRepository extends CrudRepository<License, String> {

    // Spring Data will generate SQL queries for us based on the attributes we specified in the methods name
    public List<License> findByOrganizationId(String organizationId);
    public List<License> findByOrganizationIdAndLicenseId(String organizationId, String licenseId);
}
