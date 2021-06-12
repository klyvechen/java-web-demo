package rm.project.microservice.netflix_hoxton.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "userFallback",
        commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2200")
        }
    )
    public String hello() {
        long start = System.currentTimeMillis();
        String result = restTemplate.getForEntity("http://user-service/hello", String.class).getBody();
        long end = System.currentTimeMillis();
        logger.info("UserService, Spend time: {}, result: {}", (end - start), result);
        return result;
    }

    public String userFallback() {
        logger.info("userFallback");
        return "error";
    }

}
