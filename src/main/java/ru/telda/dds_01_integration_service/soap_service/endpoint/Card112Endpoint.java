package ru.telda.dds_01_integration_service.soap_service.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.telda.dds_01_integration_service.soap_service.integration.Card112;
import ru.telda.dds_01_integration_service.soap_service.integration.Card112Response;
import ru.telda.dds_01_integration_service.soap_service.service.Card112Service;

@Endpoint
public class Card112Endpoint {

    private final String NAMESPACE = "http://www.telda.ru/dds_01_integration_service/soap_service/integration";

    @Autowired
    private Card112Service service;

    @PayloadRoot(namespace = NAMESPACE, localPart = "Card112")
    @ResponsePayload
    public Card112Response getCardStatus(@RequestPayload Card112 request){
        return service.checkCard(request);
    }

}

