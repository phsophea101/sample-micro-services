package com.example.gateway.web.controller;

import com.example.gateway.common.BizException;
import com.example.gateway.common.vo.ResponseVO;
import com.example.gateway.common.vo.ResponseVOBuilder;
import com.example.gateway.dto.ContextPathDiscoveryServiceMappingDto;
import com.example.gateway.enums.BizErrorCode;
import com.example.gateway.mapper.CamelMapperUtil;
import com.example.gateway.service.CamelRouteSetupRefresherService;
import com.example.gateway.service.ContextPathDiscoveryServiceMappingService;
import com.example.gateway.service.DiscoveryServiceStatusService;
import com.example.gateway.service.SubSystemCategoryService;
import com.example.gateway.web.vo.ContextPathDiscoveryServiceMappingVo;
import com.example.gateway.web.vo.DiscoveryServiceStatusVo;
import com.example.gateway.web.vo.SubSystemCategoryVo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service-gateway")
public class ServiceGatewayController {

    private ContextPathDiscoveryServiceMappingService contextPathDiscoveryServiceMappingService;
    private DiscoveryServiceStatusService discoveryServiceStatusService;
    private SubSystemCategoryService subSystemCategoryService;
    private CamelRouteSetupRefresherService camelRouteSetupRefresherService;

    @Autowired
    public ServiceGatewayController(ContextPathDiscoveryServiceMappingService contextPathDiscoveryServiceMappingService, DiscoveryServiceStatusService discoveryServiceStatusService, SubSystemCategoryService subSystemCategoryService, CamelRouteSetupRefresherService camelRouteSetupRefresherService) {
        this.contextPathDiscoveryServiceMappingService = contextPathDiscoveryServiceMappingService;
        this.discoveryServiceStatusService = discoveryServiceStatusService;
        this.subSystemCategoryService = subSystemCategoryService;
        this.camelRouteSetupRefresherService = camelRouteSetupRefresherService;
    }

    @GetMapping("/health")
    public ResponseVO<String> healthCheck() {
        return new ResponseVOBuilder<String>().success().data("Server is UP").build();
    }

    @GetMapping(value = "/services", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<List<ContextPathDiscoveryServiceMappingVo>> getAllServices() {
        List<ContextPathDiscoveryServiceMappingVo> body = CamelMapperUtil.INSTANCE.contextPathDiscoveryServiceListDtoToVo(contextPathDiscoveryServiceMappingService.getAllContextPathDiscoveryServiceMappings());
        return new ResponseVOBuilder<List<ContextPathDiscoveryServiceMappingVo>>().success().data(body).build();
    }

    @GetMapping(value = "/services/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @SneakyThrows
    public ResponseVO<ContextPathDiscoveryServiceMappingVo> getService(@PathVariable("id") String id) {
        ContextPathDiscoveryServiceMappingVo body = CamelMapperUtil.INSTANCE.contextPathDiscoveryServiceDtoToVo(contextPathDiscoveryServiceMappingService.getContextPathDiscoveryServiceMappingDto(id));
        return new ResponseVOBuilder<ContextPathDiscoveryServiceMappingVo>().success().data(body).build();
    }

    @PostMapping(value = "/services", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @SneakyThrows
    public ResponseVO<ContextPathDiscoveryServiceMappingVo> addService(@RequestBody ContextPathDiscoveryServiceMappingVo contextPathDiscoveryServiceMappingEntity) {
        ContextPathDiscoveryServiceMappingDto contextPathDiscoveryServiceMappingDto = CamelMapperUtil.INSTANCE.contextPathDiscoveryServiceVoToDto(contextPathDiscoveryServiceMappingEntity);
        if (contextPathDiscoveryServiceMappingService.existObject(contextPathDiscoveryServiceMappingDto)) {
            throw new BizException(BizErrorCode.E0003);
        }
        ContextPathDiscoveryServiceMappingDto ce = contextPathDiscoveryServiceMappingService.addContextPathDiscoveryServiceMappingDto(contextPathDiscoveryServiceMappingDto);
        camelRouteSetupRefresherService.addService(ce);
        ContextPathDiscoveryServiceMappingVo body = CamelMapperUtil.INSTANCE.contextPathDiscoveryServiceDtoToVo(ce);
        return new ResponseVOBuilder<ContextPathDiscoveryServiceMappingVo>().success().data(body).build();
    }

    @PutMapping(value = "/services", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @SneakyThrows
    public ResponseVO<ContextPathDiscoveryServiceMappingVo> updateService(@RequestBody ContextPathDiscoveryServiceMappingDto contextPathDiscoveryServiceMappingEntity) {
        ContextPathDiscoveryServiceMappingDto ce = contextPathDiscoveryServiceMappingService.addContextPathDiscoveryServiceMappingDto(contextPathDiscoveryServiceMappingEntity);
        camelRouteSetupRefresherService.updateService(ce);
        ContextPathDiscoveryServiceMappingVo body = CamelMapperUtil.INSTANCE.contextPathDiscoveryServiceDtoToVo(ce);
        return new ResponseVOBuilder<ContextPathDiscoveryServiceMappingVo>().success().data(body).build();
    }

    @DeleteMapping(value = "/services/{id}")
    @SneakyThrows
    public ResponseVO<Void> deleteService(@PathVariable("id") String id) {
        camelRouteSetupRefresherService.deleteService(contextPathDiscoveryServiceMappingService.getContextPathDiscoveryServiceMappingDto(id));
        contextPathDiscoveryServiceMappingService.deleteContextPathDiscoveryServiceMappingDto(id);
        return new ResponseVOBuilder<Void>().success().build();
    }

    @GetMapping(value = "/statuses", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<List<DiscoveryServiceStatusVo>> getAllStatuses() {
        List<DiscoveryServiceStatusVo> body = CamelMapperUtil.INSTANCE.discoveryServiceStatusListDtoToVo(discoveryServiceStatusService.getDiscoveryServiceStatusEntities());
        return new ResponseVOBuilder<List<DiscoveryServiceStatusVo>>().success().data(body).build();
    }

    @GetMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<List<SubSystemCategoryVo>> getAllCategories() {
        List<SubSystemCategoryVo> body = CamelMapperUtil.INSTANCE.subSystemCategoryListDtoToVo(subSystemCategoryService.getSubSystemCategoryEntities());
        return new ResponseVOBuilder<List<SubSystemCategoryVo>>().success().data(body).build();
    }

}
