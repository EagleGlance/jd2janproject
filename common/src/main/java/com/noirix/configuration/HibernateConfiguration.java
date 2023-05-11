package com.noirix.configuration;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class HibernateConfiguration {

    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {

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
    @Primary
    @Autowired
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setPackagesToScan("com.noirix");
        em.setDataSource(dataSource);

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(getAdditionalProperties());
        return em;
    }

    private Properties getAdditionalProperties() {
        Properties properties = new Properties();

        properties.put("hibernate.show_sql", "true");
        //properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
        properties.put("hibernate.connection.characterEncoding", "utf8mb4");
        properties.put("hibernate.connection.CharSet", "utf8mb4");
        properties.put("hibernate.connection.useUnicode", "true");

        //Second level cache turn-on
        properties.put("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
        properties.put("hibernate.cache.use_second_level_cache", "true");
        /*Third level cache turn-on*/
        properties.put("hibernate.cache.use_query_cache", "true");
        properties.put("javax.persistence.sharedCache.mode", "ALL");

        properties.put("hibernate.javax.cache.provider", "org.ehcache.jsr107.EhcacheCachingProvider");
        properties.put("hibernate.cache.ehcache.missing_cache_strategy", "create");

        return properties;
    }


//    @Bean
//    public CacheManager ehCacheManager() {
//        CachingProvider provider = Caching.getCachingProvider();
//        CacheManager cacheManager = provider.getCacheManager();
//
//        CacheConfigurationBuilder<String, String> configuration =
//                CacheConfigurationBuilder.newCacheConfigurationBuilder(
//                                String.class,
//                                String.class,
//                                ResourcePoolsBuilder
//                                        .newResourcePoolsBuilder().offheap(1, MemoryUnit.MB))
//                        .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(20)));
//
//        javax.cache.configuration.Configuration<String, String> stringDoubleConfiguration =
//                Eh107Configuration.fromEhcacheCacheConfiguration(configuration);
//
//        cacheManager.createCache("users", stringDoubleConfiguration);
//        return cacheManager;
//
//    }
}
