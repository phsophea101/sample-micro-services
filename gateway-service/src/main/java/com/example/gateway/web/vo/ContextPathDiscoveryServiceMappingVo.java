package com.example.gateway.web.vo;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ContextPathDiscoveryServiceMappingVo {
    private String id;
    private String contextPath;
    private String discoveryServiceName;
    private DiscoveryServiceStatusVo discoveryServiceStatus;
    private SubSystemCategoryVo subSystemCategory;
}
