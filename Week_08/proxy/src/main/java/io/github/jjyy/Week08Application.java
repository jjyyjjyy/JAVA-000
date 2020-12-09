package io.github.jjyy;

import io.github.jjyy.service.OrderService;
import io.github.jjyy.util.OrderGenerator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author jy
 */
@SpringBootApplication
public class Week08Application {

    public static void main(String[] args) {
        SpringApplication.run(Week08Application.class, args);
    }

    @Bean
    public CommandLineRunner runner(OrderService service) {
        return args -> service.save(OrderGenerator.generateOrders(1).get(0));
    }
}
