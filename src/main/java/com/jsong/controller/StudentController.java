package com.jsong.controller;


import com.jsong.entity.Student;
import com.jsong.form.SearchForm;
import com.jsong.service.DormitoryService;
import com.jsong.service.StudentService;
import com.jsong.util.CommonUtil;
import com.jsong.util.ResultVOUtil;
import com.jsong.vo.PageVO;
import com.jsong.vo.ResultVO;
import com.jsong.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2024-01-31
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/save")
    public ResultVO save(@RequestBody Student student){
        Boolean save = this.studentService.saveStudent(student);
        if(!save) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }

    @GetMapping("/list/{page}/{size}")
    public ResultVO list(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        PageVO pageVO = this.studentService.listStudent(page, size);
        return ResultVOUtil.success(pageVO);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResultVO deleteById(@PathVariable("id") Integer id){
        Boolean deleteById = this.studentService.deleteById(id);
        if (!deleteById) {
            return ResultVOUtil.fail();
        }else {
            return ResultVOUtil.success(null);
        }
    }


    @GetMapping("/search")
    public ResultVO search(SearchForm searchForm){
        PageVO search = this.studentService.search(searchForm);
        return ResultVOUtil.success(search);
    }

    @GetMapping("/searchById/{id}")
    public ResultVO searchById(@PathVariable("id") Integer id){
        Student student = this.studentService.getById(id);
        return ResultVOUtil.success(student);
    }
    @PutMapping("/update")
    public ResultVO update(@RequestBody Student student){
        Boolean update = this.studentService.studentUpdate(student);
        if (!update) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }
}

