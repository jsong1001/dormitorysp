package com.jsong.vo;

import lombok.Data;

/**
 * @author songjian
 * @created 2024-02-20-14:27
 */
@Data
public class StudentVO {
    private Integer id;
    private String number;
    private String name;
    private String gender;
    private String dormitoryName;
    private String state;
    private String createDate;
}
