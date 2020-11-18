package ru.telda.dds_01_integration_service.card_template.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CardTemplate {

    private UUID id;
    private UUID cardTypeId;
    private BigInteger extSystemCardId;
    private JsonNode extCardContent;
    private String description;
    private String address;
    private Long cardDate;

    public CardTemplate(JsonNode node, UUID cardTypeId) {
        this.cardTypeId = cardTypeId;
        extSystemCardId = node.get("nemergencyCardId").bigIntegerValue();
        extCardContent = node;
        description = node.get("strIncidentDescription").asText();
        address = node.get("strAddressString").asText();
        cardDate = node.get("dtCreate").asLong();
    }

}
