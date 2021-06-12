package rm.project.microservice.netflix_hoxton.ribbon.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
