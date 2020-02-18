package com.pyp.check_by_face.controller;


import com.pyp.check_by_face.domain.VO.ClassOfStudentInfo;
import com.pyp.check_by_face.services.ITeacherService;
import com.pyp.check_by_face.utils.FaceMatch.company.FaceMatchUtils;
import com.pyp.check_by_face.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequestMapping("/compare")
@Controller
public class ComparePicture {
    @Autowired
    private ITeacherService teacherService;

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

    /*
     *教师考勤
     */
    @RequestMapping("/addAttendanceToClassStudents.do")
    public void addAttendanceToClassStudents(HttpSession session, MultipartFile picture) throws Exception{
        String state = (String) session.getAttribute("state");
        //如果状态不为1，则结束此次请求
        if (!("1".equals(state))){
            System.out.println("教师未点击开始考勤，不能上传图片！");
            return;
        }
        //使用fileupload组件完成文件上传
        String fileName = "";
        String path =session.getServletContext().getRealPath("/img/compare/");
        if (picture != null){
            fileName = new UploadUtils().upload(picture,path);
            //如果保存失败，则结束此次请求
            if (fileName=="2"){
                System.out.println("文件上传失败，请重新上传！");
                return;
            }
        }
        String class_id = (String) session.getAttribute("class_id");
        //需要考勤的学生集合
        List<ClassOfStudentInfo> studentInfos = teacherService.findStudentByClassId(class_id);
        //获取需要更改考勤的考勤表名
        String dataName = getDataName(class_id);

        //定义考勤开始时间的key值
        String attendanceTimeKey = class_id+"_attendance_time";
        //考勤时间
        Date date = null;
        if (session.getAttribute(attendanceTimeKey)==null ){
            date = new Date();
            session.setAttribute(attendanceTimeKey,date);
            //将获取的学生id在对应的考勤表中添加考勤记录
            teacherService.addAttendanceToClassStudents(studentInfos,dataName,new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date),"旷课");
        }else {
            date = (Date) session.getAttribute(attendanceTimeKey);
        }

        Date nowDate = new Date();
        FaceMatchUtils faceMatchUtils = new FaceMatchUtils();

        //出勤
        if (nowDate.getTime()-date.getTime()<=1000L*1200L){
            for (ClassOfStudentInfo studentInfo:studentInfos){
                //获取符合条件的学生
                String sid = teacherService.findSidByDateAndStatu(dataName,"旷课",new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date),studentInfo.getStudent_id());
                System.out.println("符合条件的sid:"+sid+"；\n集合中的sid"+studentInfo.getStudent_id());
                if (sid==null){
                    System.out.println("我是"+studentInfo.getClass_name()+"不能被对比！");
                    continue;
                }
                //学生库图片路径
                String studentPicturePath = new File(System.getProperty("user.dir")).getParent()
                        + File.separator + "check_by_face_controller\\src\\main\\webapp\\img\\"+studentInfo.getDepartment()+File.separator+studentInfo.getMajor()
                        + File.separator+ studentInfo.getClass_name()+File.separator+studentInfo.getStudent_id() + ".png" ;
                //如果人脸库有信息，更新学生考勤
                if (faceMatchUtils.comparePicture(studentPicturePath,path+File.separator+fileName)){
                    //System.out.println("studentPicturePath:"+studentPicturePath+"；\nnowPath:"+path+File.separator+fileName);
                    teacherService.updateAttendanceToClassStudent(dataName,studentInfo.getStudent_id(),new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date),"出勤");
                    break;
                }
            }
        }
        //迟到
        else if (nowDate.getTime()-date.getTime()>1000L*1200L && nowDate.getTime()-date.getTime()<=1000L*3600L){
            for (ClassOfStudentInfo studentInfo:studentInfos){
                String sid = teacherService.findSidByDateAndStatu(dataName,"旷课",new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date),studentInfo.getStudent_id());
                System.out.println("符合条件的sid:"+sid+"集合中的sid"+studentInfo.getStudent_id());
                if (sid==null){
                    continue;
                }
                //学生库图片路径
                String studentPicturePath = new File(System.getProperty("user.dir")).getParent()
                        + File.separator + "check_by_face_controller\\src\\main\\webapp\\img\\"+studentInfo.getDepartment()+File.separator+studentInfo.getMajor()
                        + File.separator+ studentInfo.getClass_name()+File.separator+studentInfo.getStudent_id() + ".png" ;
                //如果人脸库有信息，更新学生考勤
                if (faceMatchUtils.comparePicture(studentPicturePath,path+File.separator+fileName)){
                    //System.out.println("studentPicturePath:"+studentPicturePath+"；\nnowPath:"+path+File.separator+fileName);
                    teacherService.updateAttendanceToClassStudent(dataName,studentInfo.getStudent_id(),new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date),"迟到");
                    break;
                }
            }
        }
    }

    //获取数据库表名
    private String getDataName(String class_id){
        String class_name = teacherService.findClassNameByClassId(class_id);
        String dataName = class_id+"_"+class_name;
        return dataName;
    }
}
