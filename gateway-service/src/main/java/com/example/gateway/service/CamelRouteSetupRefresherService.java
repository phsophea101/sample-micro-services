package com.example.gateway.service;

import com.example.gateway.dto.ContextPathDiscoveryServiceMappingDto;
import org.apache.camel.CamelContext;

public interface CamelRouteSetupRefresherService {

    void setup(CamelContext camelContext) throws Exception;

    void refresh() throws Exception;

    void addService(ContextPathDiscoveryServiceMappingDto contextPathDiscoveryServiceMappingDto) throws Exception;

    void updateService(ContextPathDiscoveryServiceMappingDto contextPathDiscoveryServiceMappingDto) throws Exception;

    void deleteService(ContextPathDiscoveryServiceMappingDto contextPathDiscoveryServiceMappingDto) throws Exception;
}
