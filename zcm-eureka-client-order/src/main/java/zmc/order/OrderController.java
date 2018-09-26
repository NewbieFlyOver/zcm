package zmc.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.net.SyslogOutputStream;

@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	
	@RequestMapping(value="/order/getOrderByUserList")
	public List<String> getOrderByUserList(){
		System.out.println("99");
		return orderService.getOrderByUserList();
	}
	
	
	@RequestMapping(value="/order/getOrderByUserList01")
	public List<String> getOrderByUserList01(){
		return orderService.getOrderByUserList01();
	}
	
	
	@RequestMapping(value="/order/getOrderByUserList02")
	public List<String> getOrderByUserList02(){
		return orderService.getOrderByUserList02();
	}
	
	@RequestMapping(value="/order/getOrderByUserList03")
	public List<String> getOrderByUserList03(){
		return orderService.getOrderByUserList03();
	}
	
	@RequestMapping(value="/order/getOrderByUserList04")
	public List<String> getOrderByUserList04(){
		return orderService.getOrderByUserList04();
	}
	
	@RequestMapping(value="/order/getOrderByUserList05")
	public List<String> getOrderByUserList05(){
		return orderService.getOrderByUserList05();
	}
	
	@RequestMapping(value="/order/getOrderByUserList06")
	public List<String> getOrderByUserList06(){
		return orderService.getOrderByUserList06();
	}
	
	@RequestMapping(value="/order/getOrderByUserList07")
	public List<String> getOrderByUserList07(){
		return orderService.getOrderByUserList07();
	}
}
