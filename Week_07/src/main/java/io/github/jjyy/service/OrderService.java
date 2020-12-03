package io.github.jjyy.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.jjyy.domain.Order;
import io.github.jjyy.mapper.OrderMapper;
import io.github.jjyy.routing.DataSourceRoute;
import org.springframework.stereotype.Service;

/**
 * @author jy
 */
@Service
public class OrderService extends ServiceImpl<OrderMapper, Order> {

    @DataSourceRoute("master")
    public void insertIntoMaster(Order order) {
        baseMapper.insert(order);
    }

    @DataSourceRoute("slave")
    public Order findFromSlave(Long id) {
        return baseMapper.selectById(id);
    }
}
