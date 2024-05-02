package com.example.urlshortner.integrations.k8s.service;

import com.example.urlshortner.integrations.k8s.model.ServiceInfo;
import io.kubernetes.client.openapi.ApiException;

public interface IServiceDiscovery {

    public ServiceInfo getServiceInfo(String serviceName, String namespace) throws ApiException;

}
