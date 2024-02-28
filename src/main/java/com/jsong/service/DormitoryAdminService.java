package com.jsong.service;

import com.jsong.entity.DormitoryAdmin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jsong.form.RuleForm;
import com.jsong.form.SearchForm;
import com.jsong.vo.PageVO;
import com.jsong.vo.ResultVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2024-01-31
 */
public interface DormitoryAdminService extends IService<DormitoryAdmin> {
    public ResultVO login(RuleForm ruleForm);
    public PageVO list(Integer page, Integer size);
    public PageVO search(SearchForm searchForm);
}
