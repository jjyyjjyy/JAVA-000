package io.github.jjyy.domain;

import com.baomidou.mybatisplus.annotation.*;
import io.github.jjyy.domain.enumeration.DeliveryType;
import io.github.jjyy.domain.enumeration.OrderStatus;
import io.github.jjyy.domain.enumeration.PaymentMethod;
import lombok.Data;

import java.time.Instant;

/**
 * @author jy
 */
@Data
@TableName("t_order")
public class Order {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;

    private Long paymentNo;

    private PaymentMethod paymentMethod;

    private Long deliveryNo;

    private DeliveryType deliveryType;

    private OrderStatus status;

    @TableField(fill = FieldFill.INSERT)
    private Instant createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Instant updatedAt;
}
