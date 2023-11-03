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
@MapperScan(value = "com.example.demo.mapper.demoInsta", sqlSessionFactoryRef = "demoInstaSqlSessionFactory")
@EnableTransactionManagement
public class DemoInstaDataBaseConfig {
    @Bean(name="demoInstaDataSource")
    @ConfigurationProperties(prefix = "spring.demoinsta.datasource")
    public DataSource gguDataSource(){
        return DataSourceBuilder.create().build();
    }


    @Bean(name="demoInstaSqlSessionFactory")
    public SqlSessionFactory demoInstaSqlSessionFactory(@Qualifier("demoInstaDataSource")DataSource demoInstaDataSource,
                                                  ApplicationContext applicationContext) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(demoInstaDataSource);
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/demoInsta/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }


    @Bean(name="demoInstaSqlSessionTemplate")
    public SqlSessionTemplate demoInstaSqlSessionTemplate(SqlSessionFactory demoInstaSqlSessionFactory) throws Exception{
        return new SqlSessionTemplate(demoInstaSqlSessionFactory);
    }
}


