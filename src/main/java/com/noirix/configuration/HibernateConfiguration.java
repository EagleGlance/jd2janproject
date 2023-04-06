package com.noirix.configuration;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class HibernateConfiguration {

    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {
        // Fix Postgres JPA Error:
        // Method org.postgresql.jdbc.PgConnection.createClob() is not yet implemented.
        // properties.put("hibernate.temp.use_jdbc_metadata_defaults",false);

        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

        // Package contain entity classes
        factoryBean.setPackagesToScan("com.noirix");
        factoryBean.setDataSource(dataSource);
        factoryBean.setHibernateProperties(getAdditionalProperties());
        factoryBean.afterPropertiesSet();
        //
        SessionFactory sf = factoryBean.getObject();
        System.out.println("## getSessionFactory: " + sf);
        return sf;
    }

    //Entity Manager
//    @Autowired
//    @Bean(name = "entityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
//        LocalContainerEntityManagerFactoryBean em
//                = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource);
//        em.setPackagesToScan("com.htp");
//
//        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//        em.setJpaProperties(getAdditionalProperties());
//
//        return em;
//    }

    private Properties getAdditionalProperties() {
        Properties properties = new Properties();

        // See: application.properties
        properties.put("hibernate.show_sql", "true");
        //properties.put("hibernate.default_schema", "apptest");
        properties.put("current_session_context_class", "org.springframework.orm.hibernate5.SpringSessionContext");
        return properties;
    }
}
