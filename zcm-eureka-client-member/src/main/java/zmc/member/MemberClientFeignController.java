package zmc.member;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberClientFeignController {
	
	@Value("${server.port}")
	private String service;
	
	
	@RequestMapping("/feign/getUserList")
	public List<String> getUserList01() {
		List<String> listUser = new ArrayList<String>();
		listUser.add("zhangsan");
		listUser.add("lisi");
		listUser.add("yushengjun");
		listUser.add(service);
		return listUser;
	}
	
	
	@RequestMapping("/feign/getUserList/param")
	public List<String> getUserList02(@RequestParam String name) {
		List<String> listUser = new ArrayList<String>();
		listUser.add(name);
		listUser.add(service);
		return listUser;
	}
	
	@RequestMapping("/feign/getUserList/{name}")
	public List<String> getUserList03(@PathVariable String name) {
		List<String> listUser = new ArrayList<String>();
		listUser.add(name);
		listUser.add(service);
		return listUser;
	}
	
	@RequestMapping("/feign/getUserList/id")
	public List<String> getUserList04(String id) {
		List<String> listUser = new ArrayList<String>();
		listUser.add(id);
		listUser.add(service);
		return listUser;
	}
	
	@RequestMapping("/feign/getUserList/entity")
	public List<Member> getUserList05(@RequestBody Member member) {
		List<Member> listUser = new ArrayList<Member>();
		listUser.add(member);
		return listUser;
	}

}
