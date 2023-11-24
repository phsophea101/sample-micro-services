package com.example.gateway.service.impl;

import com.example.gateway.common.exception.BizException;
import com.example.gateway.dto.ContextPathDiscoveryServiceMappingDto;
import com.example.gateway.dto.DiscoveryServiceStatusDto;
import com.example.gateway.entity.ContextPathDiscoveryServiceMappingEntity;
import com.example.gateway.entity.DiscoveryServiceStatusEntity;
import com.example.gateway.enums.BizErrorCode;
import com.example.gateway.enums.DiscoveryServiceStatusType;
import com.example.gateway.mapper.CamelMapperUtil;
import com.example.gateway.repository.ContextPathDiscoveryServiceMappingRepository;
import com.example.gateway.service.ContextPathDiscoveryServiceMappingService;
import com.example.gateway.service.DiscoveryServiceStatusService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContextPathDiscoveryServiceMappingServiceImpl implements ContextPathDiscoveryServiceMappingService {

    private ContextPathDiscoveryServiceMappingRepository repository;
    private DiscoveryServiceStatusService service;

    @Autowired
    public ContextPathDiscoveryServiceMappingServiceImpl(ContextPathDiscoveryServiceMappingRepository repository, DiscoveryServiceStatusService service) {
        this.repository = repository;
        this.service = service;
    }

    @Override
    public boolean existObject(ContextPathDiscoveryServiceMappingDto dto) {
        List<ContextPathDiscoveryServiceMappingDto> list = this.getAllContextPathDiscoveryServiceMappings();
        List<ContextPathDiscoveryServiceMappingDto> collect = list.stream().filter(item -> item.getContextPath().equalsIgnoreCase(dto.getContextPath())).collect(Collectors.toList());
        return !collect.isEmpty();
    }

    @Override
    public ContextPathDiscoveryServiceMappingDto getContextPathDiscoveryServiceMappingDto(String id) throws BizException {
        ContextPathDiscoveryServiceMappingEntity entity = this.repository.findById(id);
        if (ObjectUtils.isEmpty(entity))
            throw new BizException(BizErrorCode.E0002);
        return CamelMapperUtil.INSTANCE.contextPathDiscoveryServiceEntityToDto(entity);
    }

    @Override
    public List<ContextPathDiscoveryServiceMappingDto> getAllContextPathDiscoveryServiceMappings() {
        List<ContextPathDiscoveryServiceMappingEntity> list = this.repository.findAll();
        if (ObjectUtils.isEmpty(list))
            return new ArrayList<>();
        return CamelMapperUtil.INSTANCE.contextPathDiscoveryServiceListEntityToDto(list);
    }

    @Override
    public List<ContextPathDiscoveryServiceMappingDto> getAllContextPathDiscoveryServiceMappingEntitiesByDiscoveryServiceStatusType(DiscoveryServiceStatusType discoveryServiceStatusType) {
        DiscoveryServiceStatusDto discoveryServiceStatus = this.service.getDiscoveryServiceStatusByDiscoveryServiceStatusType(discoveryServiceStatusType);
        DiscoveryServiceStatusEntity discoveryServiceStatusEntity = CamelMapperUtil.INSTANCE.discoveryServiceStatusDtoToEntity(discoveryServiceStatus);
        List<ContextPathDiscoveryServiceMappingEntity> list = this.repository.findByDiscoveryServiceStatusEntity(discoveryServiceStatusEntity);
        return CamelMapperUtil.INSTANCE.contextPathDiscoveryServiceListEntityToDto(list);
    }

    @Transactional
    @Override
    public ContextPathDiscoveryServiceMappingDto addContextPathDiscoveryServiceMappingDto(ContextPathDiscoveryServiceMappingDto contextPathDiscoveryServiceMappingDto) {
        this.repository.save(CamelMapperUtil.INSTANCE.contextPathDiscoveryServiceDtoToEntity(contextPathDiscoveryServiceMappingDto));
        return contextPathDiscoveryServiceMappingDto;
    }

    @Transactional
    @Override
    public ContextPathDiscoveryServiceMappingDto updateContextPathDiscoveryServiceMappingDto(ContextPathDiscoveryServiceMappingDto contextPathDiscoveryServiceMappingDto) {
        this.repository.save(CamelMapperUtil.INSTANCE.contextPathDiscoveryServiceDtoToEntity(contextPathDiscoveryServiceMappingDto));
        return contextPathDiscoveryServiceMappingDto;
    }

    @Transactional
    @Override
    public void deleteContextPathDiscoveryServiceMappingDto(String id) {
        this.repository.deleteById(id);
    }
}
