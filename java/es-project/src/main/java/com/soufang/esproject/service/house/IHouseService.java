package com.soufang.esproject.service.house;

import java.util.Date;

import com.soufang.esproject.entity.House;
import com.soufang.esproject.service.ServiceResult;
import com.soufang.esproject.web.dto.HouseDTO;
import com.soufang.esproject.web.form.HouseForm;
import org.springframework.data.util.Pair;

/**
 * 房屋管理服务接口
 * Created by 瓦力.
 */
public interface IHouseService {
    ServiceResult<HouseDTO> save(HouseForm houseForm);
}
