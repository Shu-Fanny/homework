package com.pyp.check_by_face.controller;

import com.pyp.check_by_face.domain.PO.Course;
import com.pyp.check_by_face.domain.PO.TeacherInfo;
import com.pyp.check_by_face.services.ITeacherService;
import com.pyp.check_by_face.utils.FaceMatch.company.FaceMatchUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/test")
public class test {
    @Autowired
    private ITeacherService teacherService;

    @RequestMapping("/test.do")
    public @ResponseBody String  test() throws IOException {
       // response.setHeader("Access-Control-Allow-Origin","*");
       // response.setHeader("Access-Control-Allow-Methods","POST");
       // response.setHeader("Access-Control-Allow-Headers","Access-Control");
       // response.setContentType("application/json;charset=utf-8");
       // response.setHeader("Allow","POST");
        //response.getWriter().print("1");
        return "1";
    }

    @RequestMapping("/findCourseByTid.do")
    public @ResponseBody List<Course> findCourseByTid() throws Exception {
        TeacherInfo teacher = new TeacherInfo();
        teacher.setT_job_number("2003082802");
        System.out.println("teacher:"+teacher);
        List<Course> courses = teacherService.findCourseByTid(teacher.getT_job_number());
        return courses;
    }

    /*
     *教师添加课程
     */
    @RequestMapping("/testUpload.do")
    public @ResponseBody String addCourseToTeacher (HttpSession session, MultipartFile picture) throws Exception{
        //使用fileupload组件完成文件上传
        //上传路径
        String fileName;
        if (picture != null){
            String path = session.getServletContext().getRealPath("/img/test/");
            File file = new File(path);
            if (!file.exists()){
                file.mkdir();
            }
            //获取上传文件项
            //获取上传文件的名称
            fileName = picture.getOriginalFilename();
            //设置文件名称为唯一值，uuid
            String uuid = UUID.randomUUID().toString().replace("-","");
            fileName = uuid+"_"+fileName;
            //完成文件上传
            try {
                picture.transferTo(new File(path,fileName));
            } catch (IOException e) {
                System.out.println("上传文件失败！");
                return "2";
            }
        }
        return "1";
    }

    @RequestMapping("/comparePicture.do")
    public @ResponseBody int comparePicture(){
        //获取人脸库中的图片本地路径
        String fileLibraryPath = new File(System.getProperty("user.dir")).getParent()
                + File.separator + "check_by_face_controller\\src\\main\\webapp\\img\\test" + File.separator ;
        FaceMatchUtils faceMatchUtils = new FaceMatchUtils();
        //faceMatchUtils.comparePicture(fileLibraryPath,fileLibraryPath,"1.png");
        return 1;
    }
}
