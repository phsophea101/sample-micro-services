package com.example.gateway.repository;

import com.example.gateway.entity.SubSystemCategoryEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubSystemCategoryRepository {
    @Bean
    public List<SubSystemCategoryEntity> subSystemCategoryEntities() {
        return new ArrayList<>();
    }

    public SubSystemCategoryEntity findBySubSystemCategoryType(String subSystemCategoryType) {
        List<SubSystemCategoryEntity> entity = this.subSystemCategoryEntities().stream().filter(item -> item.getSubSystemCategoryType().equalsIgnoreCase(subSystemCategoryType)).collect(Collectors.toList());
        if (entity.size() > 0)
            return entity.get(0);
        return null;
    }

    public List<SubSystemCategoryEntity> findAll() {
        return this.subSystemCategoryEntities();
    }
}
