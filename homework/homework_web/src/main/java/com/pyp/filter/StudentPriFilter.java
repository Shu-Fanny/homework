package com.pyp.filter;

import com.pyp.domain.Student;
import com.pyp.domain.Teacher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "StudentPriFilter",urlPatterns = {"/pages/aside.jsp", "/pages/header.jsp","/pages/commit-hw-list.jsp",
                                                          "/pages/home-work-list.jsp",
                                                          "/student/logout.do","/student/findHomeWorkByState.do", "/student/updateStuWork.do"
                                                          })
public class StudentPriFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("s我这是被调用了吗");
        HttpServletRequest req=(HttpServletRequest)request;
        Student student=(Student) req.getSession().getAttribute("student");
        if(null==student){
            System.out.println("请登录后在访问");
            req.setAttribute("msg", "请登录学生账号后再访问");
            req.getRequestDispatcher("/login.jsp").forward(req, response);
            return;
        }
        System.out.println("学生："+student);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
