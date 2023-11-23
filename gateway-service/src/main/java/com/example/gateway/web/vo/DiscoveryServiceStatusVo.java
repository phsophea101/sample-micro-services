package com.example.gateway.web.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class DiscoveryServiceStatusVo {

    private String id;

    private String discoveryServiceStatusType;

    private String description;

    private List<ContextPathDiscoveryServiceMappingVo> contextPathDiscoveryServices;


}
