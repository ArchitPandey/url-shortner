package com.example.urlshortner.integrations.db.props;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@ToString
public class MySqlProps {

    private String host;

    private String port;

    private String user;

    private String password;

    private String dbName;

}
