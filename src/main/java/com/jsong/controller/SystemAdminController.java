package com.jsong.controller;


import com.jsong.form.RuleForm;
import com.jsong.service.SystemAdminService;
import com.jsong.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2024-01-31
 */
@RestController
@RequestMapping("/systemAdmin")
public class SystemAdminController {
    @Autowired
    private SystemAdminService systemAdminService;

    @GetMapping("/login")
    public ResultVO login(RuleForm ruleForm){
        ResultVO resultVO = this.systemAdminService.login(ruleForm);
        return resultVO;
    }

}

