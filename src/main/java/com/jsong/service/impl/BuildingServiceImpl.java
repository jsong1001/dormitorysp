package com.jsong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jsong.entity.Building;
import com.jsong.entity.DormitoryAdmin;
import com.jsong.form.SearchForm;
import com.jsong.mapper.BuildingMapper;
import com.jsong.mapper.DormitoryAdminMapper;
import com.jsong.service.BuildingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jsong.vo.BuildingVO;
import com.jsong.vo.PageVO;
import com.jsong.vo.ResultVO;
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
public class BuildingServiceImpl extends ServiceImpl<BuildingMapper, Building> implements BuildingService {
    @Autowired
    private BuildingMapper buildingMapper;
    @Autowired
    private DormitoryAdminMapper dormitoryAdminMapper;

    @Override
    public PageVO list(Integer page, Integer size) {
        Page<Building> page1 = new Page<>(page,size);
        Page<Building> buildingPage = this.buildingMapper.selectPage(page1, null);
        //VO转换
        List<BuildingVO> buildingVOList = new ArrayList<>();
        for(Building building : buildingPage.getRecords()){
            BuildingVO buildingVO = new BuildingVO();
            BeanUtils.copyProperties(building,buildingVO);
            DormitoryAdmin dormitoryAdmin = this.dormitoryAdminMapper.selectById(building.getAdminId());
            buildingVO.setAdminName(dormitoryAdmin.getName());
            buildingVOList.add(buildingVO);
        }
        PageVO pageVO = new PageVO();
        pageVO.setData(buildingVOList);
        pageVO.setTotal(buildingPage.getTotal());
        return pageVO;
    }

    @Override
    public PageVO searchBuilding(SearchForm searchForm) {
        String key = searchForm.getKey();
        QueryWrapper<Building> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(searchForm.getKey(),searchForm.getValue());
        Page<Building> page = new Page<>(searchForm.getPage(),searchForm.getSize());
        Page<Building> resultPage = this.buildingMapper.selectPage(page, queryWrapper);
        //VO转换
        List<BuildingVO> buildingVOList = new ArrayList<>();
        for(Building building : resultPage.getRecords()){
            BuildingVO buildingVO = new BuildingVO();
            BeanUtils.copyProperties(building,buildingVO);
            buildingVO.setAdminName(this.dormitoryAdminMapper.selectById(building.getAdminId()).getName());
            buildingVOList.add(buildingVO);
        }
        PageVO pageVO = new PageVO();
        pageVO.setTotal(resultPage.getTotal());
        pageVO.setData(buildingVOList);
        return pageVO;
    }
}
