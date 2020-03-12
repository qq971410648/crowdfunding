package com.crowdfunding.service.impl;

import com.crowdfunding.domain.Advertisement;
import com.crowdfunding.mapper.AdvertisementMapper;
import com.crowdfunding.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    private AdvertisementMapper advertisementMapper;

    public List<Advertisement> getAllAdvertisement(String name) throws Exception {
        return advertisementMapper.getAllAdvertisement(name);
    }

    public int insertAdvert(Advertisement advert) {
        return advertisementMapper.insertAdvert(advert);
    }

    public void delAdvertisementById(int id) {
        advertisementMapper.delAdvertisementById(id);
    }
}
