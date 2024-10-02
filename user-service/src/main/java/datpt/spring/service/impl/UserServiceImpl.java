package datpt.spring.service.impl;

import datpt.spring.dto.UserRequest;
import datpt.spring.dto.UserResponse;
import datpt.spring.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserResponse createUser(UserRequest request) {
        return null;
    }
}
