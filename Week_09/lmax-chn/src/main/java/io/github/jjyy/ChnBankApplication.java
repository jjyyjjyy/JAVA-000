package io.github.jjyy;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import io.github.jjyy.service.ChnAccountService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author jy
 */
@MapperScan("io.github.jjyy.mapper")
@SpringBootApplication
public class ChnBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChnBankApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(ChnAccountService chnAccountService) {
        // 账户1的7人民币转到账户2的1美元
        return args -> chnAccountService.transfer(IdWorker.getId(), 1L, 700L, 2L);
    }
}
