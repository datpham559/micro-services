package datpt.spring.service.impl;

import datpt.spring.entity.User;
import datpt.spring.repository.UserRepository;
import datpt.spring.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(User user) {
        logger.info("Start creating new user");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        logger.info("End creating new user");
        return savedUser;
    }

    @Override
    public void saveRefreshToken(String refreshToken, String username) {
        User user = userRepository.findByUsername(username).get();
        user.setRefreshToken(refreshToken);
        userRepository.save(user);
    }
}
