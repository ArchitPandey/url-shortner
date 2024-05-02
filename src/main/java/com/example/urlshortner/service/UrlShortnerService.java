package com.example.urlshortner.service;

import com.example.urlshortner.integrations.db.mybatismapper.ShortUrlMapper;
import com.example.urlshortner.integrations.idgeneratorservice.service.IdGeneratorService;
import com.example.urlshortner.integrations.k8s.model.ServiceInfo;
import com.example.urlshortner.integrations.k8s.service.IServiceDiscovery;
import io.kubernetes.client.openapi.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class UrlShortnerService {

    private IdGeneratorService idGeneratorService;
    private Base62ConverterService base62ConverterService;

    private ShortUrlMapper shortUrlMapper;

    private IServiceDiscovery serviceDiscovery;

    private String shortUrlPrefix;

    public UrlShortnerService(IdGeneratorService idGeneratorService,
                              Base62ConverterService base62ConverterService,
                              IServiceDiscovery serviceDiscovery,
                              ShortUrlMapper shortUrlMapper,
                              @Value("${app.svc-name}") String appServiceName,
                              @Value("${app.namespace}") String appNamespace
    ) throws ApiException {
        this.idGeneratorService = idGeneratorService;
        this.base62ConverterService = base62ConverterService;
        this.serviceDiscovery = serviceDiscovery;
        this.shortUrlMapper = shortUrlMapper;
        this.setShortUrlPrefix(appServiceName, appNamespace);
    }

    public String shorten(String longUrl) {
        long id = this.idGeneratorService.generateUniqueId();
        log.info("id generated {}", id);
        String base62String = this.base62ConverterService.longToBase62(id);
        log.info("id {}; base62 {}", id, base62String);
        this.shortUrlMapper.addShortUrl(id, base62String, longUrl);
        return this.shortUrlPrefix.concat(base62String);
    }

    public String findLongUrl(String shortUrl) {
        return this.shortUrlMapper.findLongUrl(shortUrl);
    }

    private String setShortUrlPrefix(String appServiceName, String appNamespace) throws ApiException {
        if (Objects.isNull(this.shortUrlPrefix) ) {
            ServiceInfo serviceInfo = this.serviceDiscovery.getServiceInfo(appServiceName, appNamespace);
            this.shortUrlPrefix = new StringBuilder()
                    .append("localhost")
                    .append(":")
                    .append(serviceInfo.getNodePort().toString())
                    .append("/r/")
                    .toString();
            log.info("short url prefix: {}", this.shortUrlPrefix);
        }

        return this.shortUrlPrefix;
    }

}
