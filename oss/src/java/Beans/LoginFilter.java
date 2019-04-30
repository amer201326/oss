/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.IOException;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

 
/**
 * Filter checks if LoginBean has loginIn property set to true.
 * If it is not set then request is being redirected to the login.xhml page.
 * 
 * @author itcuties
 *
 */
public class LoginFilter implements Filter {
 
    /**
     * Checks if user is logged in. If not it redirects to the login.xhtml page.
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Get the loginBean from session attribute
        Session loginBean = (Session)((HttpServletRequest)request).getSession().getAttribute("msession");
         
        // For the first application request there is no loginBean in the session so user needs to log in
        // For other requests loginBean is present but we need to check if user has logged in successfully
        if (loginBean == null || !loginBean.login) {
            String contextPath = ((HttpServletRequest)request).getContextPath();
            ((HttpServletResponse)response).sendRedirect(contextPath + "/login/");
        }else{
//            System.out.println("lllog = "+ ((HttpServletRequest)request).getRequestURI().contains("manager") );
//            System.out.println("lll "+"jjjj");
         if(((HttpServletRequest)request).getRequestURI().contains("manager") && loginBean.manager != null){
             System.out.println("manager");
         }else if(((HttpServletRequest)request).getRequestURI().contains("citizenn") && loginBean.citizen != null){
             System.out.println("citizenn");
         }else if(((HttpServletRequest)request).getRequestURI().contains("employeePages") && loginBean.employee != null){
             System.out.println("employeePages");
             if( ((HttpServletRequest)request).getRequestURI().contains("allServices.xhtml") && !loginBean.screens[0]){
                   String contextPath = ((HttpServletRequest)request).getContextPath();
              ((HttpServletResponse)response).sendRedirect(contextPath + "/AccessDenied.xhtml");
             }else if( ((HttpServletRequest)request).getRequestURI().contains("addService.xhtml") && !loginBean.screens[0]){
                   String contextPath = ((HttpServletRequest)request).getContextPath();
              ((HttpServletResponse)response).sendRedirect(contextPath + "/AccessDenied.xhtml");
             }else if( ((HttpServletRequest)request).getRequestURI().contains("attachment.xhtml") && !loginBean.screens[0]){
                   String contextPath = ((HttpServletRequest)request).getContextPath();
              ((HttpServletResponse)response).sendRedirect(contextPath + "/AccessDenied.xhtml");
             }else if( ((HttpServletRequest)request).getRequestURI().contains("allCitizen.xhtml") && !loginBean.screens[1]){
                   String contextPath = ((HttpServletRequest)request).getContextPath();
              ((HttpServletResponse)response).sendRedirect(contextPath + "/AccessDenied.xhtml");
             }else if( ((HttpServletRequest)request).getRequestURI().contains("addCitizen.xhtml") && !loginBean.screens[1]){
                   String contextPath = ((HttpServletRequest)request).getContextPath();
              ((HttpServletResponse)response).sendRedirect(contextPath + "/AccessDenied.xhtml");
             }else if( ((HttpServletRequest)request).getRequestURI().contains("citizenRequest.xhtml") && !loginBean.screens[1]){
                   String contextPath = ((HttpServletRequest)request).getContextPath();
              ((HttpServletResponse)response).sendRedirect(contextPath + "/AccessDenied.xhtml");
             }else if( ((HttpServletRequest)request).getRequestURI().contains("allEmployees.xhtml") && !loginBean.screens[2]){
                   String contextPath = ((HttpServletRequest)request).getContextPath();
              ((HttpServletResponse)response).sendRedirect(contextPath + "/AccessDenied.xhtml");
             }else if( ((HttpServletRequest)request).getRequestURI().contains("addEmployee.xhtml") && !loginBean.screens[2]){
                   String contextPath = ((HttpServletRequest)request).getContextPath();
              ((HttpServletResponse)response).sendRedirect(contextPath + "/AccessDenied.xhtml");
             }
         }else{
              String contextPath = ((HttpServletRequest)request).getContextPath();
              ((HttpServletResponse)response).sendRedirect(contextPath + "/AccessDenied.xhtml");
         }
            
        }
         
        chain.doFilter(request, response);
             
    }
 
    public void init(FilterConfig config) throws ServletException {
        // Nothing to do here!
    }
 
    public void destroy() {
        // Nothing to do here!
    }   
     
}