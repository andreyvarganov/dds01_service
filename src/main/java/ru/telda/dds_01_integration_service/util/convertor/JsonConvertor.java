package ru.telda.dds_01_integration_service.util.convertor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.telda.dds_01_integration_service.soap_service.integration.Card112Type;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Класс, преобразующий Card112Type в JsonNode
 */
public class JsonConvertor {

    private static final StringWriter writer = new StringWriter();
    private static StringReader reader = null;

    private static final ObjectMapper mapper = new ObjectMapper();

    private static JsonNode node;

    public static JsonNode convert(Card112Type card) {
        try {
            mapper.writeValue(writer, card);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reader = new StringReader(mapper.writeValueAsString(card));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            node = mapper.readTree(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return node;
    }

}
