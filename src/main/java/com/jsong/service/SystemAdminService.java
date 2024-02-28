package com.jsong.service;

import com.jsong.entity.SystemAdmin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jsong.form.RuleForm;
import com.jsong.vo.ResultVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2024-01-31
 */
public interface SystemAdminService extends IService<SystemAdmin> {


    public ResultVO login(RuleForm ruleForm);

}
