package com.pyp.controller;


import com.pyp.domain.HomeWork;
import com.pyp.domain.StuWork;
import com.pyp.domain.Student;
import com.pyp.service.IStudentService;
import com.pyp.utils.PageModel;
import com.pyp.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    /**
     * 用户登陆
     * @param student
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("/login.do")
    public ModelAndView login(@RequestAttribute("student") Student student, HttpSession session)throws Exception{
        ModelAndView mv = new ModelAndView();
        Student student01 = studentService.findStudentByUserName(student.getS_username());
        if (student01 == null){
            mv.addObject("msg","账号不存在！");
            mv.setViewName("forward:/login.jsp");
            return mv;
        }
        //用户界面提交的账号密码
        String username = student.getS_username();
        String password = student.getS_password();

        //数据库查询得到的账号密码
        String username01 = student01.getS_username();
        String password01 = student01.getS_password();

        //两者对比，查看登陆信息是否一致；一致则保存进session域中，并转发到首页;不同则返回到登陆界面
        if (username.equals(username01) && password.equals(password01)){
            //一个浏览器只能登陆一种角色
            session.removeAttribute("teacher");
            session.setAttribute("student",student01);
            mv.setViewName("redirect:/pages/main.jsp");
            return mv;
        }else {
            mv.setViewName("forward:/login.jsp");
            mv.addObject("msg","密码不正确!");
            return mv;
        }
    }

    /*
     *用户退出
     */
    @RequestMapping("/logout.do")
    public ModelAndView logout(HttpSession session) throws Exception{
        ModelAndView mv = new ModelAndView();
        session.removeAttribute("student");
        session.invalidate();
        mv.setViewName("redirect:/login.jsp");
        return mv;
    }

    /**
     * 根据作业提交状态查询作业列表
     * @param session
     * @param state
     * @return
     * @throws Exception
     */
    @RequestMapping("/findHomeWorkByState.do")
    public ModelAndView findHomeWorkByState(HttpSession session,String num,String state)throws Exception{
        System.out.println("请求参数"+num+"+"+state);
        ModelAndView mv = new ModelAndView();
        int curNum=Integer.parseInt(num);
        Student student = (Student) session.getAttribute("student");
        PageModel pageModel = studentService.findHomeWorkByState(student.getSid(),state,curNum);
        pageModel.setUrl("/student/findHomeWorkByState.do?state="+state);
        mv.setViewName("forward:/pages/home-work-list.jsp");
        mv.addObject("pageModel",pageModel);
        //通过这个状态位让已提交的作业和已完成作业没有上传作业按钮
        mv.addObject("state",state);
        //输出pageModel
        System.out.println("pageModel"+pageModel);
        return mv;
    }


    /**
     * 学生上传作业
     * 流程执行完毕后，作业状态更改为1--已提交状态，路径为文件路径
     * @param session
     * @param picture
     * @param sid  学生id
     * @param hid 作业id
     * @return 执行完毕之后将跳转到已提交作业列表中
     * @throws Exception
     */
    @RequestMapping("/updateStuWork.do")
    public ModelAndView updateStuWork(HttpSession session, MultipartFile picture, String sid,String hid) throws Exception {
        ModelAndView mv=new ModelAndView();
        String path = session.getServletContext().getRealPath("/img/homework/");
        UploadUtils uploadUtils = new UploadUtils();
        String fileName = uploadUtils.upload(picture,path);
        if (fileName==null) {
            mv.addObject("msg","文件上传失败，请重新上传");
            mv.setViewName("forward:/student/findHomeWorkByState.do?num=1&state=0");
            return mv;
        }
        //文件上传路径
        String file_path = "img"+"/homework/"+fileName;
        studentService.updateStuWork(sid,hid,file_path);
        //获取学生id
        mv.setViewName("forward:/student/findHomeWorkByState.do?num=1&state=1");
        return mv;
    }
}
