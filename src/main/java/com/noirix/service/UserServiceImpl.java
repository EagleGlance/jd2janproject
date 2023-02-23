package com.noirix.service;

import com.noirix.domain.User;
import com.noirix.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//id = userServiceImpl
public class UserServiceImpl implements UserService {

//    @Autowired
//    @Qualifier("userSecondRepositoryImpl")

//    @Inject //JSR-330
//    @Named("userRepositoryImpl")
//    @Named("userRepositoryImpl")

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findOne(Long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        /*Validation layer*/
        return userRepository.findAll();
    }

    @Override
    public User create(User object) {
        /*Validation layer*/
        if (object.getWeight() > 80) {
            throw new RuntimeException("Something wrong!");
        }

        return userRepository.create(object);
    }

    @Override
    public User update(User object) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
