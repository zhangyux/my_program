package com.soufang.esproject.web.controller.admin;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.soufang.esproject.base.ApiDataTableResponse;
import com.soufang.esproject.base.ApiResponse;
import com.soufang.esproject.base.HouseOperation;
import com.soufang.esproject.base.HouseStatus;
import com.soufang.esproject.entity.SupportAddress;
import com.soufang.esproject.service.ServiceMultiResult;
import com.soufang.esproject.service.ServiceResult;
import com.soufang.esproject.service.house.IAddressService;
import com.soufang.esproject.service.house.IHouseService;
import com.soufang.esproject.service.house.IQiNiuService;
import com.soufang.esproject.web.dto.*;
import com.soufang.esproject.web.form.DatatableSearch;
import com.soufang.esproject.web.form.HouseForm;
import com.sun.tracing.dtrace.ModuleAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    /**
     * 新增房源
     * @return
     */
    @GetMapping("/admin/add/house")
    public String addHousePage(){
        return "admin/house-add";
    }
    /**
     * 新增房源action
     * @param houseForm
     * @param bindingResult
     * @return
     */
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

    /**
     * 编辑房源
     * @return
     */
    @GetMapping("/admin/house/edit")
    public String houseEditPage(@RequestParam(value = "id") Long id, Model model){
        if(id == null || id < 1){
            return "404";
        }
        ServiceResult<HouseDTO> serviceResult = houseService.findCompleteOne(id);
        if( !serviceResult.isSuccess() ){
            return "404";
        }
        HouseDTO result = serviceResult.getResult();
        model.addAttribute("house", result);

        Map<SupportAddress.Level, SupportAddressDTO> addressMap = addressService.findCityAndRegion(result.getCityEnName(), result.getRegionEnName());
        model.addAttribute("city", addressMap.get(SupportAddress.Level.CITY));
        model.addAttribute("region", addressMap.get(SupportAddress.Level.REGION));

        HouseDetailDTO detailDTO = result.getHouseDetail();
        ServiceResult<SubwayDTO> subwayServiceResult = addressService.findSubway(detailDTO.getSubwayLineId());
        if (subwayServiceResult.isSuccess()) {
            model.addAttribute("subway", subwayServiceResult.getResult());
        }

        ServiceResult<SubwayStationDTO> subwayStationServiceResult = addressService.findSubwayStation(detailDTO.getSubwayStationId());
        if (subwayStationServiceResult.isSuccess()) {
            model.addAttribute("station", subwayStationServiceResult.getResult());
        }

        return "admin/house-edit";

    }
    /**
     * 编辑接口
     */
    @PostMapping("admin/house/edit")
    @ResponseBody
    public ApiResponse saveHouse(@Valid @ModelAttribute("form-house-edit") HouseForm houseForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ApiResponse(HttpStatus.BAD_REQUEST.value(), bindingResult.getAllErrors().get(0).getDefaultMessage(), null);
        }

        Map<SupportAddress.Level, SupportAddressDTO> addressMap = addressService.findCityAndRegion(houseForm.getCityEnName(), houseForm.getRegionEnName());

        if (addressMap.keySet().size() != 2) {
            return ApiResponse.ofSuccess(ApiResponse.Status.NOT_VALID_PARAM);
        }

        ServiceResult result = houseService.update(houseForm);
        if (result.isSuccess()) {
            return ApiResponse.ofSuccess(null);
        }

        ApiResponse response = ApiResponse.ofStatus(ApiResponse.Status.BAD_REQUEST);
        response.setMessage(result.getMessage());
        return response;
    }

    /**
     * 房源列表
     * @return
     */
    @GetMapping("/admin/house/list")
    public String houseListPage(){
        return "admin/house-list";
    }
    /**
     * 房源列表api
     */
    @PostMapping("admin/houses")
    @ResponseBody
    public ApiDataTableResponse houses(@ModelAttribute DatatableSearch searchBody) {
        ServiceMultiResult<HouseDTO> result = houseService.adminQuery(searchBody);
        ApiDataTableResponse response = new ApiDataTableResponse(ApiResponse.Status.SUCCESS);
        response.setData(result.getResult());
        response.setRecordsFiltered(result.getTotal());
        response.setRecordsTotal(result.getTotal());
        response.setDraw(searchBody.getDraw());
        return response;
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
    /**
     * 审核接口
     * @param id
     * @param operation
     * @return
     */
    @PutMapping("admin/house/operate/{id}/{operation}")
    @ResponseBody
    public ApiResponse operateHouse(@PathVariable(value = "id") Long id,
                                    @PathVariable(value = "operation") int operation) {
        if (id <= 0) {
            return ApiResponse.ofStatus(ApiResponse.Status.NOT_VALID_PARAM);
        }
        ServiceResult result;

        switch (operation) {
            case HouseOperation.PASS:
                result = this.houseService.updateStatus(id, HouseStatus.PASSED.getValue());
                break;
            case HouseOperation.PULL_OUT:
                result = this.houseService.updateStatus(id, HouseStatus.NOT_AUDITED.getValue());
                break;
            case HouseOperation.DELETE:
                result = this.houseService.updateStatus(id, HouseStatus.DELETE.getValue());
                break;
            case HouseOperation.RENT:
                result = this.houseService.updateStatus(id, HouseStatus.RENTED.getValue());
                break;
            default:
                return ApiResponse.ofStatus(ApiResponse.Status.BAD_REQUEST);
        }

        if (result.isSuccess()) {
            return ApiResponse.ofSuccess(null);
        }
        return ApiResponse.ofMessage(HttpStatus.BAD_REQUEST.value(),
                result.getMessage());
    }



}
