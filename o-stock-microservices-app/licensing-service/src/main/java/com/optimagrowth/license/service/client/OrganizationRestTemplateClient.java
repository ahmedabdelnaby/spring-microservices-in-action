package com.optimagrowth.license.service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.optimagrowth.license.model.Organization;

@Component
public class OrganizationRestTemplateClient {
    @Autowired
    RestTemplate restTemplate;

    public Organization getOrganization(String organizationId) {
        // when using a Load Balancer-backed RestTemplate, builds the target URL with the Eureka service ID
        ResponseEntity<Organization> restExchange =
                                            restTemplate.exchange(
                                                    "http://organization-service/v1/organization/{organizationId}",
                                                        HttpMethod.GET,
                                                    null, Organization.class, organizationId);

        return restExchange.getBody();
    }
}
