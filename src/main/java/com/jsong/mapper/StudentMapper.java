package com.jsong.mapper;

import com.jsong.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface StudentMapper extends BaseMapper<Student> {

}
