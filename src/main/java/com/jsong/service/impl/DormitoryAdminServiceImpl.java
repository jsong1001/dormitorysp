package com.jsong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jsong.entity.DormitoryAdmin;
import com.jsong.form.RuleForm;
import com.jsong.form.SearchForm;
import com.jsong.mapper.DormitoryAdminMapper;
import com.jsong.service.DormitoryAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jsong.vo.PageVO;
import com.jsong.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2024-01-31
 */
@Service
public class DormitoryAdminServiceImpl extends ServiceImpl<DormitoryAdminMapper, DormitoryAdmin> implements DormitoryAdminService {

    @Autowired
    private DormitoryAdminMapper dormitoryAdminMapper;
    @Override
    public ResultVO login(RuleForm ruleForm) {
        QueryWrapper<DormitoryAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",ruleForm.getUsername());
        DormitoryAdmin dormitoryAdmin = this.dormitoryAdminMapper.selectOne(queryWrapper);
        ResultVO resultVO = new ResultVO();
        if(dormitoryAdmin == null){
            resultVO.setCode(-1);
        }else if(dormitoryAdmin.getPassword().equals(ruleForm.getPassword())){
            resultVO.setCode(0);
            resultVO.setData(dormitoryAdmin);
        }else {
            resultVO.setCode(-2);
        }
        return resultVO;
    }

    @Override
    public PageVO list(Integer page, Integer size) {
        Page<DormitoryAdmin> dormitoryAdminPage = new Page<>(page,size);
        Page<DormitoryAdmin> resultPage = this.dormitoryAdminMapper.selectPage(dormitoryAdminPage, null);
        PageVO pageVO = new PageVO();
        pageVO.setData(resultPage.getRecords());
        pageVO.setTotal(resultPage.getTotal());
        return pageVO;
    }

    @Override
    public PageVO search(SearchForm searchForm) {
        QueryWrapper<DormitoryAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(searchForm.getKey(),searchForm.getValue());
        Page<DormitoryAdmin> page = new Page<>(searchForm.getPage(),searchForm.getSize());
        Page<DormitoryAdmin> dormitoryAdminPage = this.dormitoryAdminMapper.selectPage(page, queryWrapper);
        PageVO pageVO = new PageVO();
        pageVO.setTotal(dormitoryAdminPage.getTotal());
        pageVO.setData(dormitoryAdminPage.getRecords());
        return pageVO;
    }
}
