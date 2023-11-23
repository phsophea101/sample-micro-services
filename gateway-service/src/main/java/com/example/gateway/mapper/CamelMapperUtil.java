package com.example.gateway.mapper;

import com.example.gateway.dto.ContextPathDiscoveryServiceMappingDto;
import com.example.gateway.dto.DiscoveryServiceStatusDto;
import com.example.gateway.dto.SubSystemCategoryDto;
import com.example.gateway.entity.ContextPathDiscoveryServiceMappingEntity;
import com.example.gateway.entity.DiscoveryServiceStatusEntity;
import com.example.gateway.entity.SubSystemCategoryEntity;
import com.example.gateway.web.vo.ContextPathDiscoveryServiceMappingVo;
import com.example.gateway.web.vo.DiscoveryServiceStatusVo;
import com.example.gateway.web.vo.SubSystemCategoryVo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CamelMapperUtil {
    CamelMapperUtil INSTANCE = Mappers.getMapper(CamelMapperUtil.class);

    ContextPathDiscoveryServiceMappingDto contextPathDiscoveryServiceEntityToDto(ContextPathDiscoveryServiceMappingEntity entity);

    ContextPathDiscoveryServiceMappingEntity contextPathDiscoveryServiceDtoToEntity(ContextPathDiscoveryServiceMappingDto dto);

    List<ContextPathDiscoveryServiceMappingEntity> contextPathDiscoveryServiceListDtoToEntity(List<ContextPathDiscoveryServiceMappingDto> list);

    List<ContextPathDiscoveryServiceMappingDto> contextPathDiscoveryServiceListEntityToDto(List<ContextPathDiscoveryServiceMappingEntity> list);

    List<ContextPathDiscoveryServiceMappingVo> contextPathDiscoveryServiceListDtoToVo(List<ContextPathDiscoveryServiceMappingDto> list);
    ContextPathDiscoveryServiceMappingVo contextPathDiscoveryServiceDtoToVo(ContextPathDiscoveryServiceMappingDto dto);
    ContextPathDiscoveryServiceMappingDto contextPathDiscoveryServiceVoToDto(ContextPathDiscoveryServiceMappingVo vo);

    DiscoveryServiceStatusEntity discoveryServiceStatusDtoToEntity(DiscoveryServiceStatusDto dto);

    DiscoveryServiceStatusDto discoveryServiceStatusEntityToDto(DiscoveryServiceStatusEntity entity);

    List<DiscoveryServiceStatusDto> discoveryServiceStatusListEntityToDto(List<DiscoveryServiceStatusEntity> list);

    List<DiscoveryServiceStatusEntity> discoveryServiceStatusListDtoToEntity(List<DiscoveryServiceStatusDto> list);
    List<DiscoveryServiceStatusVo> discoveryServiceStatusListDtoToVo(List<DiscoveryServiceStatusDto> list);

    SubSystemCategoryEntity subSystemCategoryDtoToEntity(SubSystemCategoryDto dto);

    SubSystemCategoryDto subSystemCategoryEntityToDto(SubSystemCategoryEntity entity);

    List<SubSystemCategoryDto> subSystemCategoryListEntityToDto(List<SubSystemCategoryEntity> list);

    List<SubSystemCategoryEntity> subSystemCategoryListDtoToEntity(List<SubSystemCategoryDto> list);
    List<SubSystemCategoryVo> subSystemCategoryListDtoToVo(List<SubSystemCategoryDto> list);

}
