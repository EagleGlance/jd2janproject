package com.noirix.configuration;

import com.noirix.util.RandomValuesGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public RandomValuesGenerator getRandomGenerator(
//            @Autowired @Qualifier("userRepositoryImpl") UserRepository userRepository) { - example of autowiring
//            UserRepository userRepository) { - example of autowiring
    ) {

        return new RandomValuesGenerator();
    }

}
