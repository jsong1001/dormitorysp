package com.jsong.mapper;

import com.jsong.entity.DormitoryAdmin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jsong.form.RuleForm;
import com.jsong.vo.ResultVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2024-01-31
 */
@Mapper
public interface DormitoryAdminMapper extends BaseMapper<DormitoryAdmin> {
}
