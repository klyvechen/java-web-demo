package rm.project.microservice.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private DiscoveryClient client;

    @Value("${spring.application.name:}")
    private String serviceId;

    @RequestMapping("/index")
    public String index() {
        List<ServiceInstance> serviceInstanceList = client.getInstances(serviceId);
        client.getServices().forEach(id -> {
            client.getInstances(id).forEach(instance -> {
                logger.info("/hello, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
            });
        });
        return "index";
    }
}
