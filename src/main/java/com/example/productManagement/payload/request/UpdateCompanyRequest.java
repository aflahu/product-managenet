package com.example.productManagement.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@AllArgsConstructor
@RequiredArgsConstructor
public class UpdateCompanyRequest implements Serializable {

    @NotNull
    @JsonProperty("company_name")
    private String companyName;

    @NotNull
    @JsonProperty("company_fye")
    private String companyFye;

    @NotNull
    @JsonProperty("reg_no")
    private String regNo;

    @NotNull
    @JsonProperty("country_code")
    private String countryCode;

    @NotNull
    @JsonProperty("company_package")
    private String companyPackage;

    @JsonProperty("status")
    private Boolean status;

    @JsonProperty("contact_details_full_name")
    private String contactDetailsFullName;

    @JsonProperty("contact_details_company_designation")
    private String contactDetailsCompanyDesignation;

    @NotNull
    @JsonProperty("contact_details_email")
    private String companyDetailsEmail;

    @NotNull
    @JsonProperty("contact_details_phone_number")
    private String companyDetailsPhoneNumber;

}
