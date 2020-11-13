package ru.telda.dds_01_integration_service.card_template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.telda.dds_01_integration_service.card_template.mapper.CardTemplateMapper;
import ru.telda.dds_01_integration_service.card_template.model.CardTemplate;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.UUID;

@Service
public class CardTemplateService {

    @Autowired
    private CardTemplateMapper mapper;

    private CardTemplate cardTemplate;

    public void insert(JsonNode node, UUID uuid) {
        cardTemplate = new CardTemplate(node, uuid);
        mapper.insert(cardTemplate);
    }

    public Long countIdenticalCards(UUID cardTypeId, String extSystemCardId) {
        return mapper.countIdenticalCards(cardTypeId, extSystemCardId);
    }

    public UUID findCardTypeById() {
        return mapper.findCardTypeById();
    }

}
