package com.pyp.controller;

import com.pyp.domain.HomeWork;
import com.pyp.domain.Student;
import com.pyp.domain.Teacher;
import com.pyp.service.ITeacherService;
import com.pyp.utils.PageModel;
import com.pyp.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RequestMapping("/teacher")
@Controller
public class TeacherController {
    @Autowired
    private ITeacherService teacherService;

    /**
     * 用户登陆
     * @param teacher
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("/login.do")
    public ModelAndView login(@RequestAttribute("teacher") Teacher teacher, HttpSession session)throws Exception{
        ModelAndView mv = new ModelAndView();
        Teacher teacher01 = teacherService.findTeacherByUserName(teacher.getT_username());
        if (teacher01 == null){
            mv.addObject("msg","账号不存在！");
            mv.setViewName("forward:/login.jsp");
            return mv;
        }
        //用户界面提交的账号密码
        String username = teacher.getT_username();
        String password = teacher.getT_password();

        //数据库查询得到的账号密码
        String username01 = teacher01.getT_username();
        String password01 = teacher01.getT_password();

        //两者对比，查看登陆信息是否一致；一致则保存进session域中，并转发到首页;不同则返回到登陆界面
        if (username.equals(username01) && password.equals(password01)){
            //一个浏览器只能登陆一种角色
            session.removeAttribute("student");
            session.setAttribute("teacher",teacher01);
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
        session.removeAttribute("teacher");
        session.invalidate();
        mv.setViewName("redirect:/login.jsp");
        return mv;
    }


    /**
     * 查找发布的所有作业
     * @param num
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAllHomeWorkWithPage.do")
    public ModelAndView findAllHomeWorkWithPage(String num)throws Exception{
        ModelAndView mv = new ModelAndView();
        int curNum=Integer.parseInt(num);
        PageModel pageModel = teacherService.findAllHomeWorkWithPage(curNum);
        pageModel.setUrl("/teacher/findAllHomeWorkWithPage.do?1="+1);
        mv.setViewName("forward:/pages/tea-home-work-list.jsp");
        mv.addObject("pageModel",pageModel);
        //输出pageModel
        System.out.println("pageModel"+pageModel);
        return mv;
    }

    /**
     * 查找已经提交的作业
     * @param num
     * @param state 1：已提交，但是未审核  2：已提交，并且已批改
     * @return
     * @throws Exception
     */
    @RequestMapping("/findCommitHomeWorkWithPage.do")
    public ModelAndView findCommitHomeWorkWithPage(String num,String state)throws Exception{
        ModelAndView mv = new ModelAndView();
        int curNum=Integer.parseInt(num);
        PageModel pageModel = teacherService.findCommitHomeWorkWithPage(curNum,state);
        pageModel.setUrl("/teacher/findCommitHomeWorkWithPage.do?state="+state);
        mv.setViewName("forward:/pages/commit-hw-list.jsp");
        mv.addObject("pageModel",pageModel);
        mv.addObject("state",state);
        //输出pageModel
        System.out.println("pageModel"+pageModel);
        return mv;
    }

    /**
     * 根据学生id，作业id找到该学生作业存放记录，将学生成绩score更新
     * @param sid
     * @param hid
     * @param score
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateHomeWorkScore.do")
    public ModelAndView updateHomeWorkScore(String sid,String hid,String score)throws Exception{
        ModelAndView mv = new ModelAndView();
        teacherService.updateHomeWorkScore(sid,hid,score);
        mv.setViewName("forward:/teacher/findCommitHomeWorkWithPage.do?state=2&num=1");
        return mv;
    }

    /**
     * 教师发布作业
     * @param homeWork
     * @return
     * @throws Exception
     */
    @RequestMapping("/addHomeWork.do")
    public ModelAndView addHomeWork(HttpSession session, MultipartFile picture, HomeWork homeWork)throws Exception{
        ModelAndView mv=new ModelAndView();
        String path = session.getServletContext().getRealPath("/img/teacher/");
        UploadUtils uploadUtils = new UploadUtils();
        String fileName = uploadUtils.upload(picture,path);
        if (fileName==null) {
            mv.addObject("msg","文件上传失败，请重新上传");
            mv.setViewName("forward:/pages/homework-add.jsp");
            return mv;
        }
        //文件上传路径
        String file_path = "img"+"/teacher/"+fileName;
        homeWork.setFile_path(file_path);
        teacherService.addHomeWork(homeWork);
        mv.setViewName("forward:/teacher/findAllHomeWorkWithPage.do?num=1");
        return mv;
    }
}
