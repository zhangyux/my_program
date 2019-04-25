package com.soufang.esproject.web.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Description: es-project
 * Create by liangxifeng on 19-4-25
 */
@Controller
public class UserController {
    @GetMapping("/user/login")
    public String loginPage(){
        return "user/login";
    }

    @GetMapping("/user/center")
    public String center(){
        return "user/center";
    }
}
