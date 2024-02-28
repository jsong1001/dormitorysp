package com.jsong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jsong.entity.SystemAdmin;
import com.jsong.form.RuleForm;
import com.jsong.mapper.SystemAdminMapper;
import com.jsong.service.SystemAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class SystemAdminServiceImpl extends ServiceImpl<SystemAdminMapper, SystemAdmin> implements SystemAdminService {

    @Autowired
    private SystemAdminMapper systemAdminMapper;


    @Override
    public ResultVO login(RuleForm ruleForm) {
        QueryWrapper<SystemAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",ruleForm.getUsername());
        SystemAdmin systemAdmin = this.systemAdminMapper.selectOne(queryWrapper);
        ResultVO resultVO = new ResultVO();
        //1.判断用户是否存在
        if(systemAdmin == null){
            resultVO.setCode(-1);
            //判断密码是否正确
        }else {
            if(!systemAdmin.getPassword().equals(ruleForm.getPassword())){
                resultVO.setCode(-2);
            }else {
                resultVO.setCode(0);
                resultVO.setData(systemAdmin);
            }
        }
        return resultVO;
    }
}
