package io.github.jjyy.autoconfigure;

import io.github.jjyy.Student;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jy
 */
@Configuration
@ConditionalOnClass(Student.class)
public class DemoAutoConfiguration {

    @Bean
    public Student student() {
        return new Student().setId(1L).setName("Demo");
    }

}
