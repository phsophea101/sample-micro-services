package com.example.gateway.service.impl;

import com.example.gateway.common.ContextUtil;
import com.example.gateway.dto.ContextPathDiscoveryServiceMappingDto;
import com.example.gateway.enums.DiscoveryServiceStatusType;
import com.example.gateway.routebuilder.DiscoveryServiceRouteBuilder;
import com.example.gateway.service.CamelRouteSetupRefresherService;
import com.example.gateway.service.ContextPathDiscoveryServiceMappingService;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.Route;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CamelRouteSetupRefresherServiceImpl implements CamelRouteSetupRefresherService {

    private ContextPathDiscoveryServiceMappingService service;
    private CamelContext camelContext;

    @Autowired
    public CamelRouteSetupRefresherServiceImpl(ContextPathDiscoveryServiceMappingService service, CamelContext camelContext) {
        this.service = service;
        this.camelContext = camelContext;
    }

    @Override
    public void setup(CamelContext camelContext) throws Exception {
        this.camelContext = camelContext;
        List<ContextPathDiscoveryServiceMappingDto> contextServiceMappingEntities = this.service.getAllContextPathDiscoveryServiceMappings();
        // for each single microservice which expose a rest API
        for (ContextPathDiscoveryServiceMappingDto contextPathDiscoveryServiceMappingDto : contextServiceMappingEntities) {
            camelContext.addRoutes(new DiscoveryServiceRouteBuilder(contextPathDiscoveryServiceMappingDto));
        }
    }

    @Override
    public void refresh() throws Exception {
        String contextPath = ContextUtil.getProperty("camel.component.servlet.mapping.context-path").replace("/*", "");
        if (contextPath.startsWith("/"))
            contextPath = contextPath.replaceFirst("/", "");
//        // removing the routes
        List<Route> routes = this.camelContext.getRoutes();
        for (Route route : routes) {
            // if the route belong to one of services
            if (route.getEndpoint().getEndpointUri().contains(String.format(":%s/", contextPath))) {
                //camelContext.setShutdownStrategy(new DefaultShutdownStrategy());
                this.camelContext.getRouteController().stopRoute(route.getId());
            }
        }
        // adding the routes
        this.setup(this.camelContext);
    }

    @Override
    public void addService(ContextPathDiscoveryServiceMappingDto contextPathDiscoveryServiceMappingDto) throws Exception {
        if (contextPathDiscoveryServiceMappingDto.getDiscoveryServiceStatus().getDiscoveryServiceStatusType().equalsIgnoreCase(String.valueOf(DiscoveryServiceStatusType.PUBLISHED)))
            this.camelContext.addRoutes(new DiscoveryServiceRouteBuilder(contextPathDiscoveryServiceMappingDto));
        else
            System.out.println("");
    }

    @Override
    public void updateService(ContextPathDiscoveryServiceMappingDto contextPathDiscoveryServiceMappingDto) throws Exception {
        Route route = this.camelContext.getRoute("get_" + contextPathDiscoveryServiceMappingDto.getContextPath());
        if (ObjectUtils.isEmpty(route)) //does not exist
            addService(contextPathDiscoveryServiceMappingDto);
        else {
            if (contextPathDiscoveryServiceMappingDto.getDiscoveryServiceStatus().getDiscoveryServiceStatusType().equalsIgnoreCase(String.valueOf(DiscoveryServiceStatusType.PUBLISHED)))
                startRoutes(contextPathDiscoveryServiceMappingDto);
            else
                stopRoutes(contextPathDiscoveryServiceMappingDto);
        }

    }

    @Override
    public void deleteService(ContextPathDiscoveryServiceMappingDto contextPathDiscoveryServiceMappingDto) throws Exception {
        Route route = this.camelContext.getRoute("get_" + contextPathDiscoveryServiceMappingDto.getContextPath());
        if (ObjectUtils.isNotEmpty(route)) //does not exist
            stopRoutes(contextPathDiscoveryServiceMappingDto);
    }

    private void stopRoutes(ContextPathDiscoveryServiceMappingDto contextPathDiscoveryServiceMappingDto) throws Exception {
        this.camelContext.getRouteController().stopRoute("get_" + contextPathDiscoveryServiceMappingDto.getContextPath());
        this.camelContext.getRouteController().stopRoute("post_" + contextPathDiscoveryServiceMappingDto.getContextPath());
    }

    private void startRoutes(ContextPathDiscoveryServiceMappingDto contextPathDiscoveryServiceMappingDto) throws Exception {
        this.camelContext.getRouteController().startRoute("get_" + contextPathDiscoveryServiceMappingDto.getContextPath());
        this.camelContext.getRouteController().startRoute("post_" + contextPathDiscoveryServiceMappingDto.getContextPath());
    }
}
