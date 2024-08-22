package myfirstmodule.imps;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringUtil {
    public static final ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    public static void main(String[] args) {
        HelloWorldController controller = context.getBean(HelloWorldController.class);
        controller.greet();

        var service = SpringUtil.context.getBean(IHelloWorldService.class);
        service.sayHello("World2");
    }
}