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
public class GetCompanyRequest implements Serializable {

    @NotNull
    @JsonProperty("public_key")
    private String publicKey;

    @NotNull
    @JsonProperty("use_bia_app")
    private boolean useBiaApp;

    @NotNull
    @JsonProperty("use_coolingoff")
    private boolean useCoolingOff;

    @NotNull
    @JsonProperty("validate_signature")
    private boolean validateSignature;

    @NotNull
    @JsonProperty("auth_serv_url")
    private String authServiceUrl;

    @NotNull
    @JsonProperty("submit_auth_serv_url")
    private String submitAuthServiceUrl;
}
