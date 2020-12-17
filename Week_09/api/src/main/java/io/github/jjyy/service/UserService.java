package io.github.jjyy.service;

import io.github.jjyy.aspect.Rpc;
import io.github.jjyy.domain.User;

@Rpc("http://localhost:8080/")
public interface UserService {

    User findById(int id);

}
