package com.example.productManagement.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ConfigNameEnum {

    CONNECTION_TYPE("connection_type"),
    DATABASE_HOSTNAME("database_hostname"),
    DATABASE_PORT("database_port"),
    DATABASE_SCHEMA("database_schema"),
    DATABASE_USER("database_user"),
    DATABASE_PASSWORD("database_password"),
    EMAIL_HOST("email_host"),
    EMAIL_PORT("email_port"),
    EMAIL_ADDRESS("email_address"),
    EMAIL_PROTOCOL("email_protocol"),
    EMAIL_PASSWORD("email_password"),
    MAX_SIZE("MAX_SIZE"),
    EXTENSIONS("EXTENSIONS");


    private final String configName;
}
