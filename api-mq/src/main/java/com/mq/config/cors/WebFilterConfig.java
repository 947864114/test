package com.mq.config.cors;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Description:  跨域过滤器
 * <p>
 * Created by Lqz
 * DATE: 2018/7/23 0023
 */
@Configuration
public class WebFilterConfig implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        httpResponse.setHeader("X-Frame-Options", "SAMEORIGIN");
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Methods", "*");
        httpResponse.setHeader("Access-Control-Allow-Headers",
                Joiner.on(",").join(Lists.newArrayList(
                        "Content-Type",
                        "Accept",
                        "X-File-Name",
                        "X-Frame-Options",
                        "content-disposition",
                        "x-www-form-urlencoded",
                        "baby_token",
                        "customer_id"
                )));
        httpResponse.setHeader("Cache-Control", "no-cache, must-revalidate");
        httpResponse.setHeader("Pragma", "no-cache");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }


}
