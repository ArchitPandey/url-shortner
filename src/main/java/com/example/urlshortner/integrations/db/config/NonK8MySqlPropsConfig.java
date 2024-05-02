package com.example.urlshortner.integrations.db.config;

import com.example.urlshortner.integrations.db.props.MySqlProps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"nonk8", "default"})
@Slf4j
public class NonK8MySqlPropsConfig {

    @Bean
    public MySqlProps mySqlPropsNonK8(
            @Value("${mysql.host}") String host,
            @Value("${mysql.port}") String port,
            @Value("${mysql.user}") String user,
            @Value("${mysql.password}") String password,
            @Value("${mysql.db-name}") String dbName

    ) {
        MySqlProps mySqlProps = new MySqlProps();
        mySqlProps.setHost(host);
        mySqlProps.setPort(port);
        mySqlProps.setUser(user);
        mySqlProps.setPassword(password);
        mySqlProps.setDbName(dbName);
        return mySqlProps;
    }

}
