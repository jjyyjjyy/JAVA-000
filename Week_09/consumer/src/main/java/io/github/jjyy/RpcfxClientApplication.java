package io.github.jjyy;

import io.github.jjyy.client.Rpcfx;
import io.github.jjyy.domain.Order;
import io.github.jjyy.domain.User;
import io.github.jjyy.service.OrderService;
import io.github.jjyy.service.UserService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RpcfxClientApplication {

    // 二方库
    // 三方库 lib
    // nexus, userserivce -> userdao -> user
    //

    public static void main(String[] args) {

        // UserService service = new xxx();
        // service.findById

        UserService userService = Rpcfx.create(UserService.class);
        User user = userService.findById(1);
        System.out.println("find user id=1 from server: " + user.getName());

        OrderService orderService = Rpcfx.create(OrderService.class);
        Order order = orderService.findOrderById(1992129);
        System.out.println(String.format("find order name=%s, amount=%f", order.getName(), order.getAmount()));

        // 新加一个OrderService

//		SpringApplication.run(RpcfxClientApplication.class, args);
    }

}
