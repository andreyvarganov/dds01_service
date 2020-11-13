package ru.telda.dds_01_integration_service.card_template.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CardTemplate {

    private UUID id;
    private UUID cardTypeId;
    private String extSystemCardId;
    private JsonNode extCardContent;

    public CardTemplate(JsonNode node, UUID cardTypeId) {
        this.cardTypeId = cardTypeId;
        extSystemCardId = node.get("nemergencyCardId").asText();
        extCardContent = node;
    }

}
