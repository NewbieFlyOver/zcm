package zcm.feign.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import feign.Param;

@RestController
public class MemberFeignController {
	
	@Autowired
	private MemberFeignService memberFeignService ;
	
	@RequestMapping(value="/memberFeign/getUserList")
	public List<String> getUserList(){
		return memberFeignService.getUserList();
	}
	
	@RequestMapping(value="/memberFeign/getUserList/{name}")
	public List<String> getUserList02(@PathVariable String name){
		return memberFeignService.getUserList02(name);
	}
	
	@RequestMapping(value="/memberFeign/getUserList/param/{name}")
	public List<String> getUserList03(@PathVariable String name){
		return memberFeignService.getUserList03(name);
	}
	
	@RequestMapping(value="/memberFeign/getUserList/id")
	public List<String> getUserList04(String id){
		System.out.println(id+"******");
		return memberFeignService.getUserList04(id);
	}

	@RequestMapping(value="/memberFeign/getUserList/entity")
	public List<Member> getUserList05(@RequestBody Member member){
		
		return memberFeignService.getUserList05(member);
	}

}
