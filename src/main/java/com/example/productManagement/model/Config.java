package com.example.productManagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "configs")
@Entity
public class Config extends UserDateAudit {

    private String configGroup;

    private String configName;

    private String configValue;


}
