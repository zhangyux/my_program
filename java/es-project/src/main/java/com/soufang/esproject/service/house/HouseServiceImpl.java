package com.soufang.esproject.service.house;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.soufang.esproject.base.HouseStatus;
import com.soufang.esproject.base.LoginUserUtil;
import com.soufang.esproject.entity.*;
import com.soufang.esproject.repository.*;
import com.soufang.esproject.service.ServiceMultiResult;
import com.soufang.esproject.service.ServiceResult;
import com.soufang.esproject.web.dto.HouseDTO;
import com.soufang.esproject.web.dto.HouseDetailDTO;
import com.soufang.esproject.web.dto.HousePictureDTO;
import com.soufang.esproject.web.form.DatatableSearch;
import com.soufang.esproject.web.form.HouseForm;
import com.soufang.esproject.web.form.PhotoForm;
import com.soufang.esproject.web.form.RentSearch;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.*;

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
    @Autowired
    private IQiNiuService qiNiuService;

    /**
     * 新增房源信息
     * @param houseForm
     * @return
     */
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

    /**
     * 更新房源信息
     * @param houseForm
     * @return
     */
    @Override
    @Transactional
    public ServiceResult update(HouseForm houseForm) {
        House house = this.houseReposity.findOne(houseForm.getId());
        if (house == null) {
            return ServiceResult.notFound();
        }

        HouseDetail detail = this.houseDetailResposity.findByHouseId(house.getId());
        if (detail == null) {
            return ServiceResult.notFound();
        }

        ServiceResult wrapperResult = warpperDetailInfo(detail, houseForm);
        if (wrapperResult != null) {
            return wrapperResult;
        }

        houseDetailResposity.save(detail);

        List<HousePicture> pictures = gengeratePicture(houseForm, houseForm.getId());
        housePictureResposity.save(pictures);

        if (houseForm.getCover() == null) {
            houseForm.setCover(house.getCover());
        }

        modelMapper.map(houseForm, house);
        house.setLastUpdateTime(new Date());
        houseReposity.save(house);

//        if (house.getStatus() == HouseStatus.PASSED.getValue()) {
//            search.index(house.getId());
//        }

        return ServiceResult.success();
    }

    @Override
    public ServiceResult removePhoto(Long id) {
        HousePicture picture = housePictureResposity.findOne(id);
        if (picture == null) {
            return ServiceResult.notFound();
        }

        try {
            Response response = this.qiNiuService.delete(picture.getPath());
            if (response.isOK()) {
                housePictureResposity.delete(id);
                return ServiceResult.success();
            } else {
                return new ServiceResult(false, response.error);
            }
        } catch (QiniuException e) {
            e.printStackTrace();
            return new ServiceResult(false, e.getMessage());
        }
    }

    /**
     * 更新封面图片信息
     * @param coverId
     * @param targetId
     * @return
     */
    @Override
    @Transactional
    public ServiceResult updateCover(Long coverId, Long targetId) {
        HousePicture cover = housePictureResposity.findOne(coverId);
        if (cover == null) {
            return ServiceResult.notFound();
        }

        houseReposity.updateCover(targetId, cover.getPath());
        return ServiceResult.success();
    }

    @Override
    @Transactional
    public ServiceResult addTag(Long houseId, String tag) {
        House house = houseReposity.findOne(houseId);
        if (house == null) {
            return ServiceResult.notFound();
        }

        HouseTag houseTag = houseTagRepository.findByNameAndHouseId(tag, houseId);
        if (houseTag != null) {
            return new ServiceResult(false, "标签已存在");
        }

        houseTagRepository.save(new HouseTag(houseId, tag));
        return ServiceResult.success();
    }

    @Override
    @Transactional
    public ServiceResult removeTag(Long houseId, String tag) {
        House house = houseReposity.findOne(houseId);
        if (house == null) {
            return ServiceResult.notFound();
        }

        HouseTag houseTag = houseTagRepository.findByNameAndHouseId(tag, houseId);
        if (houseTag == null) {
            return new ServiceResult(false, "标签不存在");
        }

        houseTagRepository.delete(houseTag.getId());
        return ServiceResult.success();
    }
    @Override
    @Transactional
    public ServiceResult updateStatus(Long id, int status) {
        House house = houseReposity.findOne(id);
        if (house == null) {
            return ServiceResult.notFound();
        }

        if (house.getStatus() == status) {
            return new ServiceResult(false, "状态没有发生变化");
        }

        if (house.getStatus() == HouseStatus.RENTED.getValue()) {
            return new ServiceResult(false, "已出租的房源不允许修改状态");
        }

        if (house.getStatus() == HouseStatus.DELETE.getValue()) {
            return new ServiceResult(false, "已删除的资源不允许操作");
        }

        houseReposity.updateStatus(id, status);

        // 上架更新索引 其他情况都要删除索引
        if (status == HouseStatus.PASSED.getValue()) {
           // searchService.index(id);
        } else {
           // searchService.remove(id);
        }
        return ServiceResult.success();
    }

    private List<HouseDTO> wrapperHouseResult(List<Long> houseIds) {
        List<HouseDTO> result = new ArrayList<>();

        Map<Long, HouseDTO> idToHouseMap = new HashMap<>();
        Iterable<House> houses = houseReposity.findAll(houseIds);
        houses.forEach(house -> {
            HouseDTO houseDTO = modelMapper.map(house, HouseDTO.class);
            houseDTO.setCover(this.cdnPrefix + house.getCover());
            idToHouseMap.put(house.getId(), houseDTO);
        });

        //wrapperHouseList(houseIds, idToHouseMap);

        // 矫正顺序
        for (Long houseId : houseIds) {
            result.add(idToHouseMap.get(houseId));
        }
        return result;
    }

    /**
     * 房屋列表查询搜索分页
     * @param searchBody
     * @return
     */
    @Override
    public ServiceMultiResult<HouseDTO> adminQuery(DatatableSearch searchBody) {
        List<HouseDTO> houseDTOS = new ArrayList<>();

        Sort sort = new Sort(Sort.Direction.fromString(searchBody.getDirection()), searchBody.getOrderBy());
        int page = searchBody.getStart() / searchBody.getLength();
        Pageable pageable = new PageRequest(page, searchBody.getLength(), sort);

        Specification<House> specification = (root,query,cb)->{
            Predicate predicate = cb.equal(root.get("adminId"), 2);
            predicate = cb.and(predicate,cb.notEqual(root.get("status"), HouseStatus.DELETE.getValue()));
            if (searchBody.getCity() != null) {
                predicate = cb.and(predicate, cb.equal(root.get("cityEnName"), searchBody.getCity()));
            }

            if (searchBody.getStatus() != null) {
                predicate = cb.and(predicate, cb.equal(root.get("status"), searchBody.getStatus()));
            }

            if (searchBody.getCreateTimeMin() != null) {
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("createTime"), searchBody.getCreateTimeMin()));
            }

            if (searchBody.getCreateTimeMax() != null) {
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("createTime"), searchBody.getCreateTimeMax()));
            }

            if (searchBody.getTitle() != null) {
                predicate = cb.and(predicate, cb.like(root.get("title"), "%" + searchBody.getTitle() + "%"));
            }
            return predicate;
        };


        Page<House> houses = houseReposity.findAll(specification,pageable);

        //Iterable<House> houses = houseReposity.findAll();
        houses.forEach(house -> {
            HouseDTO houseDTO = modelMapper.map(house,HouseDTO.class);
            houseDTO.setCover(this.cdnPrefix + house.getCover());
            houseDTOS.add(houseDTO);
        });
        return new ServiceMultiResult<>(houses.getTotalElements(),houseDTOS);
    }

    /**
     * 查询一条完整的房源信息
     * @param id house表主键
     * @return
     */
    @Override
    public ServiceResult<HouseDTO> findCompleteOne(Long id) {
        //查询房屋表信息
        House house = houseReposity.findOne(id);
        if(house == null){
            return ServiceResult.notFound();
        }
        //通过房屋主键查询房屋详情表信息
        HouseDetail detail = houseDetailResposity.findAllByHouseId(id);
        HouseDetailDTO detailDTO = modelMapper.map(detail,HouseDetailDTO.class);
        List<HousePicture> pictures = housePictureResposity.findAllByHouseId(id);
        //将实体信息转为给客户端显示的DTO格式数据
        List<HousePictureDTO> pictrueDTOs = new ArrayList<>();
        pictures.forEach(pictrue->{
            HousePictureDTO pictureDTO = modelMapper.map(pictrue,HousePictureDTO.class);
            pictrueDTOs.add(pictureDTO);
        });
        //查询房屋标签信息通过房屋主键
        List<HouseTag> tags = houseTagRepository.findAllByHouseId(id);
        List<String> taglist = new ArrayList<>();
        for (HouseTag tag : tags) {
            taglist.add(tag.getName());
        }
        //将房屋实体信息转为给客户端显示的DTO格式数据
        HouseDTO reslut = modelMapper.map(house,HouseDTO.class);
        reslut.setHouseDetail(detailDTO);
        reslut.setPictures(pictrueDTOs);
        reslut.setTags(taglist);

        ServiceResult<HouseDTO> serviceResult = ServiceResult.of(reslut);
        return serviceResult;
    }
    @Override
    public ServiceMultiResult<HouseDTO> query(RentSearch rentSearch) {
        /*
        if (rentSearch.getKeywords() != null && !rentSearch.getKeywords().isEmpty()) {
            ServiceMultiResult<Long> serviceResult = searchService.query(rentSearch);
            if (serviceResult.getTotal() == 0) {
                return new ServiceMultiResult<>(0, new ArrayList<>());
            }

            return new ServiceMultiResult<>(serviceResult.getTotal(), wrapperHouseResult(serviceResult.getResult()));
        }

        return simpleQuery(rentSearch);*/
        return null;

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
