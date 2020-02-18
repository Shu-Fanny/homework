package com.pyp.service.impl;

import com.pyp.dao.IHomeWordDao;
import com.pyp.dao.IStudentDao;
import com.pyp.domain.StuWork;
import com.pyp.domain.Student;
import com.pyp.service.IStudentService;
import com.pyp.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements IStudentService {
    @Autowired
    private IStudentDao studentDao;
    @Autowired
    private IHomeWordDao homeWordDao;
    @Override
    public Student findStudentByUserName(String s_username) throws Exception {
        return studentDao.findStudentByUserName(s_username);
    }

    @Override
    public PageModel findHomeWorkByState(String sid, String state, int curNum) throws Exception {
        int totalRecords=homeWordDao.findTotalRecords(sid,state);
        PageModel pm=new PageModel(curNum,totalRecords,6);
        List<StuWork> stuWorks = homeWordDao.findHomeWorkByState(sid,state,pm.getStartIndex(),pm.getPageSize());
        pm.setList(stuWorks);
        return pm;
    }

    @Override
    public void updateStuWork(String sid, String hid, String file_path) throws Exception {
        studentDao.updateStuWork(file_path,sid,hid);
    }
}
