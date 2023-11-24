package com.example.gateway.repository;

import com.example.gateway.entity.ContextPathDiscoveryServiceMappingEntity;
import com.example.gateway.entity.DiscoveryServiceStatusEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class ContextPathDiscoveryServiceMappingRepository {
    @Bean
    public List<ContextPathDiscoveryServiceMappingEntity> contextPathDiscoveryServiceMappingEntities() {
        return new ArrayList<>();
    }

    public List<ContextPathDiscoveryServiceMappingEntity> findByDiscoveryServiceStatusEntity(DiscoveryServiceStatusEntity discoveryServiceStatusEntity) {
        return this.contextPathDiscoveryServiceMappingEntities().stream().filter(item -> item.getDiscoveryServiceStatus().getDiscoveryServiceStatusType().equalsIgnoreCase(discoveryServiceStatusEntity.getDiscoveryServiceStatusType())).collect(Collectors.toList());
    }

    public ContextPathDiscoveryServiceMappingEntity findById(String id) {
        List<ContextPathDiscoveryServiceMappingEntity> entity = this.contextPathDiscoveryServiceMappingEntities().stream().filter(item -> item.getId().equalsIgnoreCase(id)).collect(Collectors.toList());
        if (!entity.isEmpty())
            return entity.get(0);
        return null;
    }

    public List<ContextPathDiscoveryServiceMappingEntity> findAll() {
        return this.contextPathDiscoveryServiceMappingEntities();
    }

    public void save(ContextPathDiscoveryServiceMappingEntity entity) {
        this.contextPathDiscoveryServiceMappingEntities().add(entity);
    }


    public void deleteById(String id) {
        List<ContextPathDiscoveryServiceMappingEntity> entity = this.contextPathDiscoveryServiceMappingEntities().stream().filter(item -> item.getId().equalsIgnoreCase(id)).collect(Collectors.toList());
        if (!entity.isEmpty())
            this.contextPathDiscoveryServiceMappingEntities().remove(entity.get(0));
    }

}
