package io.github.jjyy.config;

import com.zaxxer.hikari.HikariDataSource;
import io.github.jjyy.routing.DataSourceRouteAspect;
import io.github.jjyy.routing.DataSourceRouter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author jy
 */
@Configuration
@Profile("manual")
public class DataSourceConfiguration {

    @Bean
    public DataSource dataSource() {

        HikariDataSource master = new HikariDataSource();
        master.setUsername("jy");
        master.setPassword("123456");
        master.setJdbcUrl("jdbc:postgresql://localhost:5432/geekbang");

        HikariDataSource slave = new HikariDataSource();
        slave.setUsername("jy");
        slave.setPassword("123456");
        slave.setJdbcUrl("jdbc:postgresql://localhost:5433/geekbang");

        return new DataSourceRouter(master, Map.of("master", master, "slave", slave));
    }

    @Bean
    public DataSourceRouteAspect dataSourceRouteAspect() {
        return new DataSourceRouteAspect();
    }
}
