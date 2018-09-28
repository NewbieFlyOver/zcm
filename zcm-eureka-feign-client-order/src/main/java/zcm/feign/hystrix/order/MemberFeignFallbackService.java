package zcm.feign.hystrix.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MemberFeignFallbackService implements MemberService{

	@Override
	public List<String> getUserList() {
		//这里为降级服务所要执行的方法
		List<String> listUser = new ArrayList<String>();
		listUser.add("not orderUser list");
		return listUser;
	}
	

}


