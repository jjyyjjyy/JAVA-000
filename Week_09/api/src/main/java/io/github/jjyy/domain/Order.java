package io.github.jjyy.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Order {

    private Integer id;

    private String name;

    private BigDecimal amount;

}
