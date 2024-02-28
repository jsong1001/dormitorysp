package com.jsong.util;

import com.jsong.vo.ResultVO;

/**
 * @author songjian
 * @created 2024-02-16-13:20
 */
public class ResultVOUtil {
    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO fail(){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(-1);
        return resultVO;
    }
}
