package com.tensquare.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;

public class WebFilter extends ZuulFilter {

    /**
     * 前置过滤器
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 优先级为0，数字越大，优先级越低
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否执行该过滤器，此处为true，说明需要过滤
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("zuul过滤器...");
        // 得到request上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        // 得到request域
        HttpServletRequest request = currentContext.getRequest();
        // 得到头信息
        String header = request.getHeader("Authorization");
        // 判断是否有头信息
        if (header != null && !"".equals(header)) {
            // 把头信息继续往下传
            currentContext.addZuulRequestHeader("Authorization", header);
        }
        return null;
    }

}
