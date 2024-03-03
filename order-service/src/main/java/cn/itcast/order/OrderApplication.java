package cn.itcast.order;

import cn.itcast.feign.clients.UserClient;
import cn.itcast.feign.config.DefaultFeignConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@MapperScan("cn.itcast.order.mapper")
@SpringBootApplication
//defaultConfiguration = DefaultFeignConfiguration.class 在下面括号内加入 日志级别
//clients = {UserClient.class} 包扫描问题
@EnableFeignClients(defaultConfiguration = DefaultFeignConfiguration.class, clients = {UserClient.class})
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    /**
     * 创建restTemplate并注入spring容器
     * @return
     */
    @Bean
    @LoadBalanced   //负载均衡注解
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}