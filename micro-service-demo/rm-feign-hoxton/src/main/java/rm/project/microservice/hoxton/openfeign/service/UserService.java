package rm.project.microservice.hoxton.openfeign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-service")
public interface UserService {

    @GetMapping("/hello")
    String hello();

    @GetMapping("/hello/{name}")
    String hello(@PathVariable String name);
}
