package com.noirix;

import com.noirix.repository.UserRepository;
import com.noirix.service.UserService;
import com.noirix.util.RandomValuesGenerator;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringTest {

    private static final Logger logger = Logger.getLogger(SpringTest.class);

    public static void main(String[] args) {
//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:application-context.xml");
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.noirix");

        //Object bean = applicationContext.getBean();
//        UserRepository repository = applicationContext.getBean("userRepository", UserRepository.class);
        UserRepository userRepository = applicationContext.getBean("userRepositoryImpl", UserRepository.class);
        UserService userService = applicationContext.getBean("userServiceImpl", UserService.class);
        RandomValuesGenerator randomValuesGenerator = applicationContext.getBean("getRandomGenerator", RandomValuesGenerator.class);

        logger.info(userRepository.findAll());
        logger.info(userService.findAll());
        logger.info(randomValuesGenerator.generateRandomString());
    }
}
