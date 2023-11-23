package com.example.gateway.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class SubSystemCategoryDto {
    private String id;
    private String subSystemCategoryType;
    private String description;
    private List<ContextPathDiscoveryServiceMappingDto> contextPathDiscoveryServiceMappings;

}
