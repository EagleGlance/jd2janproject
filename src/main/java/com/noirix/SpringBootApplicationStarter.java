package com.noirix;

import com.noirix.configuration.ApplicationConfig;
import com.noirix.configuration.HibernateConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages = "com.noirix")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableWebMvc
@Import(
        {ApplicationConfig.class,
                HibernateConfiguration.class}
)
public class SpringBootApplicationStarter {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplicationStarter.class, args);
    }
}
