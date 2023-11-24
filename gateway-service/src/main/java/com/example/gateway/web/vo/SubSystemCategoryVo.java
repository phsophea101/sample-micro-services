package com.example.gateway.web.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class SubSystemCategoryVo {
    private String id;
    private String subSystemCategoryType;
    private String description;
    private List<ContextPathDiscoveryServiceMappingVo> contextPathDiscoveryServiceMappings;

}
