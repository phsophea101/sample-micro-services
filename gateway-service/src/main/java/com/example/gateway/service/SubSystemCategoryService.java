package com.example.gateway.service;

import com.example.gateway.dto.SubSystemCategoryDto;
import com.example.gateway.enums.SubSystemCategoryType;

import java.util.List;

public interface SubSystemCategoryService {

    SubSystemCategoryDto getSubSystemCategoryBySubSystemCategoryType(SubSystemCategoryType subSystemCategoryType);

    List<SubSystemCategoryDto> getSubSystemCategoryEntities();

}
