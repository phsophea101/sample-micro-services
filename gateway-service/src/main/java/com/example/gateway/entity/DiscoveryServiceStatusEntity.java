package com.example.gateway.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@ToString
public class DiscoveryServiceStatusEntity {

    public static final String TABLE_NAME = "DISCOVERY_SERVICE_STATUS";

    private String id = UUID.randomUUID().toString();

    private String discoveryServiceStatusType;

    private String description;

    private List<ContextPathDiscoveryServiceMappingEntity> contextPathDiscoveryServiceMappings;


}
