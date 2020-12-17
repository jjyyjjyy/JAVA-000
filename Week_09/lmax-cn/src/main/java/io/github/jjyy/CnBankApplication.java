package io.github.jjyy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jy
 */
@MapperScan("io.github.jjyy.mapper")
@SpringBootApplication
public class CnBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(CnBankApplication.class, args);
    }
}
