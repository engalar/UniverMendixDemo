package myfirstmodule.imps;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "myfirstmodule.imps")
public class AppConfig {
}