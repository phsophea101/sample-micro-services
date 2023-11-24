package com.example.gateway.repository;

import com.example.gateway.entity.DiscoveryServiceStatusEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscoveryServiceStatusRepository {
    @Bean
    public List<DiscoveryServiceStatusEntity> discoveryServiceStatusEntities() {
        return new ArrayList<>();
    }

    public DiscoveryServiceStatusEntity findByDiscoveryServiceStatusType(String discoveryServiceStatusType) {
        List<DiscoveryServiceStatusEntity> entity = this.discoveryServiceStatusEntities().stream().filter(item -> item.getDiscoveryServiceStatusType().equalsIgnoreCase(discoveryServiceStatusType)).collect(Collectors.toList());
        if (!entity.isEmpty())
            return entity.get(0);
        return null;
    }


    public List<DiscoveryServiceStatusEntity> findAll() {
        return this.discoveryServiceStatusEntities();
    }

}
