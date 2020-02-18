package com.pyp.service.impl;

import com.pyp.dao.IHomeWordDao;
import com.pyp.dao.IStudentDao;
import com.pyp.dao.ITeacherDao;
import com.pyp.domain.HomeWork;
import com.pyp.domain.StuWork;
import com.pyp.domain.Student;
import com.pyp.domain.Teacher;
import com.pyp.service.ITeacherService;
import com.pyp.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TeacherServiceImpl implements ITeacherService {
    @Autowired
    private ITeacherDao teacherDao;
    @Autowired
    private IHomeWordDao homeWordDao;
    @Autowired
    private IStudentDao studentDao;

    /**
     * 根据教师登陆账号，找到该名教师的详细信息
     * @param t_username
     * @return
     * @throws Exception
     */
    @Override
    public Teacher findTeacherByUserName(String t_username) throws Exception {
        return teacherDao.findTeacherByUserName(t_username);
    }

    /**
     * 查找所有已发布的作业
     * @param num
     * @return
     * @throws Exception
     */
    @Override
    public PageModel findAllHomeWorkWithPage(int num) throws Exception {
        int totalRecords = homeWordDao.findTotalHWRecords();
        PageModel pm=new PageModel(num,totalRecords,6);
        List<HomeWork> homeWorks = homeWordDao.findAllHomeWorkWithPage(pm.getStartIndex(),pm.getPageSize());
        pm.setList(homeWorks);
        return pm;
    }

    /**
     * 查找所有提交的作业
     * @param num 第几页
     * @param state  1：已提交，但是未审核  2：已提交，并且已批改
     * @return
     * @throws Exception
     */
    @Override
    public PageModel findCommitHomeWorkWithPage(int num,String state) throws Exception {
        int totalRecords = homeWordDao.findTotalRecordsByState(state);
        PageModel pm=new PageModel(num,totalRecords,6);
        List<StuWork> stuWorks = homeWordDao.findCommitHomeWorkWithPage(state,pm.getStartIndex(),pm.getPageSize());
        pm.setList(stuWorks);
        return pm;
    }

    /**
     * 更新学生作业成绩
     * @param sid 学生id
     * @param hid 作业id
     * @param score 成绩
     * @throws Exception
     */
    @Override
    public void updateHomeWorkScore(String sid, String hid, String score) throws Exception {
        String state = "2";
        teacherDao.updateHomeWorkScore(sid,hid,state,score);
    }

    /**
     * 教师发布作业，在发布的同时，将该作业添加到学生作业记录中
     * @param homeWork
     * @throws Exception
     */
    @Override
    public void addHomeWork(HomeWork homeWork) throws Exception {
        String hid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        homeWork.setHid(hid);
        homeWordDao.addHomeWork(homeWork);
        List<Student> students = studentDao.findAllStudent();
        for (Student student:students) {
            studentDao.addHomeWork(student.getSid(),homeWork.getHid());
        }
    }
}
