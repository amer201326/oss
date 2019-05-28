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
 * Filter checks if LoginBean has loginIn property set to true. If it is not set
 * then request is being redirected to the login.xhml page.
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
        Session loginBean = (Session) ((HttpServletRequest) request).getSession().getAttribute("msession");

        // For the first application request there is no loginBean in the session so user needs to log in
        // For other requests loginBean is present but we need to check if user has logged in successfully
        
        System.out.println("url = "+((HttpServletRequest) request).getRequestURI());
        if (loginBean == null || !loginBean.login) {
            String contextPath = ((HttpServletRequest) request).getContextPath();
            ((HttpServletResponse) response).sendRedirect(contextPath + "/login/");
        } else {
//            System.out.println("lllog = "+ ((HttpServletRequest)request).getRequestURI().contains("manager") );
//            System.out.println("lll "+"jjjj");
            if (loginBean.typeAccount.compareTo("citizen") == 0) {
                if (!((HttpServletRequest) request).getRequestURI().contains("citizenn")) {
                    String contextPath = ((HttpServletRequest) request).getContextPath();
                    ((HttpServletResponse) response).sendRedirect(contextPath + "/AccessDenied.xhtml");
                }
            }else if (loginBean.typeAccount.compareTo("manager") == 0){
                if (((HttpServletRequest) request).getRequestURI().contains("citizenn")) {
                    String contextPath = ((HttpServletRequest) request).getContextPath();
                    ((HttpServletResponse) response).sendRedirect(contextPath + "/AccessDenied.xhtml");
                }
                if (((HttpServletRequest) request).getRequestURI().contains("employeePages")) {
                    String contextPath = ((HttpServletRequest) request).getContextPath();
                    ((HttpServletResponse) response).sendRedirect(contextPath + "/AccessDenied.xhtml");
                }
                
                if (((HttpServletRequest) request).getRequestURI().contains("login")) {
                    String contextPath = ((HttpServletRequest) request).getContextPath();
                    ((HttpServletResponse) response).sendRedirect(contextPath + "/manager/index.xhtml");
                }
                
                
            }else if (loginBean.typeAccount.compareTo("employee") == 0){
                if (((HttpServletRequest) request).getRequestURI().contains("citizenn")) {
                    String contextPath = ((HttpServletRequest) request).getContextPath();
                    ((HttpServletResponse) response).sendRedirect(contextPath + "/AccessDenied.xhtml");
                }
                if (((HttpServletRequest) request).getRequestURI().contains("manager/index.xhtml")) {
                    String contextPath = ((HttpServletRequest) request).getContextPath();
                    ((HttpServletResponse) response).sendRedirect(contextPath + "/AccessDenied.xhtml");
                }
                if (((HttpServletRequest) request).getRequestURI().contains("manager/managerProfile.xhtml")) {
                    String contextPath = ((HttpServletRequest) request).getContextPath();
                    ((HttpServletResponse) response).sendRedirect(contextPath + "/AccessDenied.xhtml");
                }
                if (((HttpServletRequest) request).getRequestURI().contains("managerCitizen") && !loginBean.screens[1]) {
                    String contextPath = ((HttpServletRequest) request).getContextPath();
                    ((HttpServletResponse) response).sendRedirect(contextPath + "/AccessDenied.xhtml");
                }
                if (((HttpServletRequest) request).getRequestURI().contains("managerService") && !loginBean.screens[0]) {
                    String contextPath = ((HttpServletRequest) request).getContextPath();
                    ((HttpServletResponse) response).sendRedirect(contextPath + "/AccessDenied.xhtml");
                } else if (((HttpServletRequest) request).getRequestURI().contains("managerEmployee") && !loginBean.screens[2]) {
                    String contextPath = ((HttpServletRequest) request).getContextPath();
                    ((HttpServletResponse) response).sendRedirect(contextPath + "/AccessDenied.xhtml");
                } else if (((HttpServletRequest) request).getRequestURI().contains("ShowServiceHead.xhtml") && !loginBean.employee.checkTypeHed()) {
                    String contextPath = ((HttpServletRequest) request).getContextPath();
                    ((HttpServletResponse) response).sendRedirect(contextPath + "/AccessDenied.xhtml");
                } else if (((HttpServletRequest) request).getRequestURI().contains("ShowServiceDoneHead.xhtml") && !loginBean.employee.checkTypeHed()) {
                    String contextPath = ((HttpServletRequest) request).getContextPath();
                    ((HttpServletResponse) response).sendRedirect(contextPath + "/AccessDenied.xhtml");
                }
                 else if (((HttpServletRequest) request).getRequestURI().contains("ShowService.xhtml") && loginBean.employee.checkTypeHed()) {
                    String contextPath = ((HttpServletRequest) request).getContextPath();
                    ((HttpServletResponse) response).sendRedirect(contextPath + "/AccessDenied.xhtml");
                }
                
                
                
                
                
                
                
                
            }
            
            

//            if (((HttpServletRequest) request).getRequestURI().contains("citizenn") && loginBean.citizen != null) {
//                System.out.println("citizenn");
//            } else if (((HttpServletRequest) request).getRequestURI().contains("manager") && loginBean.employee != null && loginBean.employee.checkTypeAdmin()) {
//                System.out.println("managerLogIn" + loginBean.employee.toString());
//            } else if (((HttpServletRequest) request).getRequestURI().contains("Municipality") && loginBean.employee != null && loginBean.employee.checkTypeAdmin()) {
//                System.out.println("managerLogIn" + loginBean.employee.toString());
//            } else if (((HttpServletRequest) request).getRequestURI().contains("managerCitizen") && loginBean.employee != null && loginBean.employee.checkTypeAdmin()) {
//                System.out.println("managerLogIn" + loginBean.employee.toString());
//            } else if (((HttpServletRequest) request).getRequestURI().contains("managerDepartment") && loginBean.employee != null && loginBean.employee.checkTypeAdmin()) {
//                System.out.println("managerLogIn" + loginBean.employee.toString());
//            } else if (((HttpServletRequest) request).getRequestURI().contains("managerEmployee") && loginBean.employee != null && loginBean.employee.checkTypeAdmin()) {
//                System.out.println("managerLogIn" + loginBean.employee.toString());
//            } else if (((HttpServletRequest) request).getRequestURI().contains("managerJopTitel") && loginBean.employee != null && loginBean.employee.checkTypeAdmin()) {
//                System.out.println("managerLogIn" + loginBean.employee.toString());
//            } else if (((HttpServletRequest) request).getRequestURI().contains("managerSection") && loginBean.employee != null && loginBean.employee.checkTypeAdmin()) {
//                System.out.println("managerLogIn" + loginBean.employee.toString());
//            } else if (((HttpServletRequest) request).getRequestURI().contains("managerService") && loginBean.employee != null && loginBean.employee.checkTypeAdmin()) {
//                System.out.println("managerLogIn" + loginBean.employee.toString());
//            } else if (loginBean.employee != null) {
//                System.out.println("employeePages");
//                if (((HttpServletRequest) request).getRequestURI().contains("allServices.xhtml") && !loginBean.screens[0]) {
//                    String contextPath = ((HttpServletRequest) request).getContextPath();
//                    ((HttpServletResponse) response).sendRedirect(contextPath + "/AccessDenied.xhtml");
//                } else if (((HttpServletRequest) request).getRequestURI().contains("addService.xhtml") && !loginBean.screens[0]) {
//                    String contextPath = ((HttpServletRequest) request).getContextPath();
//                    ((HttpServletResponse) response).sendRedirect(contextPath + "/AccessDenied.xhtml");
//                } else if (((HttpServletRequest) request).getRequestURI().contains("attachment.xhtml") && !loginBean.screens[0]) {
//                    String contextPath = ((HttpServletRequest) request).getContextPath();
//                    ((HttpServletResponse) response).sendRedirect(contextPath + "/AccessDenied.xhtml");
//                } else if (((HttpServletRequest) request).getRequestURI().contains("allCitizen.xhtml") && !loginBean.screens[1]) {
//                    String contextPath = ((HttpServletRequest) request).getContextPath();
//                    ((HttpServletResponse) response).sendRedirect(contextPath + "/AccessDenied.xhtml");
//                } else if (((HttpServletRequest) request).getRequestURI().contains("addCitizen.xhtml") && !loginBean.screens[1]) {
//                    String contextPath = ((HttpServletRequest) request).getContextPath();
//                    ((HttpServletResponse) response).sendRedirect(contextPath + "/AccessDenied.xhtml");
//                } else if (((HttpServletRequest) request).getRequestURI().contains("citizenRequest.xhtml") && !loginBean.screens[1]) {
//                    String contextPath = ((HttpServletRequest) request).getContextPath();
//                    ((HttpServletResponse) response).sendRedirect(contextPath + "/AccessDenied.xhtml");
//                } else if (((HttpServletRequest) request).getRequestURI().contains("allEmployees.xhtml") && !loginBean.screens[2]) {
//                    String contextPath = ((HttpServletRequest) request).getContextPath();
//                    ((HttpServletResponse) response).sendRedirect(contextPath + "/AccessDenied.xhtml");
//                } else if (((HttpServletRequest) request).getRequestURI().contains("addEmployee.xhtml") && !loginBean.screens[2]) {
//                    String contextPath = ((HttpServletRequest) request).getContextPath();
//                    ((HttpServletResponse) response).sendRedirect(contextPath + "/AccessDenied.xhtml");
//                } else if (((HttpServletRequest) request).getRequestURI().contains("ShowServiceHead.xhtml") && !loginBean.employee.checkTypeHed()) {
//                    String contextPath = ((HttpServletRequest) request).getContextPath();
//                    ((HttpServletResponse) response).sendRedirect(contextPath + "/AccessDenied.xhtml");
//                }
//            } else {
//                String contextPath = ((HttpServletRequest) request).getContextPath();
//                ((HttpServletResponse) response).sendRedirect(contextPath + "/AccessDenied.xhtml");
//            }
            chain.doFilter(request, response);

        }

    }

    public void init(FilterConfig config) throws ServletException {
        // Nothing to do here!
    }

    public void destroy() {
        // Nothing to do here!
    }

}
