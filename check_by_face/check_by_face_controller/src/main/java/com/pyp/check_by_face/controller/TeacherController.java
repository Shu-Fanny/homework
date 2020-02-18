package com.pyp.check_by_face.controller;

import com.pyp.check_by_face.domain.PO.*;
import com.pyp.check_by_face.domain.VO.Attendance;
import com.pyp.check_by_face.domain.VO.ClassOfStudentInfo;
import com.pyp.check_by_face.services.IStudentService;
import com.pyp.check_by_face.services.ITeacherService;
import com.pyp.check_by_face.utils.FaceMatch.company.FaceMatchUtils;
import com.pyp.check_by_face.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private ITeacherService teacherService;
    @Autowired
    private IStudentService studentService;

    /*
     *用户退出
     */
    @RequestMapping("/logout.do")
    public void logout(HttpSession session) throws Exception{
        session.removeAttribute("teacher");
        session.invalidate();
    }

    /*
     *修改个人基本资料
     */
    @RequestMapping("/updateTeacherDetails.do")
    public @ResponseBody String updateTeacherDetails(HttpSession session,TeacherInfo teacherInfo)throws Exception{
        TeacherInfo teacher = (TeacherInfo) session.getAttribute("teacher");
        teacher.setT_email(teacherInfo.getT_email()); //重新设置教师邮箱
        teacher.setT_tel(teacherInfo.getT_tel()); //重新设置教师电话
        teacher.setT_sex(teacherInfo.getT_sex()); //重新设置教师性别
        teacher.setT_job_number(teacherInfo.getT_job_number()); //重新设置教师工号
        //调用业务层修改教师信息
        teacherService.updateTeacherDetails(teacher);
        return "1";
    }

    /**
     * 修改当前教师登陆的头像
     * @param session 获取登陆的教师账号
     * @param img 教师上传的头像名称
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateTeacherImage.do")
    public @ResponseBody String updateTeacherImage(HttpSession session, MultipartFile img)throws Exception{
        TeacherInfo teacher = (TeacherInfo) session.getAttribute("teacher");
        String fileName;
        String path = session.getServletContext().getRealPath("/img/t_head_image/");
        fileName = new UploadUtils().upload(img,path);
        //完成文件上传
        teacher.setT_head_image("img"+"/t_head_image/"+fileName);
        teacherService.updateTeacherImage(teacher);
        return "1";
    }

    /**
     * 修改密码
     * @param session
     * @param t_pwd
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateTeacherPwd.do")
    public @ResponseBody String updateTeacherPwd(HttpSession session,String t_pwd)throws Exception{
        TeacherInfo teacher = (TeacherInfo) session.getAttribute("teacher");
        teacher.setT_pwd(t_pwd); //重新设置教师工号
        //调用业务层修改教师信息
        teacherService.updateTeacherPwd(teacher);
        return "1";
    }

    /*
     *跳转到添加课程界面
     */
    @RequestMapping("/jumpToCreateCourseUI.do")
    public @ResponseBody ModelAndView jumpToCreateCourseUI() throws Exception{
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/jsp/homePage.html");
        return mv;
    }

    /*
     *教师添加课程
     */
    @RequestMapping("/addCourseToTeacher.do")
    public @ResponseBody String addCourseToTeacher(HttpSession session, MultipartFile coursepicture, Course course) throws Exception{
        //使用fileupload组件完成文件上传
        //上传路径
        String fileName;
        if (coursepicture != null){
            String path = session.getServletContext().getRealPath("/img/coursepicture/");
            fileName = new UploadUtils().upload(coursepicture,path);
            //完成文件上传
            course.setCourse_picture("img"+"/coursepicture/"+fileName);
        }else {
            course.setCourse_picture("img/coursepicture/95c505efc1fb4c26a4b749303c122302_1.jpg");
        }
        //获取登陆的教师信息
        TeacherInfo teacherInfo = (TeacherInfo) session.getAttribute("teacher");
        //创建课程对象，将数据封装到课程类中，用来加入数据库
        course.setT_job_number(teacherInfo.getT_job_number());
        System.out.println(course);
        teacherService.addCourseToTeacher(course);
        return "1";
    }


    /*
     *查询教师授课信息
     */
    @RequestMapping("/findCourseByTid.do")
    public @ResponseBody List<Course> findCourseByTid(HttpSession session) throws Exception {
        TeacherInfo teacher = (TeacherInfo) session.getAttribute("teacher");
        System.out.println("teacher:"+teacher);
        List<Course> courses = teacherService.findCourseByTid(teacher.getT_job_number());
        return courses;
    }

    /*
     *查询教师基本资料
     */
    @RequestMapping("/findTeacherInfo.do")
    public @ResponseBody TeacherInfo findTeacherInfo(HttpSession session) throws Exception {
        TeacherInfo teacher = (TeacherInfo) session.getAttribute("teacher");
        return teacher;
    }

    /**
     * 点击所选课程，将所选课程的course_id传到后端，后台进行跳转指定界面
     * @param session
     * @param course_id
     * @return
     */
    @RequestMapping("/jumpToCourse.do")
    public String jumpToCourse(HttpSession session,String course_id){
        session.setAttribute("course_id",course_id);
        return "redirect:/jsp/CourseDetails.html";
    }

    /**
     * 通过某门课程的course_id找到其授课班级
     * @param session 取出course_id的值
     */
    @RequestMapping("/findClassByCourseId.do")
    public @ResponseBody List<ClassInfo> findClassByCourseId(HttpSession session) throws Exception {
        List<ClassInfo> classInfos = teacherService.findClassByCourseId((String) session.getAttribute("course_id"));
        return classInfos;
    }

    /*
     *教师为某门课程添加班级,同时为这个班级添加考勤表
     * @Param:courseId  遍历教师授课信息的时候已有，放置在hidden域中，等待客户端点击发送过来
     * @Param:className  教师添加班级时的课程名称
     */
    @RequestMapping("/addClassOfCourse.do")
    public @ResponseBody String addClassOfCourse(HttpSession session,String className){
        try {
            teacherService.addClassOfCourse((String) session.getAttribute("course_id"),className);
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "2:";
        }
    }


    /**
     * 通过某门课程授课班级的ID找到该班级下的学生
     */
    @RequestMapping("/findStudentByClassId.do")
    public @ResponseBody List<ClassOfStudentInfo> findStudentByClassId(String class_id,HttpSession session) throws Exception {
        session.setAttribute("class_id",class_id);
        List<ClassOfStudentInfo> students = teacherService.findStudentByClassId(class_id);
        return students;
    }



    /**
     * 添加学生
     * @param class_id 教师为某门课程下的某个班级id导入学生名单
     * @param student_id 学生学号id
     * @return 1：添加成功  2：添加失败
     */
    @RequestMapping("/addStudentToClassOfCourse.do")
    public @ResponseBody String addStudentToClassOfCourse(String class_id,String[] student_id) {
        Date date = new Date();
        int i=0;
        for(String sid:student_id){
            SelectCourse selectCourse = new SelectCourse();
            selectCourse.setClass_id(class_id);
            selectCourse.setJoin_time(new SimpleDateFormat("yyyy-MM-dd").format(date));
            selectCourse.setStudent_id(sid);
            try {
                teacherService.addStudentToClassOfCourse(selectCourse);
                i++;
            } catch (Exception e) {
                e.printStackTrace();
                return "2";
            }
        }
        return "1";
    }

    /**
     * 寻找某门课程的某门班级的考勤表
     * @param session
     * @return
     */
    @RequestMapping("/findAttendancesByClassIdAndCourseId.do")
    public @ResponseBody List<Attendance>  findAttendancesByClassIdAndCourseId(HttpSession session) throws Exception {
        String course_id = (String) session.getAttribute("course_id");
        String class_id = (String) session.getAttribute("class_id");
        List<Attendance> attendances = teacherService.findAttendancesByClassIdAndCourseId(course_id,class_id);
        return attendances;
    }

    /**
     * 查询某个同学在该班级的详细考勤记录
     * @param session
     * @param student_id
     * @return
     */
    @RequestMapping("/findAttendanceBySid.do")
    public @ResponseBody List<EveryAttendance> findAttendanceBySid(HttpSession session,String student_id) throws Exception {
        Student student= (Student) session.getAttribute("student");
        String dataName = getDataName((String) session.getAttribute("class_id"));
        return studentService.findEveryAttendanceBySid(dataName,student_id);
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


    /*
     *教师考勤
     */
    //@RequestMapping("/addAttendanceToClassStudents.do")
    //public void addAttendanceToClassStudents(HttpSession session, MultipartFile picture, String class_id) throws Exception{
    //    //使用fileupload组件完成文件上传
    //    String fileName;
    //    String path =session.getServletContext().getRealPath("/img/compare/");
    //    if (picture != null){
    //        fileName = new UploadUtils().upload(picture,path);
    //        if (fileName==null){
    //            return;
    //        }
    //    }
    //
    //    String attendanceTimeKey = class_id+"_attendance_time";
    //    //考勤时间
    //    Date date = null;
    //    //图片路径map集合<sid,path>
    //    String picturesPathKey = "picturesPath";
    //    if (session.getAttribute(attendanceTimeKey)==null ){
    //        date = new Date();
    //        session.setAttribute(attendanceTimeKey,date);
    //    }else {
    //        date = (Date) session.getAttribute(attendanceTimeKey);
    //    }
//
    //    String class_name = teacherService.findClassNameByClassId(class_id);
    //    String dataTableName = class_id+"_"+class_name;
    //    //学生集合
    //    List<ClassOfStudentInfo> studentInfos;
    //    //学生学号集合
    //    //List<String> student_ids = null;
    //    if (session.getAttribute("studentInfos") == null){
    //        /**
    //         *向该班级学生的考勤表中插入本次考勤记录
    //         *studentInfos：服务器获取学生id
    //         * dataTableName：插入的考勤表名称
    //         * data：考勤时间
    //         */
    //        studentInfos = teacherService.findStudentByClassId(class_id);
//
    //        teacherService.addAttendanceToClassStudents(studentInfos,dataTableName,new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date),"旷课");
    //        session.setAttribute("studentInfos",studentInfos);
    //    }else {
    //        studentInfos = (List<ClassOfStudentInfo>) session.getAttribute("studentInfos");
    //    }
//
    //    Map<String,String> map = new HashMap<>();
    //    String studentPicturePath;
    //    if (map == null){
    //        for (ClassOfStudentInfo studentInfo:studentInfos){
    //            studentPicturePath = new File(System.getProperty("user.dir")).getParent()
    //                    + File.separator + "check_by_face_controller\\src\\main\\webapp\\img\\"+studentInfo.getDepartment()+File.separator+studentInfo.getMajor()
    //                    + File.separator+ studentInfo.getClass_name()+File.separator+studentInfo.getStudent_id() + ".png" ;
    //            map.put(studentInfo.getStudent_id(),studentPicturePath);
    //            System.out.println("studentPicturePath:"+studentPicturePath);
    //        }
    //        session.setAttribute("map",map);
    //    }else {
//
    //    }
//
    //    Date nowDate = new Date();
    //    FaceMatchUtils faceMatchUtils = new FaceMatchUtils();
    //    if (nowDate.getTime()-date.getTime()<=1000L*1200L){
    //        for (ClassOfStudentInfo studentInfo:studentInfos){
    //            studentPicturePath = new File(System.getProperty("user.dir")).getParent()
    //                    + File.separator + "check_by_face_controller\\src\\main\\webapp\\img\\"+studentInfo.getDepartment()+File.separator+studentInfo.getMajor()
    //                    + File.separator+ studentInfo.getClass_name()+File.separator+studentInfo.getStudent_id() + ".png" ;
    //            String imgPath = map.get(studentInfo.getStudent_id());
    //            if (faceMatchUtils.comparePicture(studentPicturePath,path+File.separator+fileName)){
    //                //更新学生考勤
    //                teacherService.updateAttendanceToClassStudent(dataTableName,studentInfo.getStudent_id(),new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date),"出勤");
    //                //map.remove(studentInfo.getStudent_id());
    //            }
    //        }
    //    }else if (nowDate.getTime()-date.getTime()>1000L*1200L && nowDate.getTime()-date.getTime()<=1000L*3600L){
    //        for (ClassOfStudentInfo studentInfo:studentInfos){
    //            String imgPath = map.get(studentInfo.getStudent_id());
    //            studentPicturePath = new File(System.getProperty("user.dir")).getParent()
    //                    + File.separator + "check_by_face_controller\\src\\main\\webapp\\img\\"+studentInfo.getDepartment()+File.separator+studentInfo.getMajor()
    //                    + File.separator+ studentInfo.getClass_name()+File.separator+studentInfo.getStudent_id() + ".png" ;
    //            if (faceMatchUtils.comparePicture(studentPicturePath,path+File.separator+fileName)){
    //                //更新学生考勤
    //                teacherService.updateAttendanceToClassStudent(dataTableName,studentInfo.getStudent_id(),new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date),"迟到");
    //                //map.remove(studentInfo.getStudent_id());
    //            }
    //        }
    //    }
    //}

    /**
     * 开始考勤
     * @param state 教师点击考勤，状态位为1，开始考勤；否则，不保存摄像头发送的照片即不执行考勤操作
     * @param session
     */
    @RequestMapping("/startAttendance.do")
    public void startAttendance(String state,HttpSession session){
        String s = (String) session.getAttribute("state");
        if (s == null){
            session.setAttribute("state",state);
        }
    }

    /**
     * 为该班级的学生添加考勤记录
     * @param session
     * @throws Exception

    @RequestMapping("/addAttendanceToClassStudents.do")
    public void addAttendanceToClassStudents(HttpSession session,MultipartFile picture) throws Exception{
        String state = (String) session.getAttribute("state");
        if (!("1".equals(state))){
            return;
        }
        String class_id = (String) session.getAttribute("class_id");
        //使用fileupload组件完成文件上传
        //String fileName;
        //获取上传文件的名称
        //fileName = picture.getOriginalFilename();
        //String path =session.getServletContext().getRealPath("/img/compare/");
        //if (picture != null){
        //    //上传路径
        //    File file = new File(path);
        //    if (!file.exists()){
        //        file.mkdir();
        //    }
        //    //获取上传文件项
        //    //设置文件名称为唯一值，uuid
        //    String uuid = UUID.randomUUID().toString().replace("-","");
        //    fileName = uuid+"_"+fileName;
        //    //完成文件上传
        //    System.out.println("上传的文件路径："+fileName);
        //    try {
        //        picture.transferTo(new File(path,fileName));
        //    } catch (IOException e) {
        //        System.out.println("上传文件失败！");
        //    }
        //}
        //数据课表名
        String dataTableName = getDataName(class_id);
        //需要考勤的学生集合
        List<ClassOfStudentInfo> studentInfos = teacherService.findStudentByClassId(class_id);
        //获取时间
        String attendanceTimeKey = class_id+"_attendance_time";
        //考勤时间
        Date date = null;
        if (session.getAttribute(attendanceTimeKey)==null ){
            date = new Date();
            session.setAttribute(attendanceTimeKey,date);
            //将获取的学生id在对应的考勤表中加入考勤信息
            teacherService.addAttendanceToClassStudents(studentInfos,dataTableName,new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date),"旷课");
        }else {
            date = (Date) session.getAttribute(attendanceTimeKey);
        }


        Date nowDate = new Date();
        FaceMatchUtils faceMatchUtils = new FaceMatchUtils();

        if (nowDate.getTime()-date.getTime()<=1000L*1200L){
            for (ClassOfStudentInfo studentInfo:studentInfos){
                String sid = teacherService.findSidByDateAndStatu(dataTableName,"旷课",new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date),studentInfo.getStudent_id());
                System.out.println("符合条件的sid:"+sid+"集合中的sid"+studentInfo.getStudent_id());
                if (sid==null || !sid.equals(studentInfo.getStudent_id())){
                    continue;
                }
                //学生库图片路径
                //String studentPicturePath = new File(System.getProperty("user.dir")).getParent()
                //        + File.separator + "check_by_face_controller\\src\\main\\webapp\\img\\"+studentInfo.getDepartment()+File.separator+studentInfo.getMajor()
                //        + File.separator+ studentInfo.getClass_name()+File.separator+studentInfo.getStudent_id() + ".png" ;
                //如果人脸库有信息，更新学生考勤
                if (true){
                    //System.out.println("studentPicturePath:"+studentPicturePath+"；\nnowPath:"+path+File.separator+fileName);
                    teacherService.updateAttendanceToClassStudent(dataTableName,studentInfo.getStudent_id(),new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date),"出勤");
                    break;
                }
            }
        }else if (nowDate.getTime()-date.getTime()>1000L*1200L && nowDate.getTime()-date.getTime()<=1000L*3600L){
            for (ClassOfStudentInfo studentInfo:studentInfos){
                String sid = teacherService.findSidByDateAndStatu(dataTableName,"旷课",new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date),studentInfo.getStudent_id());
                System.out.println("符合条件的sid:"+sid+"集合中的sid"+studentInfo.getStudent_id());
                if (!sid.equals(studentInfo.getStudent_id())){
                    continue;
                }
                //学生库图片路径
                //String studentPicturePath = new File(System.getProperty("user.dir")).getParent()
                //        + File.separator + "check_by_face_controller\\src\\main\\webapp\\img\\"+studentInfo.getDepartment()+File.separator+studentInfo.getMajor()
                //        + File.separator+ studentInfo.getClass_name()+File.separator+studentInfo.getStudent_id() + ".png" ;
                //如果人脸库有信息，更新学生考勤
                if (true){
                    //System.out.println("studentPicturePath:"+studentPicturePath+"；\nnowPath:"+path+File.separator+fileName);
                    teacherService.updateAttendanceToClassStudent(dataTableName,studentInfo.getStudent_id(),new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date),"迟到");
                    break;
                }
            }
        }
    }
    */


    /**
     * 查找某班级提交的考勤修改审核表
     * @param session
     * @param state 0：未审核的考勤申请  1：已经审核的考勤申请
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAttendanceChange.do")
    public @ResponseBody List<Kaoqin_change> findAttendanceChange(HttpSession session,String state) throws Exception {
        String class_id = (String) session.getAttribute("class_id");
        return teacherService.findAttendanceChange(class_id,state);
    }


    /**
     * 更新考勤审核信息
     * @param kaoqin_change 前端点击 同意/不同意 state=1，statue_if_agree 同意为1，不同意为0,不同意附上不同意的理由 reason_not_agree
     *                      所以前端调用此链接应传输id，statue_if_agree和reason_not_agree三个参数
     * 是否还需要前端传输一个考勤更改的参数?
     * @return
     */
    @RequestMapping("/updateAttendanceChange.do")
    public @ResponseBody String updateAttendanceChange(Kaoqin_change kaoqin_change)throws Exception{
        kaoqin_change.setState("1");
        teacherService.updateAttendanceChange(kaoqin_change);
        return "1";
    }

    //获取数据库表名
    private String getDataName(String class_id){
        String class_name = teacherService.findClassNameByClassId(class_id);
        String dataName = class_id+"_"+class_name;
        return dataName;
    }
}
