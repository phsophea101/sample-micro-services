package com.example.gateway.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AuditDto {
    protected String createdBy;
    protected Date createdDate = new Date();
    protected String updatedBy;
    protected Date updatedDate;
}
