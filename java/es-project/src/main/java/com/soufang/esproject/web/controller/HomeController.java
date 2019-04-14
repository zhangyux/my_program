package com.soufang.esproject.web.controller;

import com.soufang.esproject.base.ApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 瓦力.
 */
@Controller
public class HomeController {
    /*
    @Autowired
    private ISmsService smsService;*/

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("name","你好我是从controller赋值过来的,ok");
        return "index";
    }
    @GetMapping("get")
    @ResponseBody
    public ApiResponse get()
    {
        //return new ApiResponse(11,"dafdsa",null);
        return ApiResponse.ofMessage(2,"数据库未查询到任何记录");
        //return ApiResponse.ofStatus(ApiResponse.Status.BAD_REQUEST);
        //return ApiResponse.ofMessage(101010,"hahahah");
    }

    /*
    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        return "index";
    }*/

    @GetMapping("/404")
    public String notFoundPage() {
        return "404";
    }

    @GetMapping("/403")
    public String accessError() {
        return "403";
    }

    @GetMapping("/500")
    public String internalError() {
        return "500";
    }

    @GetMapping("/logout/page")
    public String logoutPage() {
        return "logout";
    }

     /*
    @GetMapping(value = "sms/code")

    @ResponseBody
    public ApiResponse smsCode(@RequestParam("telephone") String telephone) {
        if (!LoginUserUtil.checkTelephone(telephone)) {
            return ApiResponse.ofMessage(HttpStatus.BAD_REQUEST.value(), "请输入正确的手机号");
        }
        ServiceResult<String> result = smsService.sendSms(telephone);
        if (result.isSuccess()) {
            return ApiResponse.ofSuccess("");
        } else {
            return ApiResponse.ofMessage(HttpStatus.BAD_REQUEST.value(), result.getMessage());
        }

    }*/
}
