package zcm.zuul;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * 服务过滤
 *
 */
@Component
public class MyZuulFilter extends ZuulFilter{
	private static Logger log = LoggerFactory.getLogger(MyZuulFilter.class);

	@Override
	public boolean shouldFilter() {
		/*
			返回一个boolean类型来判断该过滤器是否要执行。我们可以通过此方法来指定过滤器的有效范围。
		*/
		//此处要返回true，否则返回值不显示
		return true;
	}

	//此方法判断访问路径中是否传递参数token及其是否为空值
	//访问方式：http://localhost:8769/api-member/getUserList?token=123
	@Override
	public Object run() {
		/*
			过滤器的具体逻辑。在该函数中，我们可以实现自定义的过滤逻辑，来确定是否要拦截当前的请求，
			不对其进行后续的路由，或是在请求路由返回结果之后，对处理结果做一些加工等。
		*/
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
		Object accessToken = request.getParameter("token");
		if (accessToken != null) {
			ctx.setSendZuulResponse(true);//会进行路由，也就是会调用api服务提供者        	
			ctx.setResponseStatusCode(200);        	
			ctx.set("isOK",true);//可以把一些值放到ctx中，便于后面的filter获取使用
			return null;
		}
		log.warn("token is empty");
		ctx.setSendZuulResponse(false);
		ctx.setResponseStatusCode(401);
		try {
			ctx.getResponse().getWriter().write("token is empty");
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public String filterType() {
        /* 
			filterType：该函数需要返回一个字符串来代表过滤器的类型，而这个类型就是在HTTP请求过程中定义的各个阶段。在Zuul中默认定义了四种不同生命周期的过滤器类型，具体如下：
			pre：可以在请求被路由之前调用。
			routing：在路由请求时候被调用。
			post：在routing和error过滤器之后被调用。
			error：处理请求时发生错误时被调用。
		*/
		return "pre";
	}

	@Override
	public int filterOrder() {
		/*
			通过int值来定义过滤器的执行顺序，数值越小优先级越高。
		*/
		return 0;
	}

}
