package com.rui.filter;

import javax.servlet.http.HttpServletRequest;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class MyFilter extends ZuulFilter {

	public boolean shouldFilter() {
		return false;
	}
	/**
	 * how to filter
	 */
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String parameter = request.getParameter("accessToken");
		if (parameter == null) {
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			ctx.setResponseBody("{\"result\":\"accessToken is empty!\"}");
			return null;
		}
		return null;
	}

	/**
	 * the type for filter the type is 'pre'
	 */
	@Override
	public String filterType() {
		return "pre";
	}

	/**
	 * order for filter
	 */
	@Override
	public int filterOrder() {
		return 0;
	}
}
