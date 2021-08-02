package com.emp.test.zuul.filter;



import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class RouteFilter extends ZuulFilter {
private static Logger log = LoggerFactory.getLogger(RouteFilter.class);

	@Override
	public String filterType() {
		return "route";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}


	
	  @Override public Object run() { 
		
	  RequestContext ctx = RequestContext.getCurrentContext();
	  HttpServletRequest request = ctx.getRequest(); 
	  String header = request.getHeader("Test_auth-key");
	  
	  if ("AKTIMETRIK".equals(header) ) { 
	  try { 
		  ctx.setRouteHost(new
	  URL("http://localhost:9090/employee/getEmployeess")); } 
	  catch(MalformedURLException e) 
	  { e.printStackTrace(); } } 
	  else {
		  ctx. put("request is forbidden",HttpStatus.FORBIDDEN) ;
		  return ctx;
		  }
	  return ctx;
	  }
	 
	
	
	
	
	
	
}
