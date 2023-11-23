package com.example.gateway.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;

@Setter
@Getter
@ToString
public class ContextPathDiscoveryServiceMappingEntity extends AuditEntity implements Serializable {
    public static final String TABLE_NAME = "CPES_MAPPING";
    private String id = UUID.randomUUID().toString();
    private String contextPath;
    private String discoveryServiceName;
    private DiscoveryServiceStatusEntity discoveryServiceStatus;
    private SubSystemCategoryEntity subSystemCategory;
}
