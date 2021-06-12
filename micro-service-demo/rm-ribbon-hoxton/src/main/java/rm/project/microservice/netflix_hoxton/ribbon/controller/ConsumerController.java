package rm.project.microservice.netflix_hoxton.ribbon.controller;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.command.HystrixCommandBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import rm.project.microservice.netflix_hoxton.ribbon.command.UserCommand;
import rm.project.microservice.netflix_hoxton.ribbon.service.UserService;

@RestController
public class ConsumerController {
    private Logger logger = LoggerFactory.getLogger(ConsumerController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/ribbon-consumer")
    public String indexConsumer() {
        return userService.hello();
    }

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/ribbon-consumer2")
    public String indexConsumer2() {
        return new UserCommand(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"), restTemplate,1l).execute();
    }


}
