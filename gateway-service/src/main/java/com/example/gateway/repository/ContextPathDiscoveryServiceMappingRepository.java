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
        List<ContextPathDiscoveryServiceMappingEntity> entities = this.contextPathDiscoveryServiceMappingEntities().stream().filter(item -> item.getDiscoveryServiceStatus().getDiscoveryServiceStatusType().equalsIgnoreCase(discoveryServiceStatusEntity.getDiscoveryServiceStatusType())).collect(Collectors.toList());
        return entities;
    }

    public ContextPathDiscoveryServiceMappingEntity findById(String id) {
        List<ContextPathDiscoveryServiceMappingEntity> entities = this.contextPathDiscoveryServiceMappingEntities();
        List<ContextPathDiscoveryServiceMappingEntity> entity = entities.stream().filter(item -> item.getId().equalsIgnoreCase(id)).collect(Collectors.toList());
        if (entity.size() > 0)
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
        List<ContextPathDiscoveryServiceMappingEntity> entities = this.contextPathDiscoveryServiceMappingEntities();
        List<ContextPathDiscoveryServiceMappingEntity> entity = entities.stream().filter(item -> item.getId().equalsIgnoreCase(id)).collect(Collectors.toList());
        if (entity.size() > 0)
            this.contextPathDiscoveryServiceMappingEntities().remove(entity.get(0));
    }

}
