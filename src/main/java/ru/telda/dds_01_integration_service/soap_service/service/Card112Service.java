package ru.telda.dds_01_integration_service.soap_service.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.telda.dds_01_integration_service.card_template.service.CardTemplateService;
import ru.telda.dds_01_integration_service.notify_service.CardResponseService;
import ru.telda.dds_01_integration_service.soap_service.integration.Card112;
import ru.telda.dds_01_integration_service.soap_service.integration.Card112Response;
import ru.telda.dds_01_integration_service.soap_service.integration.Card112Result;
import ru.telda.dds_01_integration_service.soap_service.integration.Card112Type;
import ru.telda.dds_01_integration_service.util.convertor.JsonConvertor;

import java.math.BigInteger;
import java.util.UUID;

@Service
public class Card112Service {

    @Autowired
    private CardTemplateService service;

    @Autowired
    private CardResponseService responseService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    //private Notify notify = new Notify();

    /**
     * метод, возвращающий ответ, формирующийся на основе данных, полученных с карточки
     * @param request - запрос, содержащий данные о карточке
     * @return response - ответ, включающий два поля: статус запроса (0 или 1) и номер карточки (cardId)
     */
    public Card112Response getResponse(Card112 request) {

        Card112Response response = new Card112Response();
        Card112Result result = new Card112Result();

        Card112Type card = request.getCard();

        if (createAndCheckCard(request)) {
            result.setNEmergencyCardId(card.getNEmergencyCardId());
            result.setErrorCode(BigInteger.ZERO);
        } else {
            result.setNEmergencyCardId(card.getNEmergencyCardId());
            result.setErrorCode(BigInteger.ONE);
        }

        // отправка отчета серверу dds
        responseService.sendResponse(card);

        response.setCard112Result(result);
        return response;

    }

    /**
     * Создаем карточку на основе данных, полученных из запроса и проверяем, есть ли такая карточка в БД
     * @param request - запрос, содержащий данные о карточке
     * @return true, если карточка с такими полями уже существует в БД. Иначе - false
     */

    public boolean createAndCheckCard(Card112 request) {

        Card112Type card = request.getCard();

        JsonNode node = JsonConvertor.convert(card);

        // в дальнейшем нужно будет передать параметр, чтобы можно было возвращать любой тип карточки
        // (в данный момент возвращает карточку типа 112)
        UUID cardTypeId = service.findCardTypeById();
        log.info("Получена карточка с extSystemCardId = " + node.get("nemergencyCardId") + " и CardTypeId = " + cardTypeId);

        return checkCardInBD(node, cardTypeId, node.get("nemergencyCardId").bigIntegerValue());

    }

    public boolean checkCardInBD(JsonNode node, UUID uuid, BigInteger bi) {

        Long count = service.countIdenticalCards(uuid, bi);

        //notify.setNotifyId(uuid);

        if (count == 0) {
            log.info("Карточки с таким параметрамом еще не существует в БД.");
            service.insert(node, uuid);
            log.info("Карточка успешно сохранена в БД.\n");

            //notify.setApproved(true);

            return true;
        }
        else {
            log.warn("Карточка с таким параметром уже присутствует в БД.\n");
            //notify.setApproved(false);
        }

        return false;

    }

}
