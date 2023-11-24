package com.example.gateway.routebuilder;

import com.example.gateway.common.util.ContextUtil;
import com.example.gateway.dto.ContextPathDiscoveryServiceMappingDto;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.http.common.HttpMethods;

public class DiscoveryServiceRouteBuilder extends RouteBuilder {

    private ContextPathDiscoveryServiceMappingDto dto;

    public DiscoveryServiceRouteBuilder(ContextPathDiscoveryServiceMappingDto dto) {
        this.dto = dto;
    }

    @Override
    public void configure() {
        String contextPath = ContextUtil.getProperty("camel.component.servlet.mapping.context-path").replace("/*", "");
        restConfiguration()
//                .bindingMode(RestBindingMode.json)
                .component("servlet")
                .enableCORS(true);
        // redirect Http.GET requests to appropriate micro-service
        String remove = String.format("%s/%s", contextPath, dto.getContextPath());
        from(String.format("rest://get:%s/", dto.getContextPath()))
//                .route()
                .routeId("get_" + dto.getContextPath())
                .setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.GET))
                .setHeader("in_uri", simple("${header[CamelHttpUri].replace('" + remove + "', '')}"))
                .removeHeader("CamelHttp*")
                .removeHeader(Exchange.HTTP_PATH)
                .removeHeader(Exchange.HTTP_URI)
                .log(LoggingLevel.INFO, "com.example.gateway.routebuilder.DiscoveryServiceRouteBuilder", String.format("Service path [%s] for service name [%s] called to URI [%s] ", dto.getContextPath(), dto.getDiscoveryServiceName(), dto.getDiscoveryServiceName() + "${header[in_uri]}"));
//                .serviceCall(dto.getDiscoveryServiceName() + "${header[in_uri]}");

        // redirect Http.POST requests to appropriate micro-service

        from(String.format("rest://post:%s/", dto.getContextPath()))
//                .route()
                .routeId("post_" + dto.getContextPath())
                .setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.POST))
                .setHeader("in_uri", simple("${header[CamelHttpUri].replace('" + remove + "', '')}"))
                .removeHeader("CamelHttp*")
                .removeHeader(Exchange.HTTP_PATH)
                .removeHeader(Exchange.HTTP_URI)
                .log(LoggingLevel.INFO, "com.example.gateway.routebuilder.DiscoveryServiceRouteBuilder", String.format("Service path [%s] for service name [%s] called to URI [%s] ", dto.getContextPath(), dto.getDiscoveryServiceName(), dto.getDiscoveryServiceName() + "${header[in_uri]}"));
//                .serviceCall(dto.getDiscoveryServiceName() +  "${header[in_uri]}");

    }

}
