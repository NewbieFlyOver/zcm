package zmc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix  //启动断路器
@EnableHystrixDashboard //启动 Hystrix 仪表盘
public class EurekaClientOrder {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientOrder.class, args);
	}
	
	@Bean
	@LoadBalanced  //开启负载均衡的功能,使用ribbon实现负载均衡
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
}
