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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
	//postForLacation()会在POST请求的请求体中发送一个资源到服务器端，返回的不再是资源对象，而是创建资源的位置
	//postForLacation的其他的两个方法传参与getForObject相同
	public List<String> getOrderByUserList05(){
		MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
		param.add("firstName", "serviceName");
		param.add("secondName", "serviceValue");
 
		HttpHeaders headers = new HttpHeaders();
		/*
		Content-Type：被指定为 application/x-www-form-urlencoded；浏览器的原生 form 表单，如果不设置 enctype 属性，那么最终就会以 application/x-www-form-urlencoded 方式提交数据
		             其次，提交的数据按照 key1=val1&key2=val2 的方式进行编码，key 和 val 都进行了 URL 转码。大部分服务端语言都对这种方式有很好的支持。
		             
		multipart/form-data：我们使用表单上传文件时，必须让 form 的 enctyped 等于这个值。controller层接收对象时不能使用@RequestBody 。    
		        
		application/json：用来告诉服务端消息主体是序列化后的 JSON 字符串，JSON 格式支持比键值对复杂得多的结构化数据 ，推荐使用。        
		*/
		
		//headers.setContentType(MediaType.APPLICATION_JSON_UTF8); // 在header里面设置编码方式
		headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(param , headers);
		URI postForLocation = restTemplate.postForLocation(URI.create("http://service-member/post/member/name"), requestEntity);
		//System.out.println(postForLocation.getUserInfo());
		System.out.println(postForLocation.getHost());
		return null;
	}
	
	
//*******************************************  postForEntity  ***************************************************************************		
	//对于post方法来说，请求参数可以放到请求url里面（uriVariables），也可以放到http的body里面，当然一般来说post的数据放到body里面比较正规，
	//也比较好，因为这样数据相对不会暴露。但是有些比较简单的无关紧要的数据放到url里面传也不是不可以, 就像get那样。
	//postForEntity的其他的两个方法传参与getForObject相同
	
	public List<String> getOrderByUserList06(){
		MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
		param.add("firstName", "serviceName");
		param.add("secondName", "serviceValue");
 
		HttpHeaders headers = new HttpHeaders();
		/*
		Content-Type：被指定为 application/x-www-form-urlencoded；浏览器的原生 form 表单，如果不设置 enctype 属性，那么最终就会以 application/x-www-form-urlencoded 方式提交数据
		             其次，提交的数据按照 key1=val1&key2=val2 的方式进行编码，key 和 val 都进行了 URL 转码。大部分服务端语言都对这种方式有很好的支持。
		             
		multipart/form-data：我们使用表单上传文件时，必须让 form 的 enctyped 等于这个值。controller层接收对象时不能使用@RequestBody 。    
		        
		application/json：用来告诉服务端消息主体是序列化后的 JSON 字符串，JSON 格式支持比键值对复杂得多的结构化数据 ，推荐使用。        
		*/
		
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8); // 在header里面设置编码方式
		//headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(param , headers);
		
		//混合传值url和body一个地方放一点参数。requestEntity可以换成自定的对象。
		String url = "http://service-member/postForEntity/member/name?firstName= {first}";
		ResponseEntity<List> postForEntity = restTemplate.postForEntity(url, requestEntity, List.class, "firsthaha");
		return postForEntity.getBody();
	}
	
//*******************************************  postForObject  ***************************************************************************			
	//postForObject的其他的两个方法传参与getForObject相同
	//postForEntity与postForObject最大的区别就在于返回内容不一样： 
	//postForEntity返回的是一个ResponseEntity，而postForObject返回的就只是返回内容。postForObject的返回相当于只返回http的body部份而postForEntity的返回是返回全部信息
	public List<String> getOrderByUserList07(){
		MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
		param.add("firstName", "serviceName");
		param.add("secondName", "serviceValue");
 
		HttpHeaders headers = new HttpHeaders();	
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8); // 在header里面设置编码方式
		//headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(param , headers);
		
		//混合传值url和body一个地方放一点参数。requestEntity可以换成自定的对象。
		String url = "http://service-member/postForEntity/member/name?firstName= {first}";
		 List postForObject = restTemplate.postForObject(url, requestEntity, List.class, "firsthaha");
		return postForObject;
	}
	
//*******************************************  patch、put、delete、exchange 方法的用法  ***************************************************************************	
	/*
	1. 通过patch方法用给定的对象来更新一个资源并返回。与上面的方法一样，参数和写法都是一样的，不用多说了。强调一下的是patch操作不是幂等的，
	所以不能反复的去调用此方法来做某一个更新，另外patch方法与put方法做更新的区别需要说明一下：put是将对象丢过去，没有就创建，有就全体更新，
	面patch是只将对象的一个部份丢过去，并更新丢过去的那一部份。patch方法目前用的不多。
    
    2. delete的没什么说的，与上面的一样.
    
    3. exchange方法可以当get请求发也可以当post请求来发，甚至可以当其他的（put、patch等）请求来发。
    
	    当要查询一个List类型的bean对象的时候，之前的Class<T> responseType类型的返回就有点力不从心了，因为我们在使用Class<T> responseType的时候只能定义到List层面，
	    不知道List里面的是什么结构。使用ResponseEntity<List<UserVO>>后，我们可以在接收请求处定义一个与请求的bean数据结构一模一样的一个VO（value object），
	    例如此处是查询一个User的List，我们在发送请求处定义一个与User数据结构一模一样的UserVO来接收List<User>，这样结果就更好处理。 
	  	例如：
		    ParameterizedTypeReference<List<UserVO>> myBean = new ParameterizedTypeReference<List<UserVO>>() {};
			ResponseEntity<List<UserVO>> exchange = restTemplate.exchange("http://www.xxx.com/testSend", HttpMethod.GET, null, myBean, 90,2019);

    
      以上都是参考博客：https://blog.csdn.net/u012843361/article/details/79893638
	*/
	
}
