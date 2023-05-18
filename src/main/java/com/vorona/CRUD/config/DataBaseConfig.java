package com.vorona.CRUD.config;

import jakarta.annotation.Resource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("com.vorona.CRUD.DAO") //репозиторий или DAO еще прописать
@EnableTransactionManagement
@ComponentScan("com.vorona.CRUD")
@PropertySource("classpath:db.properties")
@EnableWebMvc
public class DataBaseConfig {
    @Resource
    private final Environment env;
    @Autowired
    public DataBaseConfig(Environment env) {
        this.env = env;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(getDataSource());
        em.setPackagesToScan(env.getRequiredProperty("db.package"));     //new String[]{"web"});
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(hibernateProperties()); //getHibertnateProperties()
        return em;
    }

    @Bean
    public DataSource getDataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        //      DriverManagerDataSource dataSource = new DriverManagerDataSource();
        basicDataSource.setDriverClassName(env.getRequiredProperty("db.driver"));
        basicDataSource.setUsername(env.getRequiredProperty("db.username"));
        basicDataSource.setPassword(env.getRequiredProperty("db.password"));
        basicDataSource.setUrl(env.getRequiredProperty("db.url"));/*?createDatabaseIfNotExist=true"*/
        return basicDataSource;
    }
    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.setProperty("hibernate.show sql", "true");
//        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
//        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));

        return properties;
    }
//    @Bean
//    public JdbcTemplate jdbcTemplate(){
//        return new JdbcTemplate(getDataSource());
//    }
//    @Bean
//    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
//        return new PersistenceExceptionTranslationPostProcessor();
//    }

}

