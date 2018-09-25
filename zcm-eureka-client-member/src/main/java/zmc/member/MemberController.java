package zmc.member;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
	
	@Value("${server.port}")
	private String service;
	
	//1. 响应get方法之不带参数
	@RequestMapping("/getUserList")
	public List<String> getUserList() {
		List<String> listUser = new ArrayList<String>();
		listUser.add("zhangsan");
		listUser.add("lisi");
		listUser.add("yushengjun");
		listUser.add(service);
		return listUser;
	}
	
	//2. 响应get方法带参数之/member/{firstName}/name/{secondName}形式
	@RequestMapping(value="/member/{firstName}/name/{secondName}")
	public List<String> getUserList01(@PathVariable String firstName, @PathVariable String secondName){ //@PathVariable注解表示获取的是url路径上的参数。 
		List<String> listUser = new ArrayList<String>();
		listUser.add(firstName);
		listUser.add(secondName);
		return listUser;
	}
	
	//3. 响应get方法带参数之/member/name形式
	@RequestMapping(value="/member/name")
	public List<String> getUserList02(@RequestParam String firstName, @RequestParam String secondName){  //@RequestParam注解表示是请求的参数。 
		List<String> listUser = new ArrayList<String>();
		listUser.add(firstName);
		listUser.add(secondName);
		return listUser;
	}
	
	
	
}
