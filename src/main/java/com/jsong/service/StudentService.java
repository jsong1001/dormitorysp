package com.jsong.service;

import com.jsong.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jsong.form.SearchForm;
import com.jsong.vo.PageVO;
import com.jsong.vo.ResultVO;
import com.jsong.vo.StudentVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2024-01-31
 */
public interface StudentService extends IService<Student> {
    public Boolean saveStudent(Student student);
    public PageVO listStudent(Integer page, Integer size);
    public Boolean deleteById(Integer id);
    public PageVO search(SearchForm searchForm);
    public Boolean studentUpdate(Student student);
}
