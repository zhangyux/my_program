package com.soufang.esproject.repository;

import com.soufang.esproject.entity.House;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Description: es-project
 * Create by liangxifeng on 19-5-13
 */
public interface HouseReposity extends PagingAndSortingRepository<House,Long> {
}
