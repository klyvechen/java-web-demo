package rm.project.micorservice.ribbon.service;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.fallback.DefaultFallbackDecorator;
import io.vavr.CheckedFunction0;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    {
        CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("testName");
        // When I decorate my function and invoke the decorated function
        CheckedFunction0<String> checkedSupplier =
                CircuitBreaker.decorateCheckedSupplier(circuitBreaker, () -> {
                    throw new RuntimeException("BAM!");
                });
        try {
            circuitBreaker.executeCallable(()->{return "";});
        } catch (Exception e) {

        }

        Supplier s;
        Try<String> result = Try.of(checkedSupplier)
                .recover(throwable -> "Hello Recovery");
        // Then the function should be a success,
        // because the exception could be recovered
//        assertThat(result.isSuccess()).isTrue();
        // and the result must match the result of the recovery function.
//        assertThat(result.get()).isEqualTo("Hello Recovery");
    }


    public String hello() {
        return restTemplate.getForEntity("http://user-service/index", String.class).getBody();
    }

    public String userFallback() {
        return "error";
    }
}
