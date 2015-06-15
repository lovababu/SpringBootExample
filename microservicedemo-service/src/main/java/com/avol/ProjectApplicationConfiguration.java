package com.avol;

import com.avol.util.CustomErrorAttribute;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Durga on 6/8/2015.
 * Configuration for service microservicedemo.
 * Register service specific beans here.
 */

@Configuration
@Slf4j
public class ProjectApplicationConfiguration {


    @Value("${hibernate.hibernateDialect}")
    private String hibernateDialect;
    @Value("${hibernate.showSQL}")
    private String showSql;
    @Value("${hibernate.generateStatistics}")
    private String generateStatistics;

    /*@Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }*/

    @Bean
    public Validator validator(){
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public ErrorAttributes customErrorAttribute(){
        return new CustomErrorAttribute();
    }

    @Bean(name = "sessionFactory")
    public SessionFactory sessionFactory() throws IOException {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan("com.avol.domain");
        sessionFactoryBean.setAnnotatedPackages("com.avol.domain");
        sessionFactoryBean.setHibernateProperties(getHibernateProperties());
        sessionFactoryBean.afterPropertiesSet();
        return sessionFactoryBean.getObject();
    }

    /**
     * Spring provided H2 Embedded Database.
     * Read the dbscript and initiates the Database with the name H2-Test-DB.
     *
     * @return DataSource
     */
    @Bean(name = "dataSource")
    public DataSource dataSource(){
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        builder.setName("H2-Test-DB");
        EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:db-scripts/project-ddl.sql")
                .addScript("classpath:db-scripts/project-dml.sql").build();
        log.info("Initiating the database from dbscript.");
        return db;
    }


    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager() throws Exception {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(this.sessionFactory());
        return transactionManager;
    }

    public Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", hibernateDialect);
        properties.put("hibernate.show_sql", showSql);
        properties.put("hibernate.cache.use_second_level_cache", false);
        properties.put("hibernate.generate_statistics", generateStatistics);
        return properties;
    }
}
