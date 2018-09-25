package zmc.order;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	RestTemplate restTemplate;  //一个类似于httpClient的框架，在启动类中注入

//*******************************************  getForObject   ***************************************************************************
	
	//getForObject的第一种获取数据的方法：不带参数
	public List<String> getOrderByUserList(){
		return restTemplate.getForObject("http://service-member/getUserList", List.class);
	}
	
	//getForObject的第二种获取数据的方法：带参数之http://service-member/member/{firstName}/name/{secondName}形式,也可以用第三中方式请求
	public List<String> getOrderByUserList01(){
		//可以用一个字符串做占位符，这是一个可变长度的参数，来一一替换前面的占位符
		String url = "http://service-member/member/{firstName}/name/{secondName}";
		return restTemplate.getForObject(url, List.class,"first","second");
	}
	
	//getForObject的第三种获取数据的方法：带参数之http://service-member/member/name形式
	public List<String> getOrderByUserList02(){
		//构造参数,map的key即为前边占位符的名字，map的value为参数值
		Map<String,Object> paraMap = new HashMap<>();
		paraMap.put("firstName", "first");
		paraMap.put("secondName", "second");
		
		String url = "http://service-member/member/name?firstName= {firstName}&secondName= {secondName}";
		return restTemplate.getForObject(url, List.class,paraMap);  
	}
	
	//getForObject的第三种获取数据的方法：带参数之uri形式
	public List<String> getOrderByUserList03(){
		String url = "http://service-member/member/name?firstName= {firstName}&secondName= {secondName}";
		UriComponents uriComponents = UriComponentsBuilder.fromUriString(url).build().expand("first","second").encode();
	    URI uri = uriComponents.toUri();
		return restTemplate.getForObject(uri, List.class);  
	}
	
	
//*******************************************  getForEntity   ***************************************************************************	
	//getForEntity与getForObject最大的区别就在于返回内容不一样： 
	//getForEntity返回的是一个ResponseEntity，而getForObject返回的就只是返回内容。getForObject的返回相当于只返回http的body部份而getForEntity的返回是返回全部信息：
	//getForEntity的其他的两个方法传参与getForObject相同
	public List<String> getOrderByUserList04(){
		String url = "http://service-member/member/{firstName}/name/{secondName}";
		ResponseEntity<List> forEntity = restTemplate.getForEntity(url, List.class,"first","second");
		
		HttpHeaders headers = forEntity.getHeaders();
		HttpStatus statusCode = forEntity.getStatusCode();
		int statusCodeValue = forEntity.getStatusCodeValue();
		Class<? extends ResponseEntity> class1 = forEntity.getClass();
		List body = forEntity.getBody();
		
		StringBuffer result = new StringBuffer();
		 result.append("responseEntity.getBody()：").append(body).append("<hr>")
         .append("responseEntity.getStatusCode()：").append(statusCode).append("<hr>")
         .append("responseEntity.getClass()：").append(class1).append("<hr>")
         .append("responseEntity.getStatusCodeValue()：").append(statusCodeValue).append("<hr>")
         .append("responseEntity.getHeaders()：").append(headers).append("<hr>");
		 System.out.println(result.toString());
		 
		return restTemplate.getForEntity(url, List.class,"first","second").getBody();
	}
	
	
	
//*******************************************  postForLocation : 此处有问题  ***************************************************************************		
	public List<String> getOrderByUserList05(){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8); // 在header里面设置编码方式
		String firstName = "xiao ming";
		HttpEntity<String> requestEntity = new HttpEntity<String>(firstName , headers);
		URI postForLocation = restTemplate.postForLocation(URI.create("http://service-member/member/name"), requestEntity);
		System.out.println(postForLocation.getUserInfo());
		return null;
	}
	
	
	
	
	
	
	

}
