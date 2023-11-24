package com.example.gateway.service;

import com.example.gateway.dto.DiscoveryServiceStatusDto;
import com.example.gateway.enums.DiscoveryServiceStatusType;

import java.util.List;

public interface DiscoveryServiceStatusService {

    DiscoveryServiceStatusDto getDiscoveryServiceStatusByDiscoveryServiceStatusType(DiscoveryServiceStatusType discoveryServiceStatusType);

    List<DiscoveryServiceStatusDto> getDiscoveryServiceStatusEntities();

}
