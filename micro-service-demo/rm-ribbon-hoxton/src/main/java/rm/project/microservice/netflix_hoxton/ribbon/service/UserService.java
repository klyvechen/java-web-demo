package rm.project.microservice.netflix_hoxton.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "userFallback")
    public String hello() {
        return restTemplate.getForEntity("http://user-service/index", String.class).getBody();
    }

    public String userFallback() {
        return "error";
    }

}
