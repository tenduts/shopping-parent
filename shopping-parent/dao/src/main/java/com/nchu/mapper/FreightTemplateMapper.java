package com.nchu.mapper;

import com.nchu.model.FreightTemplate;
import com.nchu.model.FreightTemplateExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface FreightTemplateMapper {
    int countByExample(FreightTemplateExample example);

    int deleteByExample(FreightTemplateExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FreightTemplate record);

    int insertSelective(FreightTemplate record);

    List<FreightTemplate> selectByExample(FreightTemplateExample example);

    FreightTemplate selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FreightTemplate record, @Param("example") FreightTemplateExample example);

    int updateByExample(@Param("record") FreightTemplate record, @Param("example") FreightTemplateExample example);

    int updateByPrimaryKeySelective(FreightTemplate record);

    int updateByPrimaryKey(FreightTemplate record);
}