package io.github.jjyy.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.jjyy.domain.Order;
import io.github.jjyy.mapper.OrderMapper;
import org.springframework.stereotype.Service;

/**
 * @author jy
 */
@Service
public class OrderService extends ServiceImpl<OrderMapper, Order> {
}
