package com.soufang.esproject.service.house;

import com.soufang.esproject.entity.*;
import com.soufang.esproject.repository.*;
import com.soufang.esproject.service.ServiceResult;
import com.soufang.esproject.web.dto.HouseDTO;
import com.soufang.esproject.web.dto.HouseDetailDTO;
import com.soufang.esproject.web.dto.HousePictureDTO;
import com.soufang.esproject.web.form.HouseForm;
import com.soufang.esproject.web.form.PhotoForm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description: es-project
 * Create by liangxifeng on 19-5-13
 */
@Service
public class HouseServiceImpl implements IHouseService {
    //@Autowired
    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private HouseReposity houseReposity;
    @Autowired
    private HouseDetailResposity houseDetailResposity;
    @Autowired
    private HousePictureResposity housePictureResposity;
    @Autowired
    private HouseTagRepository houseTagRepository;
    @Autowired
    private SubwayRepository subwayRepository;
    @Autowired
    private SubwayStationRepository subwayStationRepository;
    @Value("${qiniu.cdn.prefix}")
    private String cdnPrefix;
    @Override
    public ServiceResult<HouseDTO> save(HouseForm houseForm) {
        House house = new House();
        modelMapper.map(houseForm,house);
        Date now = new Date();
        house.setCreateTime(now);
        house.setLastUpdateTime(now);
        house.setAdminId(1L);
        house = houseReposity.save(house);

        HouseDetail houseDetail = new HouseDetail();
        houseDetail.setHouseId(house.getId());
        houseDetail = houseDetailResposity.save(houseDetail);

        List<HousePicture> pictures = gengeratePicture(houseForm,house.getId());
        Iterable<HousePicture> housePictures = housePictureResposity.save(pictures);

        HouseDTO houseDTO = modelMapper.map(house,HouseDTO.class);
        HouseDetailDTO detailDTO = modelMapper.map(houseDetail,HouseDetailDTO.class);
        houseDTO.setHouseDetail(detailDTO);

        List<HousePictureDTO> pictureDTOS = new ArrayList<>();
        housePictures.forEach(housePicture -> pictureDTOS.add(modelMapper.map(housePicture,HousePictureDTO.class)));
        houseDTO.setPictures(pictureDTOS);
        houseDTO.setCover(this.cdnPrefix + houseDTO.getCityEnName());


//        List<String> tags = houseForm.getTags();
//        if(tags != null || !tags.isEmpty()){
//            List<HouseTag> houseTags = new ArrayList<>();
//            for (String tag : tags) {
//                houseTags.add(new HouseTag(house.getId(),tag));
//            }
//            houseTagRepository.save(houseTags);
//            houseDTO.setTags(tags);
//        }
        return new ServiceResult<HouseDTO>(true,null,houseDTO);
    }
    private List<HousePicture> gengeratePicture(HouseForm houseForm, Long houseId){
        List<HousePicture> pictures = new ArrayList<>();
        if(houseForm.getPhotos() == null || houseForm.getPhotos().isEmpty()) {
            return pictures;
        }
        for (PhotoForm photoForm : houseForm.getPhotos()) {
            HousePicture picture = new HousePicture();
            picture.setHouseId(houseId);
            picture.setCdnPrefix(cdnPrefix);
            picture.setPath(photoForm.getPath());
            picture.setWidth(photoForm.getWidth());
            picture.setHeight(photoForm.getHeight());
            pictures.add(picture);
        }
        return pictures;

    }

    /**
     * 房屋详情
     * @param houseDetail
     * @param houseForm
     * @return
     */
    private ServiceResult<HouseDTO> warpperDetailInfo(HouseDetail houseDetail, HouseForm houseForm){
        Subway subway = subwayRepository.findOne(houseForm.getSubwayLineId());
        if(subway == null)
        {
            return new ServiceResult<>(false,"Not valid subway line!");
        }
        SubwayStation subwayStation = subwayStationRepository.findOne(houseForm.getSubwayStationId());
        if(subwayStation == null || subway.getId() != subwayStation.getSubwayId()){
            return new ServiceResult<>(false,"Not valid subway station line!");
        }
        houseDetail.setSubwayLineId(subway.getId());
        houseDetail.setSubwayLineName(subway.getName());

        houseDetail.setSubwayStationId(subwayStation.getId());
        houseDetail.setSubwayStationName(subwayStation.getName());

        houseDetail.setDescription(houseForm.getDescription());
        houseDetail.setDetailAddress("测试地址");
        houseDetail.setLayoutDesc(houseForm.getLayoutDesc());
        houseDetail.setRentWay(houseForm.getRentWay());

        houseDetail.setRoundService(houseForm.getRoundService());
        houseDetail.setTraffic(houseForm.getTraffic());
        return null;
    }
}
