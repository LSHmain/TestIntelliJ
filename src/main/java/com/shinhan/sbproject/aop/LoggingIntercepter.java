package com.shinhan.sbproject.aop;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoggingIntercepter  implements HandlerInterceptor  {
	// Controller  
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("[PreHandle]   URI: " + request.getRequestURI());
        return true; 
    }
  
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           org.springframework.web.servlet.ModelAndView modelAndView) throws Exception {
        System.out.println("[PostHandle] viewName: " + 
            (modelAndView != null ? modelAndView.getViewName() : "null"));
    }
  
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) throws Exception {
        System.out.println("[AfterCompletion] ");
    }
}



