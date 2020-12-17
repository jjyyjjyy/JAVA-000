package io.github.jjyy;

import io.github.jjyy.api.RpcfxRequest;
import io.github.jjyy.api.RpcfxResolver;
import io.github.jjyy.provider.DemoResolver;
import io.github.jjyy.provider.OrderServiceImpl;
import io.github.jjyy.provider.UserServiceImpl;
import io.github.jjyy.server.RpcfxInvoker;
import io.github.jjyy.service.OrderService;
import io.github.jjyy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RpcfxServerApplication {

    @Autowired
    RpcfxInvoker invoker;

    public static void main(String[] args) {
        SpringApplication.run(RpcfxServerApplication.class, args);
    }

    @PostMapping("/")
    public Object invoke(@RequestBody RpcfxRequest request) {
        return invoker.invoke(request);
    }

    @Bean
    public RpcfxInvoker createInvoker(@Autowired RpcfxResolver resolver) {
        return new RpcfxInvoker(resolver);
    }

    @Bean
    public RpcfxResolver createResolver() {
        return new DemoResolver();
    }

    // 能否去掉name
    //
    @Bean(name = "io.github.jjyy.service.UserService")
    public UserService createUserService() {
        return new UserServiceImpl();
    }

    @Bean(name = "io.github.jjyy.service.OrderService")
    public OrderService createOrderService() {
        return new OrderServiceImpl();
    }

}
