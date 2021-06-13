package rm.project.microservice.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private DiscoveryClient client;

    @Value("${spring.application.name:}")
    private String serviceId;

    @GetMapping("/index")
    public String index() throws InterruptedException {
        List<ServiceInstance> serviceInstanceList = client.getInstances(serviceId);
        int sleepTime = new Random().nextInt(3000);
        logger.info("Sleep time:" + sleepTime);
        Thread.sleep(sleepTime);
        client.getServices().forEach(id -> {
            client.getInstances(id).forEach(instance -> {
                logger.info("/index, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
            });
        });
        return "index";
    }

    @GetMapping("/hello")
    public String hello() throws InterruptedException {
        List<ServiceInstance> serviceInstanceList = client.getInstances(serviceId);
        int sleepTime = new Random().nextInt(3000);
        logger.info("Sleep time:" + sleepTime);
        Thread.sleep(sleepTime);
        client.getServices().forEach(id -> {
            client.getInstances(id).forEach(instance -> {
                logger.info("/hello, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
            });
        });
        return "hello";
    }

    @GetMapping("/hello/{name}")
    public String helloSir(@PathVariable("name") String name) throws InterruptedException {
        List<ServiceInstance> serviceInstanceList = client.getInstances(serviceId);
        int sleepTime = new Random().nextInt(3000);
        logger.info("Sleep time:" + sleepTime);
        Thread.sleep(sleepTime);
        client.getServices().forEach(id -> {
            client.getInstances(id).forEach(instance -> {
                logger.info("/hello, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
            });
        });
        return "hello " + name;
    }
}
