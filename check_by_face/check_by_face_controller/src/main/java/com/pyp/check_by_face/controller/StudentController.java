package com.pyp.check_by_face.controller;

import com.pyp.check_by_face.domain.PO.*;
import com.pyp.check_by_face.domain.VO.Attendance;
import com.pyp.check_by_face.domain.VO.StuCourseAndClassId;
import com.pyp.check_by_face.services.IStudentService;
import com.pyp.check_by_face.services.ITeacherService;
import com.pyp.check_by_face.utils.UploadUtils;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private IStudentService studentService;
    @Autowired
    private ITeacherService teacherService;

    @RequestMapping("/login.do")
    public @ResponseBody String login(Student student, HttpSession session) throws Exception {
        Student s = studentService.findBySid(student.getStudent_id());
        System.out.println("学生："+s);
        String pwd = student.getStudent_pwd();
        //3：用户名不存在
        if (s == null){
            return "3";
        }
        //2：登录成功，无人脸信息，跳转到录入人脸信息界面
        else if (pwd.equals(s.getStudent_pwd()) && s.getStudent_face_info() == 0){
            session.setAttribute("student",s);
            return "2";
        }
        //1、密码正确，而且有人脸信息，返回：1
        else if (pwd.equals(s.getStudent_pwd()) && s.getStudent_face_info() == 1){
            session.setAttribute("student",s);
            return "1";
        }//4：密码不正确
        else {
            return "4";
        }
    }

    /*
     *用户退出登陆
     */
    @RequestMapping("/logout.do")
    public void logout(HttpSession session) throws Exception{
        session.removeAttribute("student");
        session.invalidate();
    }

    /*
     *学生添加人脸库图片
     */
    @RequestMapping("/addStudentPicture.do")
    public @ResponseBody String addStudentPicture (HttpSession session, MultipartFile picture) throws Exception{
        //使用fileupload组件完成文件上传
        //上传路径
        Student student = (Student) session.getAttribute("student");
        String fileName;
        fileName = student.getStudent_id()+".png";

        String path = new File(System.getProperty("user.dir")).getParent()
                + File.separator + "check_by_face_controller\\src\\main\\webapp\\img\\"+student.getDepartment()+File.separator+student.getMajor()
                + File.separator+ student.getClass_name()+File.separator;
        File file = new File(path);
        //判断该文件是否存在，不存在则创建
        if (!file.exists()){
            file.mkdirs();
        }
        picture.transferTo(new File(path,fileName));
        studentService.updateStudent_face_info(student.getStudent_id());
        return "1";
    }

    /**
     * 获取用户基本资料
     */
    @RequestMapping("/findStudentBySid.do")
    public @ResponseBody Student findStudentBySid(HttpSession session){
        return (Student) session.getAttribute("student");
    }

    /**
     * 更改学生登陆密码
     */
    @RequestMapping("/updateStudentPassword.do")
    public @ResponseBody String updateStudentPassword(String student_pwd,HttpSession session){
        Student student = (Student) session.getAttribute("student");
        String student_id = student.getStudent_id();
        try {
            studentService.updateStudentPassword(student_id,student_pwd);
            return "1"; //成功
        } catch (Exception e) {
            e.printStackTrace();
            return "2"; //失败
        }
    }

    /**
     * 修改头像,暂时空着
     */
    @RequestMapping("/updateStudentIMG.do")
    public @ResponseBody String updateStudentIMG(String student_pwd,HttpSession session){
        return null;
    }

    /**
     * 查找我上的课程
     */
    @RequestMapping("/findCourseBySid.do")
    public @ResponseBody List<StuCourseAndClassId> findCourseBySid(HttpSession session) throws Exception {
        Student student= (Student) session.getAttribute("student");
        //1、先拿到class_id
        List<String> class_ids = studentService.findClassIdByStudentId(student.getStudent_id());
        //2、根据拿到的class_id遍历课程
        List<StuCourseAndClassId> stuCourseAndClassIds = new ArrayList<>();
        for (String class_id:class_ids) {
            StuCourseAndClassId courseAndClassId = new StuCourseAndClassId();
            Course course = studentService.findCourseBySid(class_id);
            courseAndClassId.setClass_id(class_id);
            courseAndClassId.setCourse_picture(course.getCourse_picture());
            courseAndClassId.setCourse_name(course.getCourse_name());
            courseAndClassId.setTeachers(course.getTeachers());
            stuCourseAndClassIds.add(courseAndClassId);
        }
        //3、将所有信息共同遍历到 StuCourseAndClassId集合中
        return stuCourseAndClassIds;
    }


    @RequestMapping("/saveClassId.do")
    public @ResponseBody String saveClassId(HttpSession session, String class_id) throws Exception {
        session.setAttribute("class_id",class_id);
        return "1";
    }


    /**
     * 查找该门课程的考勤信息
     */
    @RequestMapping("/findAttendanceBySid.do")
    public @ResponseBody Attendance findAttendanceBySid(HttpSession session) throws Exception {
        Student student= (Student) session.getAttribute("student");
        String dataName = getDataName((String) session.getAttribute("class_id"));
        return studentService.findAttendanceBySid(dataName,student.getStudent_id());
    }


    /**
     * 查找该门课程的每一条考勤信息
     */
    @RequestMapping("/findEveryAttendanceBySid.do")
    public @ResponseBody List<EveryAttendance> findEveryAttendanceBySid(HttpSession session) throws Exception {
        Student student= (Student) session.getAttribute("student");
        String dataName = getDataName((String) session.getAttribute("class_id"));
        return studentService.findEveryAttendanceBySid(dataName,student.getStudent_id());
    }

    /**
     * 根据考勤id找到记录
     * @param session
     * @param att_statu
     * @return
     */
    @RequestMapping("/findAttendanceBySidAndAid.do")
    public @ResponseBody List<EveryAttendance> findAttendanceBySidAndAid(HttpSession session,String att_statu) throws Exception {
        Student student= (Student) session.getAttribute("student");
        String dataName = getDataName((String) session.getAttribute("class_id"));
        return studentService.findAttendanceBySidAndAid(dataName,student.getStudent_id(),att_statu);
    }

    /**
     * 申请考勤变更
     */
    @RequestMapping("/addAttendanceChange.do")
    public @ResponseBody String addAttendanceChange(HttpSession session,String att_time, String att_statu, String reason, MultipartFile picture) throws Exception{
        //使用fileupload组件完成文件上传
        String fileName;
        //获取上传文件的名称
        fileName = picture.getOriginalFilename();
        String path =session.getServletContext().getRealPath("/img/updateAttendance/");
        if (picture != null){
            //上传路径
            File file = new File(path);
            if (!file.exists()){
                file.mkdir();
            }
            //获取上传文件项
            //设置文件名称为唯一值，uuid
            String uuid = UUID.randomUUID().toString().replace("-","");
            fileName = uuid+"_"+fileName;
            //完成文件上传
            System.out.println("上传的文件路径："+fileName);
            try {
                picture.transferTo(new File(path,fileName));
            } catch (IOException e) {
                System.out.println("上传文件失败！");
                return "2";
            }
        }

        Student student = (Student) session.getAttribute("student");
        Kaoqin_change kaoqin_change = new Kaoqin_change();
        kaoqin_change.setApply_time(att_time);
        kaoqin_change.setStudent_id(student.getStudent_id());
        kaoqin_change.setReason(reason);
        kaoqin_change.setSupport_material("/img/updateAttendance/"+fileName);
        kaoqin_change.setStatue_before_change(att_statu);
        kaoqin_change.setClass_id((String) session.getAttribute("class_id"));
        studentService.addAttendanceChange(kaoqin_change);
        return "1";
    }


    //获取数据库表名
    private String getDataName(String class_id){
        String class_name = teacherService.findClassNameByClassId(class_id);
        String dataName = class_id+"_"+class_name;
        return dataName;
    }
}
