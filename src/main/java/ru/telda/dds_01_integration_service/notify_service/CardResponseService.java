package ru.telda.dds_01_integration_service.notify_service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.telda.dds_01_integration_service.soap_service.integration.Card112Type;

@Service
public class CardResponseService {

    public void sendResponse(Card112Type card, Notify notify) {

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/v1/integration/newCard";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer swaggertoken");

        HttpEntity<Notify> requestEntity = new HttpEntity<>(notify, httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(url + "/" + card.getNEmergencyCardId(), HttpMethod.POST, requestEntity, String.class);

    }
}
