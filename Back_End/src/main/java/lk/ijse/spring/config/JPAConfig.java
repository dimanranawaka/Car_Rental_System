package lk.ijse.spring.config;

import lk.ijse.spring.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "lk.ijse.spring.repo")
@EnableTransactionManagement
@PropertySource("classpath:properties.properties")
public class JPAConfig {

    @Autowired
    Environment environment;



    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource ds , JpaVendorAdapter vad){
        // This is the Spring Data JPA main object which handles all the features
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(ds);
        factory.setJpaVendorAdapter(vad);
        factory.setPackagesToScan(environment.getRequiredProperty("properties.entity"));
        return factory;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUsername(environment.getRequiredProperty("properties.username"));
        ds.setPassword(environment.getRequiredProperty("properties.password"));
        ds.setDriverClassName(environment.getRequiredProperty("properties.driver"));
        ds.setUrl(environment.getRequiredProperty("properties.url"));
        return ds;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        HibernateJpaVendorAdapter vad= new HibernateJpaVendorAdapter();
        vad.setDatabase(Database.MYSQL); // What is the DB
        vad.setGenerateDdl(true); // Data definition language enable
        vad.setDatabasePlatform(environment.getRequiredProperty("properties.dial")); // platform version
        vad.setShowSql(true); // if you wanted to see generated sql
        return vad;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }
}
