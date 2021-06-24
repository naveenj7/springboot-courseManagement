package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.example.demo.repo",
        entityManagerFactoryRef = "courseEntityManagerFactory",
        transactionManagerRef= "courseTransactionManager"
)
public class MyCustomrDataSourceConfiguration {
	
	@Bean
    @Primary
    @ConfigurationProperties("app.datasource.course")
    public DataSourceProperties courseDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.course.configuration")
    public DataSource courseDataSource() {
        return courseDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "courseEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean courseEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(courseDataSource())
                .packages("com.example.demo.entity")
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager courseTransactionManager(
            final @Qualifier("courseEntityManagerFactory") LocalContainerEntityManagerFactoryBean courseEntityManagerFactory) {
        return new JpaTransactionManager(courseEntityManagerFactory.getObject());
    }


}
