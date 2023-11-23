package com.example.gateway.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@ToString
public class SubSystemCategoryEntity {

    public static final String TABLE_NAME = "SUB_SYSTEM_CATEGORY";
    private String id = UUID.randomUUID().toString();
    private String subSystemCategoryType;
    private String description;
    private List<ContextPathDiscoveryServiceMappingEntity> contextPathDiscoveryServiceMappings;

}
