package com.pyp.filter;

import com.pyp.domain.Teacher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "TeacherPriFilter",urlPatterns = {"/pages/commit-hw-list.jsp", "/pages/aside.jsp", "/pages/header.jsp",
                                                           "/pages/homework-add.jsp","/pages/tea-home-work-list.jsp",
                                                          "/teacher/findAllHomeWorkWithPage.do","/teacher/findCommitHomeWorkWithPage.do", "/teacher/updateHomeWorkScore.do",
                                                          "/teacher/addHomeWork.do","/teacher/logout.do"})
public class TeacherPriFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("我这是被调用了吗");
        HttpServletRequest req=(HttpServletRequest)request;
        Teacher teacher=(Teacher) req.getSession().getAttribute("teacher");
        if(null==teacher){
            System.out.println("请登录后在访问");
            req.setAttribute("msg", "请登录教师账号后再访问");
            req.getRequestDispatcher("/login.jsp").forward(req, response);
            return;
        }
        System.out.println("教师："+teacher);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
