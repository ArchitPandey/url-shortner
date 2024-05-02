package com.example.urlshortner.integrations.idgeneratorservice.service;

import com.example.urlshortner.integrations.idgeneratorservice.model.UniqueId;
import com.example.urlshortner.integrations.idgeneratorservice.props.IdGeneratorServiceProps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class IdGeneratorService {

    private RestTemplate restTemplate;

    private IdGeneratorServiceProps idGeneratorServiceProps;

    public IdGeneratorService(
            @Qualifier("idGeneratorRestTemplate") RestTemplate restTemplate,
            IdGeneratorServiceProps idGeneratorServiceProps
    ) {
        this.restTemplate = restTemplate;
        this.idGeneratorServiceProps = idGeneratorServiceProps;
    }

    public long generateUniqueId() {
        ResponseEntity<UniqueId> response = this.restTemplate.getForEntity(this.idGeneratorServiceProps.getGenerateIdResourcePath(), UniqueId.class);
        return response.getBody().getId();
    }

}
