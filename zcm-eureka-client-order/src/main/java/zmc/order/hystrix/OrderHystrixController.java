package zmc.order.hystrix;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderHystrixController {
	
	@Autowired
	private OrderHystrixService orderHystrixService;
	
	@RequestMapping(value="/orderHystrix/getOrderByUserList")
	public List<String> getOrderHystrixByUserList(){
		return orderHystrixService.getOrderByUserList();
	}

}
