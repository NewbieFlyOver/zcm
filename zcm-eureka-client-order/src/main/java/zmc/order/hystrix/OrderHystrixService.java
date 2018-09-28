package zmc.order.hystrix;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class OrderHystrixService {
	@Autowired
	RestTemplate restTemplate;
	
	//在ribbon中使用hystrix,实现服务降级，fallbackMethod为降级后执行的对应的方法。
	//作用:服务发生错误，回调方法。解决服务雪崩的情况
	//需要在启动类中添加@EnableHystrix注解开启断路器
	@HystrixCommand(fallbackMethod = "orderError")
	public List<String> getOrderByUserList(){
		return restTemplate.getForObject("http://service-member/getUserList", List.class);
	}
	
	
	public List<String> orderError() {
		List<String> listUser = new ArrayList<String>();
		listUser.add("not orderUser list");
		return listUser;
	}

}
