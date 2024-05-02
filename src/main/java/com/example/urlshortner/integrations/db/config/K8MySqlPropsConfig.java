package com.example.urlshortner.integrations.db.config;

import com.example.urlshortner.integrations.db.props.MySqlProps;
import com.example.urlshortner.integrations.k8s.model.ServiceInfo;
import com.example.urlshortner.integrations.k8s.service.K8sServiceDiscovery;
import com.example.urlshortner.utils.SystemEnvUtils;
import io.kubernetes.client.openapi.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("k8")
@Slf4j
public class K8MySqlPropsConfig {

    private K8sServiceDiscovery k8sServiceDiscovery;

    public K8MySqlPropsConfig(K8sServiceDiscovery k8sServiceDiscovery) {
        this.k8sServiceDiscovery = k8sServiceDiscovery;
    }

    @Bean
    public MySqlProps mySqlProps(
            @Value("${mysql.svc-name}") String serviceName,
            @Value("${mysql.namespace}") String namespace,
            @Value("${mysql.user}") String user,
            @Value("${mysql.db-name}") String dbName
    ) throws ApiException {
        MySqlProps mySqlProps = new MySqlProps();

        ServiceInfo serviceInfo = this.k8sServiceDiscovery.getServiceInfo(serviceName, namespace);

        mySqlProps.setHost(serviceInfo.getClusterIp());
        mySqlProps.setPort(serviceInfo.getPort().toString());
        mySqlProps.setUser(user);
        mySqlProps.setPassword(SystemEnvUtils.getFromEnv("MYSQL_PWD"));
        mySqlProps.setDbName(dbName);

        log.info("MySql props {}", mySqlProps.toString());

        return mySqlProps;
    }

}
