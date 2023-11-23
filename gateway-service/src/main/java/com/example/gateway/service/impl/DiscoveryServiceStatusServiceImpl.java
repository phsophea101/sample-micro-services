package com.example.gateway.service.impl;

import com.example.gateway.dto.DiscoveryServiceStatusDto;
import com.example.gateway.entity.DiscoveryServiceStatusEntity;
import com.example.gateway.enums.DiscoveryServiceStatusType;
import com.example.gateway.mapper.CamelMapperUtil;
import com.example.gateway.repository.DiscoveryServiceStatusRepository;
import com.example.gateway.service.DiscoveryServiceStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscoveryServiceStatusServiceImpl implements DiscoveryServiceStatusService {

    private DiscoveryServiceStatusRepository repository;

    @Autowired
    public DiscoveryServiceStatusServiceImpl(DiscoveryServiceStatusRepository repository) {
        this.repository = repository;
    }

    @Override
    public DiscoveryServiceStatusDto getDiscoveryServiceStatusByDiscoveryServiceStatusType(DiscoveryServiceStatusType discoveryServiceStatusType) {
        DiscoveryServiceStatusEntity byDiscoveryServiceStatusType = this.repository.findByDiscoveryServiceStatusType(String.valueOf(discoveryServiceStatusType));
        return CamelMapperUtil.INSTANCE.discoveryServiceStatusEntityToDto(byDiscoveryServiceStatusType);
    }

    @Override
    public List<DiscoveryServiceStatusDto> getDiscoveryServiceStatusEntities() {
        List<DiscoveryServiceStatusEntity> list = this.repository.findAll();
        return CamelMapperUtil.INSTANCE.discoveryServiceStatusListEntityToDto(list);
    }
}
