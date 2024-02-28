package com.jsong.form;

import lombok.Data;

/**
 * @author songjian
 * @created 2024-02-19-14:25
 */
@Data
public class SearchForm {
    private String value;
    private String key;
    private Integer page;
    private Integer size;
}
