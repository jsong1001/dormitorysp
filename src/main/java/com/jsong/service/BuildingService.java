package com.jsong.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jsong.entity.Building;
import com.baomidou.mybatisplus.extension.service.IService;
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
public interface BuildingService extends IService<Building> {
    public PageVO list(Integer page, Integer size);
    public PageVO searchBuilding(SearchForm searchForm);

}
