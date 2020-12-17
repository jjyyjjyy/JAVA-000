package io.github.jjyy.provider;

import io.github.jjyy.domain.Order;
import io.github.jjyy.service.OrderService;

import java.math.BigDecimal;

public class OrderServiceImpl implements OrderService {

    @Override
    public Order findOrderById(int id) {
        return new Order().setId(id).setName("Cuijing" + System.currentTimeMillis()).setAmount(BigDecimal.valueOf(9.9));
    }
}
