package com.example.urlshortner.integrations.k8s.service;

import com.example.urlshortner.integrations.k8s.model.ServiceInfo;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Service;
import io.kubernetes.client.openapi.models.V1ServicePort;
import io.kubernetes.client.openapi.models.V1ServiceSpec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Profile("k8")
public class K8sServiceDiscovery implements IServiceDiscovery {

    private CoreV1Api coreV1Api;

    public K8sServiceDiscovery(CoreV1Api coreV1Api) {
        this.coreV1Api = coreV1Api;
    }

    public ServiceInfo getServiceInfo(String serviceName, String namespace) throws ApiException {
        try {
            V1Service service = this.coreV1Api.readNamespacedService(serviceName, namespace).execute();
            V1ServiceSpec serviceSpec = service.getSpec();

            String clusterIp = serviceSpec.getClusterIP();
            V1ServicePort servicePort = serviceSpec.getPorts().get(0);
            ServiceInfo serviceInfo = new ServiceInfo(
                    clusterIp,
                    servicePort.getPort(),
                    servicePort.getNodePort()
            );

            log.info("service info for service {} is {}", serviceName, serviceInfo);

            return serviceInfo;
        } catch(Exception e) {
            log.error("error in reading namespaced service", e);
            throw e;
        }
    }

}
