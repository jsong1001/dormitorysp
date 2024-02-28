package com.jsong.controller;


import com.jsong.entity.Building;
import com.jsong.form.RuleForm;
import com.jsong.form.SearchForm;
import com.jsong.service.BuildingService;
import com.jsong.util.ResultVOUtil;
import com.jsong.vo.PageVO;
import com.jsong.vo.ResultVO;
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
@RequestMapping("/building")
public class BuildingController {
    @Autowired
    private BuildingService buildingService;


    @PostMapping("/save")
    public ResultVO save(@RequestBody Building building){
        boolean save = this.buildingService.save(building);
        if(!save) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }

    @GetMapping("/list/{page}/{size}")
    public ResultVO list(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        PageVO resultPageVO = this.buildingService.list(page, size);
        return ResultVOUtil.success(resultPageVO);
    }

    @GetMapping("/search")
    public ResultVO search(SearchForm searchForm){
        PageVO pageVO = this.buildingService.searchBuilding(searchForm);
        return ResultVOUtil.success(pageVO);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResultVO deleteByID(@PathVariable("id") Integer id){
        boolean result = this.buildingService.removeById(id);
        if(!result) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }

    @PutMapping("/update")
    public ResultVO updateById(@RequestBody Building building){
        boolean result = this.buildingService.updateById(building);
        if(!result) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }

    @GetMapping("/searchById/{id}")
    public ResultVO searchById(@PathVariable("id") Integer id){
        Building building = this.buildingService.getById(id);
        return ResultVOUtil.success(building);
    }
}

