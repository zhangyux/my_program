package com.soufang.esproject.web.controller.admin;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.soufang.esproject.base.ApiResponse;
import com.soufang.esproject.entity.SupportAddress;
import com.soufang.esproject.service.ServiceResult;
import com.soufang.esproject.service.house.IAddressService;
import com.soufang.esproject.service.house.IHouseService;
import com.soufang.esproject.service.house.IQiNiuService;
import com.soufang.esproject.web.dto.HouseDTO;
import com.soufang.esproject.web.dto.QiNiuPutRet;
import com.soufang.esproject.web.dto.SupportAddressDTO;
import com.soufang.esproject.web.form.HouseForm;
import com.sun.tracing.dtrace.ModuleAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Description: es-project
 * Create by liangxifeng on 19-4-15
 */
@Controller
public class AdminController {
    @Autowired
    IQiNiuService iQiNiuService;
    @Autowired
    Gson gson;
    @Autowired
    private IAddressService addressService;

    @Autowired
    private IHouseService houseService;

    @GetMapping("/admin/center")
    public String adminCenterPage(){
        return "admin/center";
    }

    @GetMapping("/admin/welcome")
    public String welcomePage(){
        return "admin/welcome";

    }

    @GetMapping("/admin/login")
    public String adminLoginPage(){
        return "admin/login";
    }

    @GetMapping("/admin/add/house")
    public String addHousePage(){
        return "admin/house-add";
    }

    @GetMapping("/admin/house/list")
    public String houseListPage(){
        return "admin/house-list";
    }

    /**
     * 上传图片到本地
     * @param file
     * @return
     */
    @PostMapping(value = "/admin/upload/locphoto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ApiResponse uploadLocPhoto(@RequestParam("file") MultipartFile file ){
        if( file.isEmpty() ){
            return ApiResponse.ofStatus(ApiResponse.Status.NOT_VALID_PARAM);
        }
        String fileName = file.getOriginalFilename();
        File target = new File("/home/lxf/git/user_liangxifeng833/my_program/java/es-project/tmp/" + fileName);
        try {
            file.transferTo(target);
        } catch (IOException e) {
            e.printStackTrace();
            return ApiResponse.ofStatus(ApiResponse.Status.INTERNAL_SERVER_ERROR);
        }
        return ApiResponse.ofSuccess(null);
    }

    /**
     * 上传图片到七牛云
     * @param file
     * @return
     */
    @PostMapping(value = "/admin/upload/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ApiResponse uploadPhoto(@RequestParam("file") MultipartFile file ){
        if( file.isEmpty() ){
            return ApiResponse.ofStatus(ApiResponse.Status.NOT_VALID_PARAM);
        }
        try {
            //获取内容中的文件流
            InputStream fileInputStream = file.getInputStream();
            //将文件传输到远程七牛云
            Response response = iQiNiuService.uploadFile(fileInputStream);
            if( response.isOK() ){
                //解析上传后的结果为QiNiuPutRet类格式
                QiNiuPutRet qiNiuPutRet = gson.fromJson(response.bodyString(), QiNiuPutRet.class);
                return ApiResponse.ofSuccess(qiNiuPutRet);
            }else{
                return ApiResponse.ofMessage(response.statusCode,response.getInfo());
            }
        } catch (QiniuException e){
            Response response = e.response;
            try {
                return ApiResponse.ofMessage(response.statusCode,response.bodyString());
            } catch (QiniuException e1) {
                e1.printStackTrace();
                return ApiResponse.ofStatus(ApiResponse.Status.INTERNAL_SERVER_ERROR);
            }
        } catch (IOException e) {
            return ApiResponse.ofStatus(ApiResponse.Status.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("admin/add/house")
    @ResponseBody
    public ApiResponse addHouse(@Valid @ModelAttribute("form-house-add") HouseForm houseForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ApiResponse(HttpStatus.BAD_REQUEST.value(),bindingResult.getAllErrors().get(0).getDefaultMessage(),null);
        }
        if(houseForm.getPhotos() == null || houseForm.getCover() == null){
            return ApiResponse.ofMessage(HttpStatus.BAD_REQUEST.value(),"必须上传图片");
        }
        Map<SupportAddress.Level,SupportAddressDTO> addRessMap = addressService.findCityAndRegion(houseForm.getCityEnName(),houseForm.getRegionEnName());
        if( addRessMap.keySet().size() != 2 ){
            return ApiResponse.ofStatus(ApiResponse.Status.NOT_VALID_PARAM);
        }
        ServiceResult<HouseDTO> result = houseService.save(houseForm);
        if( result.isSuccess() ){
            return ApiResponse.ofSuccess(result.getResult());
        }
        return ApiResponse.ofStatus(ApiResponse.Status.NOT_VALID_PARAM);
    }
}
