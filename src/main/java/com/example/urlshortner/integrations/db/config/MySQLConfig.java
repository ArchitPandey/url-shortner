package com.example.urlshortner.integrations.db.config;

import com.example.urlshortner.integrations.db.props.MySqlProps;
import com.mysql.cj.jdbc.MysqlDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@MapperScan("com.example.urlshortner.integrations.db.mybatismapper")
@Slf4j
public class MySQLConfig {

    @Bean("mySQLDatasource")
    public DataSource mySQLDatasource(MySqlProps mySqlProps) {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setServerName(mySqlProps.getHost());
        mysqlDataSource.setPort(Integer.parseInt(mySqlProps.getPort()));
        mysqlDataSource.setUser(mySqlProps.getUser());
        mysqlDataSource.setPassword(mySqlProps.getPassword());
        mysqlDataSource.setDatabaseName(mySqlProps.getDbName());
        return mysqlDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("mySQLDatasource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }

}
