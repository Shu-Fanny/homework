package com.pyp.check_by_face.controller;

import com.pyp.check_by_face.domain.PO.SysManager;
import com.pyp.check_by_face.domain.PO.TeacherInfo;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

//@WebFilter(filterName = "PrivilegeFilter",urlPatterns = {"/jsp/AttendanceManagement.html",
//                                                         "/jsp/basics.html",
//                                                         "/jsp/changeHeadImg.html",
//                                                         "/jsp/changePwd.html",
//                                                         "/jsp/classManagement.html",
//                                                         "/jsp/AttendanceManagement",
//                                                         "/jsp/CourseDetails.html",
//                                                         "/jsp/CreateCourse.html",
//                                                         "/jsp/homePage.html",
//                                                         "/jsp/index.html",
//                                                         "/jsp/ModifyAttendance.html"})
public class PrivilegeFilter implements Filter {

    public void destroy() {
    }
    public void init(FilterConfig fConfig) throws ServletException {
    }
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("我这是被调用了吗");
        HttpServletRequest req=(HttpServletRequest)request;
        TeacherInfo uu=(TeacherInfo) req.getSession().getAttribute("teacher");
        SysManager manager = (SysManager) req.getSession().getAttribute("manager");
        if(null==uu || null==manager){
            System.out.println("请登录后在访问");
            req.setAttribute("msg", "请登录后在访问");
            req.getRequestDispatcher("/jsp/register.html").forward(req, response);
            return;
        }
        System.out.println("教师1111"+uu);
        System.out.println("管理员111"+manager);
        chain.doFilter(request, response);
    }
}
