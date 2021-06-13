package rm.project.microservice.hoxton.openfeign.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import rm.project.microservice.hoxton.openfeign.service.UserService;

@RestController
public class ConsumerController {
    private Logger logger = LoggerFactory.getLogger(ConsumerController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/ribbon-consumer")
    public String helloConsumer() {
        return userService.hello();
    }

    @GetMapping("/ribbon-consumer/{name}")
    public String helloConsumerSir(@PathVariable String name) {
        return userService.hello(name);
    }

}
