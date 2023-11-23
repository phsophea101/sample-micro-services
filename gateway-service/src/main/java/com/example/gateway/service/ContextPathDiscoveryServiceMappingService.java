package com.example.gateway.service;

import com.example.gateway.common.BizException;
import com.example.gateway.dto.ContextPathDiscoveryServiceMappingDto;
import com.example.gateway.enums.DiscoveryServiceStatusType;

import java.util.List;

public interface ContextPathDiscoveryServiceMappingService {

    boolean existObject(ContextPathDiscoveryServiceMappingDto contextPathDiscoveryServiceMappingDto);

    ContextPathDiscoveryServiceMappingDto getContextPathDiscoveryServiceMappingDto(String id) throws BizException;

    List<ContextPathDiscoveryServiceMappingDto> getAllContextPathDiscoveryServiceMappings();

    List<ContextPathDiscoveryServiceMappingDto> getAllContextPathDiscoveryServiceMappingEntitiesByDiscoveryServiceStatusType(DiscoveryServiceStatusType discoveryServiceStatusType);

    ContextPathDiscoveryServiceMappingDto addContextPathDiscoveryServiceMappingDto(ContextPathDiscoveryServiceMappingDto contextPathDiscoveryServiceMappingDto);

    ContextPathDiscoveryServiceMappingDto updateContextPathDiscoveryServiceMappingDto(ContextPathDiscoveryServiceMappingDto contextPathDiscoveryServiceMappingDto);

    void deleteContextPathDiscoveryServiceMappingDto(String id);

}
