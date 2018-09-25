package zcm.feign.order;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@FeignClient("service-member") //需要调用服务名称
public interface MemberFeignService {
	
	@RequestMapping("/getUserList") //服务请求名称
	public List<String> getUserList();
	

}
