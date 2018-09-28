package zcm.feign.hystrix.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberHystrixController {
	
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/memberFeign/getUserList/hy")
	public List<String> getUserList06(){
		return memberService.getUserList();
	}
	

}
