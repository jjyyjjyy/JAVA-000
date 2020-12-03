package io.github.jjyy;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.google.common.collect.Lists;
import io.github.jjyy.domain.Order;
import io.github.jjyy.domain.enumeration.DeliveryType;
import io.github.jjyy.domain.enumeration.OrderStatus;
import io.github.jjyy.domain.enumeration.PaymentMethod;
import io.github.jjyy.mapper.OrderMapper;
import io.github.jjyy.service.OrderService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author jy
 */
@SpringBootApplication
public class Week07Application {

    private static final int COUNT = 1_000_000;

    public static void main(String[] args) {
        SpringApplication.run(Week07Application.class, args);
    }

    @Bean
    public ApplicationRunner runner(OrderMapper orderMapper) {
        return args -> {
            List<Order> noob = generateOrders(COUNT);
            List<List<Order>> elite = Lists.partition(generateOrders(COUNT), 500);

            orderMapper.delete(null);
            StopWatch watch = new StopWatch();
            watch.start("单条插入");
            noob.parallelStream().forEach(orderMapper::insert);
            watch.stop();
            orderMapper.delete(null);

            watch.start("批量插入");
            elite.parallelStream().forEach(orderMapper::insertBatchSomeColumn);
            watch.stop();

            System.out.println(watch.prettyPrint());
        };
    }

    @Bean
    public ApplicationRunner dataSourceRouterRunner(OrderService orderService) {
        return args -> {
            orderService.insertIntoMaster(generateOrders(1).get(0));
            System.out.println(orderService.findFromSlave(1L));
        };
    }

    private List<Order> generateOrders(int num) {
        OrderStatus[] orderStatuses = OrderStatus.class.getEnumConstants();
        PaymentMethod[] paymentMethods = PaymentMethod.class.getEnumConstants();
        DeliveryType[] deliveryTypes = DeliveryType.class.getEnumConstants();
        return IntStream
            .rangeClosed(1, num)
            .parallel()
            .mapToObj(i -> new Order()
                .setUserId(1L)
                .setPaymentMethod(paymentMethods[i % paymentMethods.length])
                .setDeliveryType(deliveryTypes[i % deliveryTypes.length])
                .setPaymentNo(IdWorker.getId())
                .setDeliveryNo(IdWorker.getId())
                .setStatus(orderStatuses[i % orderStatuses.length]))
            .collect(Collectors.toList());
    }
}
