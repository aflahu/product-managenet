package com.example.productManagement.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ConfigGroupEnum {

    EMAIL_CONFIGURATION("EMAIL_CONFIG");

    private final String configGroup;
}
