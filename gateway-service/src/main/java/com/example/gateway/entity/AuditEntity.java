package com.example.gateway.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AuditEntity {
    protected String createdBy;
    protected Date createdDate = new Date();
    protected String updatedBy;
    protected Date updatedDate;
}
