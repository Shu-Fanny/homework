package com.pyp.controller;

import com.pyp.domain.Student;
import com.pyp.domain.Teacher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/login.do")
    public ModelAndView login(String username,String password,String role)throws Exception{
        ModelAndView mv = new ModelAndView();
        Student student;
        Teacher teacher;
        if (role.equals("student")){
            //如果角色是student，实例化student并填充值
            student = new Student();
            student.setS_username(username);
            student.setS_password(password);
            mv.setViewName("forward:/student/login.do");
            mv.addObject("student",student);
        }else if (role.equals("teacher")){
            //如果角色是teacher，实例化teacher并填充值
            teacher = new Teacher();
            teacher.setT_username(username);
            teacher.setT_password(password);
            mv.setViewName("forward:/teacher/login.do");
            mv.addObject("teacher",teacher);
        }else {
            //否则，跳转回登陆界面
            mv.addObject("msg","请重新填写登陆信息！");
            mv.setViewName("forward:/login.jsp");
        }
        return mv;
    }
}
