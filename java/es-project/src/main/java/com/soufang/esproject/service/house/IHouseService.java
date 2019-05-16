package com.soufang.esproject.service.house;

import java.util.Date;

import com.soufang.esproject.service.ServiceMultiResult;
import com.soufang.esproject.service.ServiceResult;
import com.soufang.esproject.web.dto.HouseDTO;
import com.soufang.esproject.web.form.DatatableSearch;
import com.soufang.esproject.web.form.HouseForm;
import com.soufang.esproject.web.form.RentSearch;
import org.springframework.data.util.Pair;

/**
 * 房屋管理服务接口
 * Created by 瓦力.
 */
public interface IHouseService {
    /**
     * 新增房源
     * @param houseForm
     * @return
     */
    ServiceResult<HouseDTO> save(HouseForm houseForm);

    /**
     * 修改房源
     * @param houseForm
     * @return
     */
    ServiceResult update(HouseForm houseForm);

    /**
     * 后台查询列表
     * @param searchBody
     * @return
     */
    ServiceMultiResult<HouseDTO> adminQuery(DatatableSearch searchBody);

    /**
     * 查询完整房源信息
     * @param id
     * @return
     */
    ServiceResult<HouseDTO> findCompleteOne(Long id);
    /**
     * 移除图片
     * @param id
     * @return
     */
    ServiceResult removePhoto(Long id);

    /**
     * 更新封面
     * @param coverId
     * @param targetId
     * @return
     */
    ServiceResult updateCover(Long coverId, Long targetId);

    /**
     * 新增标签
     * @param houseId
     * @param tag
     * @return
     */
    ServiceResult addTag(Long houseId, String tag);

    /**
     * 移除标签
     * @param houseId
     * @param tag
     * @return
     */
    ServiceResult removeTag(Long houseId, String tag);
    /**
     * 更新房源状态
     * @param id
     * @param status
     * @return
     */
    ServiceResult updateStatus(Long id, int status);
    /**
     * 查询房源信息集
     * @param rentSearch
     * @return
     */
    ServiceMultiResult<HouseDTO> query(RentSearch rentSearch);

}
