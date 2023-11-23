package com.example.gateway.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class DiscoveryServiceStatusDto {

    private String id;

    private String discoveryServiceStatusType;

    private String description;

    private List<ContextPathDiscoveryServiceMappingDto> contextPathDiscoveryServiceMappings;


}
