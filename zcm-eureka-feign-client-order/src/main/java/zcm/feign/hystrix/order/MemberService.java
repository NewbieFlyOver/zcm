package zcm.feign.hystrix.order;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

//在feign中使用hystrix做服务降级，解决服务雪崩现象。 
//fallback 为降级服务所调用的类，其要实现MemberService这个接口，里面的方法的实现即为降级服务所要调用的对应的方法体
//不需要在启动类中添加@EnableHystrix注解开启断路器,因为Feign已经实现了Hystrix

@FeignClient(value ="service-member",fallback=MemberFeignFallbackService.class)
public interface MemberService {
	
	@RequestMapping("/feign/getUserList") 
	public List<String> getUserList();

}
