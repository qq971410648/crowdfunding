package com.crowdfunding.mapper;

import com.crowdfunding.domain.Advertisement;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 撒旦
 */
@Repository
public interface AdvertisementMapper {





    int deleteByPrimaryKey(Integer id);

    int insert(Advertisement record);

    Advertisement selectByPrimaryKey(Integer id);

    List<Advertisement> selectAll();

    int updateByPrimaryKey(Advertisement record);

    /**
     * 分页查询 + 条件
     *
     * @param name
     * @return
     */
    List<Advertisement> getAllAdvertisement(@Param("name") String name);

    int insertAdvert(Advertisement advert);

    /**
     * 根据id删除广告
     *
     * @param id
     */
    @Delete("delete from t_advertisement where id = #{id}")
    void delAdvertisementById(int id);
}