package myfirstmodule.imps;

import org.springframework.stereotype.Service;

@Service()
public class HelloWorldService implements IHelloWorldService {

    public void sayHello(String name) {
        System.out.println(String.format("Hello, %s!", name));;
    }

}