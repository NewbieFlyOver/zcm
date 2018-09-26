package zcm.feign.order;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import feign.Param;

@RestController
@FeignClient("service-member") //生成代理，并注册到Spring容器中,需要调用服务名称
public interface MemberFeignService {
	
	/*
	1.如果使用url传参,加注解@PathVariable,不能传递对象,只能用作传递基本数值;
	2.如果使用get请求传递参数,同样不能传递对象,而且如果传递参数的话,需要用到@RequestParam标注名称
	3.传递对象请使用post方式,并且接收端使用@RequestBody注解

	*/
	
	@RequestMapping("/feign/getUserList") //服务请求名称
	public List<String> getUserList();
	
	@RequestMapping("/feign/getUserList/param") 
	//@RequestParam("name")中的("name")一定要加！一定要加！一定要加！
	public List<String> getUserList02(@RequestParam("name") String name); 
	
	@RequestMapping("/feign/getUserList/{name}") 
	//@RequestParam("name")中的("name")一定要加！一定要加！一定要加！
	public List<String> getUserList03(@RequestParam("name") String name); 
	
	@RequestMapping("/feign/getUserList/id?id={id}") 
	//@RequestParam("name")中的("name")一定要加！一定要加！一定要加！
	public List<String> getUserList04(@RequestParam("id") String id); 
	
	@RequestMapping("/feign/getUserList/entity") 
	public List<Member> getUserList05(Member member); 
}
