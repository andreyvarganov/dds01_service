package ru.telda.dds_01_integration_service.card_template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.telda.dds_01_integration_service.card_template.mapper.CardTemplateMapper;
import ru.telda.dds_01_integration_service.card_template.model.CardTemplate;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class CardTemplateService {

    @Autowired
    private CardTemplateMapper mapper;

    private CardTemplate cardTemplate;

    public void insert(JsonNode node) {
        cardTemplate = new CardTemplate(node);
        mapper.insert(cardTemplate);
    }

    public Long select(JsonNode node) {
        cardTemplate = new CardTemplate(node);
        return mapper.select(cardTemplate);
    }
}
