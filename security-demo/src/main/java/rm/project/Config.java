package rm.project;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(value = "com.amazintalker.interview.rm")
public class Config extends SpringBootServletInitializer {
}
