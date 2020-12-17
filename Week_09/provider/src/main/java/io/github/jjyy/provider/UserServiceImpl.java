package io.github.jjyy.provider;

import io.github.jjyy.domain.User;
import io.github.jjyy.service.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public User findById(int id) {
        return new User().setId(id).setName("KK" + System.currentTimeMillis());
    }
}
