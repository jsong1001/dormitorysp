package com.jsong.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author songjian
 * @created 2024-02-20-10:51
 */
public class CommonUtil {
    public static String createDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }
}
