package com.pyp.check_by_face.controller;

import com.pyp.check_by_face.domain.PO.SysManager;
import com.pyp.check_by_face.domain.PO.TeacherInfo;
import com.pyp.check_by_face.domain.VO.LoginVO;
import com.pyp.check_by_face.services.IManagerService;
import com.pyp.check_by_face.services.ITeacherService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/user")
public class UserLoginController {

    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private IManagerService managerService;

    @RequestMapping("/login.do")
    public @ResponseBody String login(@RequestParam String role, @RequestParam String tJobNumber, @RequestParam String t_pwd, HttpSession session) throws Exception {
        if ("1".equals(role)){
            TeacherInfo teacherInfo = teacherService.findTeacherById(tJobNumber);
            System.out.println(teacherInfo);
            /*
             * 1.登陆成功
             * 2.用户名不存在
             * 3.密码错误
             */
            if (null == teacherInfo){
                return "2";
            }else if (teacherInfo.getT_pwd().equals(t_pwd)){
                session.setAttribute("teacher",teacherInfo);
                System.out.println("教师："+teacherInfo+"；teacher:"+(TeacherInfo)session.getAttribute("teacher"));
                return "1";
            }else {
                return "3";
            }
        }else if ("2".equals(role)){
            SysManager sysManager = managerService.findManagerById(tJobNumber);
            System.out.println(sysManager);
            /*
             * 1.登陆成功
             * 2.用户名不存在
             * 3.密码错误
             */
            if (null == sysManager){
                return "2";
            }else if (sysManager.getS_pwd().equals(t_pwd)){
                session.setAttribute("manager",sysManager);
                return "1";
            }else {
                return "3";
            }
        }
        return "";
    }
}
