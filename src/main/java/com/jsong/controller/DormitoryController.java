package com.jsong.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jsong.entity.Dormitory;
import com.jsong.service.DormitoryService;
import com.jsong.util.ResultVOUtil;
import com.jsong.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2024-01-31
 */
@RestController
@RequestMapping("/dormitory")
public class DormitoryController {
    @Autowired
    DormitoryService dormitoryService;

    @GetMapping("/availableList")
    public ResultVO availableList(){
        QueryWrapper<Dormitory> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("available",0);
        List<Dormitory> dormitoryList = this.dormitoryService.list(queryWrapper);
        return ResultVOUtil.success(dormitoryList);
    }
}

