package com.noirix;

import com.noirix.domain.User;
import com.noirix.repository.UserRepository;
import com.noirix.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class SpringTest {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
                "com.noirix"
        );

        UserRepository userRepository = applicationContext.getBean("userRepositoryImpl", UserRepository.class);

        UserService userService = applicationContext.getBean("userServiceImpl", UserService.class);

        System.out.println(userService.findAll());
        System.out.println(userService.findById(9L));

        System.out.println(userService.changedOverTime(1));

        System.out.println(userService.update(9L, new User(9L, "1t28w", "lt682avw",
                "+375298832456", "asdf@email.com","KH103456t8277",
                Timestamp.valueOf(LocalDateTime.now()))));

        System.out.println(userService.create(new User(28L, "i1788170w", "e518i68a77w", "+375298795670", "qwer@email.com","KH1340175589", Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()))));

        System.out.println(userService.emailAndPhoneNumber());
    }
}