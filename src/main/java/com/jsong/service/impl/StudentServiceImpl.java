package com.jsong.service.impl;

import ch.qos.logback.classic.spi.STEUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jsong.entity.Dormitory;
import com.jsong.entity.Student;
import com.jsong.form.SearchForm;
import com.jsong.mapper.DormitoryMapper;
import com.jsong.mapper.StudentMapper;
import com.jsong.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jsong.util.CommonUtil;
import com.jsong.util.ResultVOUtil;
import com.jsong.vo.PageVO;
import com.jsong.vo.ResultVO;
import com.jsong.vo.StudentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2024-01-31
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private DormitoryMapper dormitoryMapper;

    @Override
    public Boolean saveStudent(Student student) {
        //添加学生
        student.setCreateDate(CommonUtil.createDate());
        student.setState("入住");
        int insert = this.studentMapper.insert(student);
        if (insert != 1) return false;
        //修改宿舍数据
        Dormitory dormitory = this.dormitoryMapper.selectById(student.getDormitoryId());
        if(dormitory.getAvailable() == 0){
            return false;
        }
        dormitory.setAvailable(dormitory.getAvailable() - 1);
        int update = this.dormitoryMapper.updateById(dormitory);
        if(update != 1) {
            return false;
        }
        return true;
    }

    @Override
    public PageVO listStudent(Integer page,Integer size) {
        Page<Student> studentPage = new Page<>(page,size);
        Page<Student> resultPage = this.studentMapper.selectPage(studentPage,null);
        List<Student> records = resultPage.getRecords();
        //VO转换
        List<StudentVO> studentVOList = new ArrayList<>();
        for(Student student : records){
            StudentVO studentVO = new StudentVO();
            BeanUtils.copyProperties(student,studentVO);
            Dormitory dormitory = this.dormitoryMapper.selectById(student.getDormitoryId());
            studentVO.setDormitoryName(dormitory.getName());
            studentVOList.add(studentVO);
        }

        PageVO pageVO = new PageVO();
        pageVO.setData(studentVOList);
        pageVO.setTotal(resultPage.getTotal());
        return pageVO;
    }

    @Override
    public Boolean deleteById(Integer id) {
        //修改宿舍人数
        Student student = this.studentMapper.selectById(id);
        Dormitory dormitory = this.dormitoryMapper.selectById(student.getDormitoryId());
        dormitory.setAvailable(dormitory.getAvailable()+1);
        int updateById = this.dormitoryMapper.updateById(dormitory);
        if (updateById != 1) {
            return false;
        }
        //删除学生
        int deleteById = this.studentMapper.deleteById(id);
        if (deleteById != 1) {
            return false;
        }
        return true;
    }

    @Override
    public PageVO search(SearchForm searchForm) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(searchForm.getKey(), searchForm.getValue());
        Page<Student> page = new Page<>(searchForm.getPage(),searchForm.getSize());
        Page<Student> studentPage = this.studentMapper.selectPage(page, queryWrapper);
        //VO转换
        List<StudentVO> studentVOList = new ArrayList<>();
        for(Student student : studentPage.getRecords()){
            StudentVO studentVO = new StudentVO();
            BeanUtils.copyProperties(student,studentVO);
            Dormitory dormitory = this.dormitoryMapper.selectById(student.getDormitoryId());
            studentVO.setDormitoryName(dormitory.getName());
            studentVOList.add(studentVO);
        }
        PageVO pageVO = new PageVO();
        pageVO.setTotal(studentPage.getTotal());
        pageVO.setData(studentVOList);
        return pageVO;
    }

    @Override
    public Boolean studentUpdate(Student student) {
        //宿舍改变
        Student student1 = this.studentMapper.selectById(student.getId());
        if(!student.getDormitoryId().equals(student1.getDormitoryId())){
            Dormitory dormitoryNow = this.dormitoryMapper.selectById(student.getDormitoryId());
            Dormitory dormitoryBefore = this.dormitoryMapper.selectById(student1.getDormitoryId());
            dormitoryBefore.setAvailable(dormitoryBefore.getAvailable()+1);
            int i1 = this.dormitoryMapper.updateById(dormitoryBefore);
            if(i1 != 1 ) return false;
            dormitoryNow.setAvailable(dormitoryNow.getAvailable()-1);
            int i2 = this.dormitoryMapper.updateById(dormitoryNow);
            if(i2 != 1) return false;
            int i = this.studentMapper.updateById(student);
            if(i != 1 ) return false;
        }else {
            //宿舍未改变
            int update = this.studentMapper.updateById(student);
            if (update != 1) {
                return false;
            }
        }
        return true;
    }

}
