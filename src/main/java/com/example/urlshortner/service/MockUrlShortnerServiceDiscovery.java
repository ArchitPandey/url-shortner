package com.example.urlshortner.service;

import com.example.urlshortner.integrations.k8s.model.ServiceInfo;
import com.example.urlshortner.integrations.k8s.service.IServiceDiscovery;
import io.kubernetes.client.openapi.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@Profile({"nonk8", "default"})
public class MockUrlShortnerServiceDiscovery implements IServiceDiscovery {

    private Map<String, ServiceInfo> serviceMap;

    public MockUrlShortnerServiceDiscovery() {
        this.serviceMap = new HashMap<>();
        this.serviceMap.put("url-shortner", new ServiceInfo("localhost", 8080, 8080));
    }

    @Override
    public ServiceInfo getServiceInfo(String serviceName, String namespace) throws ApiException {
        return this.serviceMap.get(serviceName);
    }
}
