package io.github.jjyy.service;

import io.github.jjyy.aspect.Rpc;
import io.github.jjyy.domain.Order;

@Rpc("http://localhost:8080/")
public interface OrderService {

    Order findOrderById(int id);

}
