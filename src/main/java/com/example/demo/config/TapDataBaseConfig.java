package com.example.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
@Configuration
@MapperScan(value = "com.example.demo.mapper.tap", sqlSessionFactoryRef = "tapSqlSessionFactory")
@EnableTransactionManagement
public class TapDataBaseConfig {
    @Bean(name="tapDataSource")
    @ConfigurationProperties(prefix = "spring.tap.datasource")
    public DataSource gguDataSource(){
        return DataSourceBuilder.create().build();
    }


    @Bean(name="tapSqlSessionFactory")
    public SqlSessionFactory tapSqlSessionFactory(@Qualifier("tapDataSource")DataSource tapDataSource,
                                                  ApplicationContext applicationContext) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(tapDataSource);
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/tap/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }


    @Bean(name="tapSqlSessionTemplate")
    public SqlSessionTemplate tapSqlSessionTemplate(SqlSessionFactory tapSqlSessionFactory) throws Exception{
        return new SqlSessionTemplate(tapSqlSessionFactory);
    }
}


