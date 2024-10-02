package datpt.spring.service;

import datpt.spring.dto.UserRequest;
import datpt.spring.dto.UserResponse;

public interface UserService {
    UserResponse createUser(UserRequest request);
}
