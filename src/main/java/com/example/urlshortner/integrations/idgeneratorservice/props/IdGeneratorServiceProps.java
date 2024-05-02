package com.example.urlshortner.integrations.idgeneratorservice.props;

import com.example.urlshortner.utils.SystemEnvUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@ToString
@Setter
@Slf4j
public class IdGeneratorServiceProps {

    private String serviceName;

    private String host;

    private int port;

    private String generateIdApi;

    public String getGenerateIdResourcePath() {
        StringBuilder resourcePathBuilder = new StringBuilder();
        String resourcePath = resourcePathBuilder
                .append("http://")
                .append(this.host)
                .append(":")
                .append(this.port)
                .append(this.generateIdApi)
                .toString();

        log.info("generate-id resource path {}", resourcePath);
        return resourcePath;
    }
}
