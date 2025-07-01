package datpt.spring.service;

import datpt.spring.entity.User;

public interface UserService {
    User createUser(User user);
    void saveRefreshToken(String refreshToken, String username);
}
