package com.crowdfunding.service.impl;

import com.crowdfunding.domain.Cert;
import com.crowdfunding.mapper.CertMapper;
import com.crowdfunding.service.CertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertServiceImpl implements CertService {

    @Autowired
    private CertMapper certMapper;

    public List<Cert> getAllCert(String name) {
        return certMapper.getAllCert(name);
    }

    public void doDel(int id) {
        certMapper.doDel(id);
    }

    public void doDelForIds(int[] ids) {
        for (int id : ids) {
            certMapper.doDel(id);
        }
    }

    public void doAdd(Cert cert) {
        certMapper.doAdd(cert);
    }

    public Cert getCertById(int id) {
        return certMapper.getCertById(id);
    }

    public void doEdit(Cert cert) {
        certMapper.doEdit(cert);
    }
}
