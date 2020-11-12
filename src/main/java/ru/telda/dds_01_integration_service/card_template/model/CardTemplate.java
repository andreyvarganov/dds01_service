package ru.telda.dds_01_integration_service.card_template.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
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
    private BigInteger cardId;
    private UUID cardTypeId;
    private String cardAddress;
    private String cardData;
    private String cardDescription;
    private boolean success;
    private boolean isActive;
    private JsonNode cardInfo;

    public CardTemplate(JsonNode node) {
        this.cardId = node.get("nemergencyCardId").bigIntegerValue();
        this.cardTypeId = UUID.randomUUID();
        this.cardAddress = node.get("strAddressString").asText();
        this.cardData = null;
        this.cardDescription = null;
        this.success = true;
        this.isActive = true;
        this.cardInfo = node;
    }

}
