package rm.project.microservice.netflix_hoxton.ribbon.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class UserCommand extends HystrixCommand<String> {
    private Logger logger = LoggerFactory.getLogger(UserCommand.class);

    private RestTemplate restTemplate;
    private Long id;

    public UserCommand(HystrixCommandGroupKey groupKey, RestTemplate restTemplate, Long id) {
        super(groupKey);
        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected String run() throws Exception {
        long start = System.currentTimeMillis();
        String result = restTemplate.getForEntity("http://user-service/hello", String.class).getBody();
        long end = System.currentTimeMillis();
        logger.info("UserCommand, Spend time: {}, result: {}", (end - start), result);
        return result;
    }
}
