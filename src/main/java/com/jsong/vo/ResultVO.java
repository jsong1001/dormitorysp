package com.jsong.vo;

import lombok.Data;

/**
 * @author songjian
 * @created 2024-01-31-15:11
 */
@Data
public class ResultVO<T> {
    private Integer code;
    private T data;
}
