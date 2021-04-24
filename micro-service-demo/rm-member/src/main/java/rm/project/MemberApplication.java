package rm.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * 1. 想要遠程調用別的服務
 * 1) 引入open-feign
 * 2)
 *  2.1 編寫一個接口(介面)，需要調用遠程服務
 *  2.2 聲明街口的每一個方法都是條用哪個遠程服務的請求
 * 3) 開啟遠程調用功能
 */
@EnableFeignClients("rm.project.member.feign")
@EnableDiscoveryClient
@SpringBootApplication
public class MemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class, args);
    }

}
