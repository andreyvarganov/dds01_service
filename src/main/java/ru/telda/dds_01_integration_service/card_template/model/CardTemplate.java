package ru.telda.dds_01_integration_service.card_template.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
    private LocalDateTime cardDate;

    private String addresses;
    private UUID crossId;
    private String crossName;
    private String flat;
    private Integer floor;
    private Integer floors;
    private String entrance;
    private String entranceCode;

    private LocalDateTime extRegDate;
    private LocalDateTime eventDate;
    private LocalDateTime sendedTo01Date;

    public CardTemplate(JsonNode node, UUID cardTypeId, LocalDateTime dateTime) {

        this.cardTypeId = cardTypeId;
        extSystemCardId = node.get("nemergencyCardId").bigIntegerValue();
        extCardContent = node;
        description = node.get("strIncidentDescription").asText();
        address = node.get("strAddressString").asText();
        cardDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(node.get("dtCreate").asLong()), ZoneId.of("UTC-0"));

        addresses = null;
        crossId = null;
        crossName = null;
        flat = node.get("strRoom").asText();
        floor = null;
        floors = node.get("strStoreys").asInt();
        entrance = node.get("strEntrance").asText();
        entranceCode = node.get("strEntranceCode").asText();

        extRegDate = dateTime;
        eventDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(node.get("dtIncident").asLong()), ZoneId.of("UTC-0"));

    }

}
