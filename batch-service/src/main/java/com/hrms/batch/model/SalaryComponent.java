package com.hrms.batch.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hrms.batch.model.ComponentType;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SalaryComponent {
    private Long id;
    private String name;
    private ComponentType type;
    private Double amount;
}