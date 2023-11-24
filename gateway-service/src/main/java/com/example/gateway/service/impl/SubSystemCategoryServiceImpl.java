package com.example.gateway.service.impl;

import com.example.gateway.dto.SubSystemCategoryDto;
import com.example.gateway.entity.SubSystemCategoryEntity;
import com.example.gateway.enums.SubSystemCategoryType;
import com.example.gateway.mapper.CamelMapperUtil;
import com.example.gateway.repository.SubSystemCategoryRepository;
import com.example.gateway.service.SubSystemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubSystemCategoryServiceImpl implements SubSystemCategoryService {

    private SubSystemCategoryRepository repository;

    @Autowired
    public SubSystemCategoryServiceImpl(SubSystemCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public SubSystemCategoryDto getSubSystemCategoryBySubSystemCategoryType(SubSystemCategoryType subSystemCategoryType) {
        SubSystemCategoryEntity bySubSystemCategoryType = this.repository.findBySubSystemCategoryType(String.valueOf(subSystemCategoryType));
        return CamelMapperUtil.INSTANCE.subSystemCategoryEntityToDto(bySubSystemCategoryType);
    }

    @Override
    public List<SubSystemCategoryDto> getSubSystemCategoryEntities() {
        List<SubSystemCategoryEntity> list = this.repository.findAll();
        return CamelMapperUtil.INSTANCE.subSystemCategoryListEntityToDto(list);
    }
}
