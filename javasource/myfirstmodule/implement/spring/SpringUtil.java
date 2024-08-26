package myfirstmodule.implement.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringUtil {
    public static final ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
}