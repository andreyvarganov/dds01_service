package ru.telda.dds_01_integration_service.soap_service.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.telda.dds_01_integration_service.card_template.service.CardTemplateService;
import ru.telda.dds_01_integration_service.soap_service.integration.Card112;
import ru.telda.dds_01_integration_service.soap_service.integration.Card112Response;
import ru.telda.dds_01_integration_service.soap_service.integration.Card112Result;
import ru.telda.dds_01_integration_service.soap_service.integration.Card112Type;
import ru.telda.dds_01_integration_service.util.convertor.JsonConvertor;

import java.math.BigInteger;

@Service
public class Card112Service {

    @Autowired
    private CardTemplateService service;

    /**
     * метод, возвращающий ответ, формирующийся на основе данных, полученных с карточки
     * @param request - запрос, содержащий данные о карточке
     * @return response - ответ, включающий два поля: статус запроса (0 или 1) и номер карточки (cardId)
     */
    public Card112Response checkCard(Card112 request) {

        Card112Response response = new Card112Response();
        Card112Result result = new Card112Result();

        Card112Type card = request.getCard();

        JsonNode node = JsonConvertor.convert(card);

        if (service.select(node) == 0) {
            System.out.println("Такой записи не существует.");
            service.insert(node);
        }
        else System.out.println("Запись с такими параметрами уже присутствует в БД");

        if (!card.getNEmergencyCardId().equals(BigInteger.ZERO)) {
            result.setNEmergencyCardId(card.getNEmergencyCardId());
            result.setErrorCode(BigInteger.valueOf(0));
        } else {
            result.setNEmergencyCardId(BigInteger.ZERO);
            result.setErrorCode(BigInteger.ONE);
        }

        response.setCard112Result(result);
        return response;
    }

}
