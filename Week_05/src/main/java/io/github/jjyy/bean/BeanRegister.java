package io.github.jjyy.bean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * @author jy
 */
@Configuration
public class BeanRegister {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanRegister.class);
        applicationContext.register(DemoBean.class);
    }
}
