package com.example.urlshortner.integrations.idgeneratorservice.config;

import com.example.urlshortner.integrations.idgeneratorservice.props.IdGeneratorServiceProps;
import com.example.urlshortner.integrations.k8s.model.ServiceInfo;
import com.example.urlshortner.integrations.k8s.service.K8sServiceDiscovery;
import io.kubernetes.client.openapi.ApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("k8")
public class K8IdGeneratorConfig {

    private K8sServiceDiscovery k8sServiceDiscovery;

    public K8IdGeneratorConfig(K8sServiceDiscovery k8sServiceDiscovery) {
        this.k8sServiceDiscovery = k8sServiceDiscovery;
    }

    @Bean
    public IdGeneratorServiceProps idGeneratorServiceProps(
            @Value("${id-generator.svc-name}") String serviceName,
            @Value("${id-generator.namespace}") String namespace,
            @Value("${id-generator.generate-id-api}") String generateIdApi
    ) throws ApiException {
        IdGeneratorServiceProps idGeneratorServiceProps = new IdGeneratorServiceProps();
        idGeneratorServiceProps.setServiceName(serviceName);

        ServiceInfo serviceInfo = this.k8sServiceDiscovery.getServiceInfo(serviceName, namespace);

        idGeneratorServiceProps.setHost(serviceInfo.getClusterIp());
        idGeneratorServiceProps.setPort(serviceInfo.getPort());
        idGeneratorServiceProps.setGenerateIdApi(generateIdApi);
        return idGeneratorServiceProps;
    }

}
