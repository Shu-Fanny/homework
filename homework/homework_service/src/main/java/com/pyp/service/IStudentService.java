package com.pyp.service;

import com.pyp.domain.StuWork;
import com.pyp.domain.Student;
import com.pyp.utils.PageModel;

import java.util.List;

public interface IStudentService {
    Student findStudentByUserName(String s_username)throws Exception;

    PageModel findHomeWorkByState(String sid, String state, int curNum)throws Exception;

    void updateStuWork(String sid, String hid, String file_path)throws Exception;
}
