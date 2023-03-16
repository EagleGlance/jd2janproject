package com.noirix;


import com.noirix.domain.User;
import com.noirix.repository.UserRepository;
import com.noirix.repository.UserRepositoryImpl;
import com.noirix.service.UserService;
import com.noirix.service.UserServiceImpl;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {

    public static void main(String[] args) {


        UserRepository userRepository = new UserRepositoryImpl();
        UserService userService = new UserServiceImpl(userRepository);

        List<User> allUser = userService.findAll();
        for (User user : allUser) {
            System.out.println(user);
        }

        List<User> all = userRepository.changedOverTime(1);
        for (User user : all) {
            System.out.println(user);
        }

        System.out.println(userService.update(9L, new User(9L, "1t28w", "lt682avw", "+36843t26724w6", "ahet68jwt","13456t8w277h", Timestamp.valueOf(LocalDateTime.now()))));
        System.out.println(userService.create(new User(27L, "i178170w", "e51li68a77w", "+000i7", "i1h7765e80jwt","13401755e68777h", Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()))));


        Map<String, String> emailPhone = userRepository.emailAndPhoneNumber();
        if (!emailPhone.isEmpty()) {
            System.out.println(emailPhone);
        }
    }
}