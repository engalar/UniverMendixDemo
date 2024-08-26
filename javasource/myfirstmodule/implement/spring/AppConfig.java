package myfirstmodule.implement.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "myfirstmodule.implement")
public class AppConfig {
}