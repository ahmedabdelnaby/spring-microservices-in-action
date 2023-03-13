package com.optimagrowth.license.controller;

import com.optimagrowth.license.model.License;
import com.optimagrowth.license.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/v1/organization/{organizationId}/license")
public class LicenseController {

    @Autowired
    private LicenseService licenseService;

    @Autowired
    MessageSource messages;

    @PostMapping
    public String createLicense(@PathVariable("organizationId") String organizationId,
                                @RequestBody License licenseRequest,
                                @RequestHeader(value = "Accept-Language", required = false) Locale locale) { // receive the locale from the header attribute, if not provided, we'll use default locale

        String responseMessage = null;
        if (licenseRequest != null) {
            licenseRequest.setOrganizationId(organizationId);
            responseMessage = String.format(messages.getMessage("license.create.message", null, locale), licenseRequest.toString());
        }

        return responseMessage;
    }

    @GetMapping("/{licenseId}")
    public ResponseEntity<License> getLicense(@PathVariable("organizationId") String organizationId,
                                              @PathVariable("licenseId") String licenseId) {

        License license = licenseService.getLicense(licenseId, organizationId);

        return ResponseEntity.ok(license);
    }

    @PutMapping
    public String updateLicense(@PathVariable("organizationId") String organizationId,
                                @RequestBody License licenseRequest) {

        String responseMessage = null;
        if (licenseRequest != null) {
            licenseRequest.setOrganizationId(organizationId);
            responseMessage = String.format(messages.getMessage("license.update.message", null, null), licenseRequest.toString());
        }

        return responseMessage;
    }

    @DeleteMapping("/{licenseId}")
    public ResponseEntity<String> deleteLicense(@PathVariable("organizationId") String organizationId,
                                                @PathVariable("licenseId") String licenseId) {

        return ResponseEntity.ok(licenseService.deleteLicense(licenseId, organizationId));
    }
}
