package com.jfeat.am.modular.wechat.config;

import com.jfinal.core.JFinalFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by jackyhuang on 2017/7/3.
 */
public class JfinalWeixinFilter implements Filter {

    JFinalFilter jFinalFilter = new JFinalFilter();

    public void init(FilterConfig filterConfig) throws ServletException {
        jFinalFilter.init(filterConfig);
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;

        String uri = request.getRequestURI();
        if (uri.startsWith("/wx/"))
            jFinalFilter.doFilter(req, res, chain);
        else
            chain.doFilter(req, res);
    }

    public void destroy() {
        jFinalFilter.destroy();
    }

}
