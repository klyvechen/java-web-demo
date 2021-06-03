package rm.project.micorservice.ribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * 1. 學習Load Balance
 *
 */
@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/ribbon-consumer")
    public String indexConsumer() {
        return restTemplate.getForEntity("http://user-service/index", String.class).getBody();
    }

}
