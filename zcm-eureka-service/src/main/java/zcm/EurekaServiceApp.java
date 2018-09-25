package zcm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer //向服务中心注册
public class EurekaServiceApp {
	
	public static void main(String[] args) {
		SpringApplication.run(EurekaServiceApp.class, args);
		
	}

}
