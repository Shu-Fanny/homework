package com.pyp.check_by_face.controller;

import com.pyp.check_by_face.domain.PO.Student;
import com.pyp.check_by_face.domain.PO.TeacherInfo;
import com.pyp.check_by_face.services.IManagerService;
import com.pyp.check_by_face.services.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/manager")
public class SysmanagerController {
    @Autowired
    private IManagerService managerService;
    @Autowired
    private ITeacherService teacherService;

    /*
     *用户退出
     */
    @RequestMapping("/logout.do")
    public void logout(HttpSession session) throws Exception{
        session.removeAttribute("manager");
        session.invalidate();
    }

    @RequestMapping("/findTeachersInfo.do")
    public @ResponseBody List<TeacherInfo> findTeachersInfo() throws Exception {
        return managerService.findTeachersInfo();
    }

    @RequestMapping("/updateTeacherInfo.do")
    public void updateTeacherInfo(String t_name,String t_job_number,String department,String major) throws Exception {
        managerService.updateTeacherInfo(t_name,t_job_number,department,major);
    }

    @RequestMapping("/addTeacherInfo.do")
    public void addTeacherInfo(TeacherInfo[] teacherInfos) throws Exception {
        for (TeacherInfo teacherInfo:teacherInfos){
            managerService.addTeacherInfo(teacherInfo);
        }
    }

    @RequestMapping("/deleteTeacherInfo.do")
    public void deleteTeacherInfo(String t_job_number) throws Exception {
        managerService.deleteTeacherInfo(t_job_number);
    }


    @RequestMapping("/findStudentsInfo.do")
    public @ResponseBody List<Student> findStudentsInfo() throws Exception {
        return managerService.findStudentsInfo();
    }

    @RequestMapping("/updateStudentsInfo.do")
    public void updateStudentsInfo(String student_name,String student_id,String department,String major,String class_name) throws Exception {
        managerService.updateStudentsInfo(student_name,student_id,department,major,class_name);
    }

    @RequestMapping("/addStudentsInfo.do")
    public void addStudentsInfo(Student[] students) throws Exception {
        for (Student student:students){
            managerService.addStudentsInfo(student);
        }
    }

    @RequestMapping("/deleteStudentsInfo.do")
    public void deleteStudentsInfo(String student_id) throws Exception {
        managerService.deleteStudentsInfo(student_id);
    }

    //查找所有专业
    @RequestMapping("/findDepartment.do")
    public @ResponseBody List<String> findDepartment() throws Exception {
        List<String> list = teacherService.findAllDepartment();
        list.add(0,"请输入");
        return list;
    }

    //根据学院名找到其所有专业
    @RequestMapping("/findMajorByDepartmentName.do")
    public @ResponseBody List<String> findMajorByDepartmentName(String department) throws Exception {
        List<String> list = teacherService.findMajorByDepartmentName(department);
        list.add(0,"请输入");
        return list;
    }


    //通过专业名找到该专业所有老师
    @RequestMapping("/findTeacherByMajor.do")
    public @ResponseBody List<Student> findTeacherByMajor(String major) throws Exception {
        return teacherService.findTeacherByMajor(major);
    }

    //通过专业找到其拥有的班级
    @RequestMapping("/findAllClazzByMajor.do")
    public @ResponseBody List<String> findAllClazzByMajor(String major) throws Exception {
        List<String> list = teacherService.findAllClazzByMajor(major);
        list.add(0,"请输入");
        return list;
    }

    //通过班级名找到该班的学生
    @RequestMapping("/findStudentByClazzName.do")
    public @ResponseBody List<Student> findStudentByClazzName(String class_name) throws Exception {
        return teacherService.findStudentByClazzName(class_name);
    }

}
