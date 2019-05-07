package com.soufang.esproject.web.controller.house;

import com.soufang.esproject.base.ApiResponse;
import com.soufang.esproject.service.ServiceMultiResult;
import com.soufang.esproject.service.house.IAddressService;
import com.soufang.esproject.web.dto.SupportAddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description: es-project
 * Create by liangxifeng on 19-5-7
 */
@Controller
public class HouseController {
    @Autowired
    IAddressService addressService;

    @GetMapping("address/support/cities")
    @ResponseBody
    public ApiResponse getSupportCities()
    {
        ServiceMultiResult<SupportAddressDTO> result = addressService.findAllCities();
        if( result.getResultSize() == 0 ){
            return ApiResponse.ofSuccess(ApiResponse.Status.NOT_FOUND);
        }else
        {
            return ApiResponse.ofSuccess(result);
        }

    }
}
