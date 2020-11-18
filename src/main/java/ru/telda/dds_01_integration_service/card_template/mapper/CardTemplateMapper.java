package ru.telda.dds_01_integration_service.card_template.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ru.telda.dds_01_integration_service.card_template.model.CardTemplate;

import java.math.BigInteger;
import java.util.UUID;

@Mapper
public interface CardTemplateMapper {

    void insert(@Param("card") CardTemplate cardTemplate);

    Long countIdenticalCards(@Param("cti") UUID cardTypeId, @Param("esci") BigInteger extSystemCardId);

    UUID findCardTypeById();

    UUID findCardId(BigInteger id);

}
