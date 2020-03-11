package com.crowdfunding.service;

import com.crowdfunding.domain.Cert;

import java.util.List;

public interface CertService {
    List<Cert> getAllCert(String name);

    void doDel(int id);

    void doDelForIds(int[] ids);

    void doAdd(Cert cert);

    Cert getCertById(int id);

    void doEdit(Cert cert);
}
