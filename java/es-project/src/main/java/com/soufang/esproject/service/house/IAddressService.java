package com.soufang.esproject.service.house;

import com.soufang.esproject.entity.SupportAddress;
import com.soufang.esproject.service.ServiceMultiResult;
import com.soufang.esproject.web.dto.SupportAddressDTO;

import java.util.List;


/**
 * 地址服务接口
 * Created by 瓦力.
 */
public interface IAddressService {
    /**
     * 获取所有支持的城市列表
     * @return
     */
    ServiceMultiResult<SupportAddressDTO> findAllCities();

}

