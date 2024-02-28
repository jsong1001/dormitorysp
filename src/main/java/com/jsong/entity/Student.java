package com.jsong.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2024-01-31
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class Student implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String number;

    private String name;

    private String gender;

    private Integer dormitoryId;

    private String state;

    private String createDate;


}
