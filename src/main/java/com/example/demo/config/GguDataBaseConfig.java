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
@MapperScan(value = "com.example.demo.mapper.ggu", sqlSessionFactoryRef = "gguSqlSessionFactory")
@EnableTransactionManagement
public class GguDataBaseConfig {
    @Bean(name="gguDataSource")
    @ConfigurationProperties(prefix = "spring.ggubabo.datasource")
    public DataSource gguDataSource(){
        return DataSourceBuilder.create().build();
    }


    @Bean(name="gguSqlSessionFactory")
    public SqlSessionFactory gguSqlSessionFactory(@Qualifier("gguDataSource")DataSource gguDataSource,
                                                     ApplicationContext applicationContext) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(gguDataSource);
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/ggu/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }


    @Bean(name="gguSqlSessionTemplate")
    public SqlSessionTemplate gguSqlSessionTemplate(SqlSessionFactory gguSqlSessionFactory) throws Exception{
        return new SqlSessionTemplate(gguSqlSessionFactory);
    }
}


