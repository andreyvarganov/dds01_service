package ru.telda.dds_01_integration_service.card_template.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ru.telda.dds_01_integration_service.card_template.model.CardTemplate;

@Mapper
public interface CardTemplateMapper {
    void insert(@Param("card") CardTemplate cardTemplate);
    Long select(@Param("card") CardTemplate cardTemplate);
}
