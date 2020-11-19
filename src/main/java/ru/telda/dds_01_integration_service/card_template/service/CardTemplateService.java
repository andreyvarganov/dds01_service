package ru.telda.dds_01_integration_service.card_template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.telda.dds_01_integration_service.card_template.mapper.CardTemplateMapper;
import ru.telda.dds_01_integration_service.card_template.model.CardTemplate;
import com.fasterxml.jackson.databind.JsonNode;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CardTemplateService {

    @Autowired
    private CardTemplateMapper mapper;

    public void insert(JsonNode node, UUID uuid) {
        CardTemplate cardTemplate = new CardTemplate(node, uuid, LocalDateTime.now());
        cardTemplate.setSendedTo01Date(LocalDateTime.now());
        mapper.insert(cardTemplate);
    }

    public Long countIdenticalCards(UUID cardTypeId, BigInteger extSystemCardId) {
        return mapper.countIdenticalCards(cardTypeId, extSystemCardId);
    }

    public UUID findCardTypeById() {
        return mapper.findCardTypeById();
    }

    public UUID findCardId(BigInteger id) {
        return mapper.findCardId(id);
    }

}
