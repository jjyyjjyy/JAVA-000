package io.github.jjyy.util;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import io.github.jjyy.domain.Order;
import io.github.jjyy.domain.enumeration.DeliveryType;
import io.github.jjyy.domain.enumeration.OrderStatus;
import io.github.jjyy.domain.enumeration.PaymentMethod;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author jy
 */
public class OrderGenerator {

    public static List<Order> generateOrders(int num) {
        OrderStatus[] orderStatuses = OrderStatus.class.getEnumConstants();
        PaymentMethod[] paymentMethods = PaymentMethod.class.getEnumConstants();
        DeliveryType[] deliveryTypes = DeliveryType.class.getEnumConstants();
        return IntStream
            .rangeClosed(1, num)
            .parallel()
            .mapToObj(i -> new Order()
                .setUserId(Math.random() > 0.5 ? 1L : 2L)
                .setPaymentMethod(paymentMethods[i % paymentMethods.length])
                .setDeliveryType(deliveryTypes[i % deliveryTypes.length])
                .setPaymentNo(IdWorker.getId())
                .setDeliveryNo(IdWorker.getId())
                .setStatus(orderStatuses[i % orderStatuses.length]))
            .collect(Collectors.toList());
    }
}
