package rm.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 1. 引入依賴
 * 1) 引入依賴
 * 2) 創建bootstrap.properties
 *  spring.application.name=rm-coupon
 *  spring.cloud.nacos.config.server-addr=127.0.0.1:8848
 * 3) 需要給配置中心添加一個叫做 數據集(Data Id) 應用名叫做properties
 * 4) 給 應用名.properties添加任何配置
 * 5) 動態獲取配置
 *  ＠ RefreshScope 動態獲取並刷新配置
 *  @ Value
 *  如果配置中心和當前應用配置文件中都配置相同項，配置中心優先配置
 *
 *  2. 細節
 *  1) 命名空間：配置隔離;
 *    默認命名空間為：public(保留空間)
 *    spring.cloud.nacos.config.namespace=cbc073da-d624-4f88-b9d4-148fdfa9d55e
 *
 *    每一個微服務都有一個自己的命名空間，
 *  2) 配置集: 所有配置的集合
 *
 *  3) 配置集Id: 類式配置文件名，取名叫做Data ID
 *
 *  4) 配置分組: group
 *    默認所有的配置及皆屬於: Default_Group
 *    可以在創建的時候直接告知所屬類別。 ex 1111, 1212, 618
 *
 *  每個微服務創建自己的環境，再給予屬於該內容的命名空間。
 *
 *  3. 同時加載多個配置集
 *  1) 為服務任何配置信息，任何配置文件都可以放在配置中心中
 *  2) 只要在bootstrap.properties指名有哪些即可
 *  3) @Value, @ConfigurationProperties等都可以使用
 *  以前SpringBoot任何方法從配置文件中獲取值，都能夠使用
 *  配置中心有的優先使用配置中心的
 */
@EnableDiscoveryClient
@SpringBootApplication
public class CouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(CouponApplication.class, args);
    }

}
