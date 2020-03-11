package com.crowdfunding.service;

import com.crowdfunding.domain.Advertisement;

import java.util.List;

public interface AdvertisementService {

    List<Advertisement> getAllAdvertisement(String name)throws Exception;

    int insertAdvert(Advertisement advert);

    void delAdvertisementById(int id);
}
