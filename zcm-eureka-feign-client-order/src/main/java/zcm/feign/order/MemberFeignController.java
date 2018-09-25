package zcm.feign.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberFeignController {
	
	@Autowired
	private MemberFeignService memberFeignService ;
	
	@RequestMapping(value="/memberFeign/getUserList")
	public List<String> getUserList(){
		return memberFeignService.getUserList();
	}

}
