package com.optimagrowth.license.service.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.optimagrowth.license.model.Organization;

@FeignClient("organization-service")        // identifies the service to Feign
public interface OrganizationFeignClient {
    // defines path and action to the endpoint
    @GetMapping(
                value="/v1/organization/{organizationId}",
                consumes="application/json")
    Organization getOrganization(@PathVariable("organizationId") String organizationId);        // defines the parameters passed into this endpoint
}
