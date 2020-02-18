package com.pyp.service;

import com.pyp.domain.HomeWork;
import com.pyp.domain.Teacher;
import com.pyp.utils.PageModel;

public interface ITeacherService {
    Teacher findTeacherByUserName(String t_username)throws Exception;

    PageModel findAllHomeWorkWithPage(int num)throws Exception;

    PageModel findCommitHomeWorkWithPage(int curNum,String state)throws Exception;

    void updateHomeWorkScore(String sid, String hid, String score)throws Exception;

    void addHomeWork(HomeWork homeWork)throws Exception;
}
