package com.example.gateway.dto;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ContextPathDiscoveryServiceMappingDto extends AuditDto implements Serializable {
    private String id;
    private String contextPath;
    private String discoveryServiceName;
    private DiscoveryServiceStatusDto discoveryServiceStatus;
    private SubSystemCategoryDto subSystemCategory;
}
