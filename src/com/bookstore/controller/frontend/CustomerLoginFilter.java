package com.bookstore.controller.frontend;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class CustomerLoginFilter implements Filter{
    private static final String[] loginRequiredURLs = {
            "/view_profile", "/edit_profile", "/update_profile"
    };

    public CustomerLoginFilter() {
    }

    public void destroy(){

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession(false);

        String path = httpServletRequest.getRequestURI().substring(httpServletRequest.getContextPath().length());
        if(path.startsWith("/admin/")){
            chain.doFilter(request, response);
            return;
        }

        boolean loggedIn = session != null && session.getAttribute("loggedCustomer") != null;


        if(!loggedIn && isLoginRequired(((HttpServletRequest) request).getContextPath())){
            String loginPage = "frontend/login.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(loginPage);
            dispatcher.forward(request, response);
        }else{
            chain.doFilter(request, response);
        }
    }

    private boolean isLoginRequired(String requestURL){
        for(String loginRequiredURL : loginRequiredURLs){
            if(requestURL.contains(loginRequiredURL)){
                return true;
            }
        }
        return false;
    }

    public void init(FilterConfig fconfig) throws ServletException{

    }

}
