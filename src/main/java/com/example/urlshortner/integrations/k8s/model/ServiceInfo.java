package com.example.urlshortner.integrations.k8s.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ServiceInfo {

    private String clusterIp;

    //clusterIp service port
    private Integer port;

    //node port
    private Integer nodePort;

}
