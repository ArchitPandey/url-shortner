package com.example.urlshortner.integrations.idgeneratorservice.config;

import com.example.urlshortner.integrations.idgeneratorservice.props.IdGeneratorServiceProps;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"nonk8", "default"})
public class NonK8IdGeneratorConfig {

    @Bean
    public IdGeneratorServiceProps idGeneratorServiceProps(
            @Value("${id-generator.svc-name}") String serviceName,
            @Value("${id-generator.host}") String host,
            @Value("${id-generator.port}") String port,
            @Value("${id-generator.generate-id-api}") String generateIdApi
    ) {
        IdGeneratorServiceProps idGeneratorServiceProps = new IdGeneratorServiceProps();
        idGeneratorServiceProps.setServiceName(serviceName);
        idGeneratorServiceProps.setHost(host);
        idGeneratorServiceProps.setPort(Integer.parseInt(port));
        idGeneratorServiceProps.setGenerateIdApi(generateIdApi);
        return idGeneratorServiceProps;
    }

}
