package com.crowdfunding.mapper;

import com.crowdfunding.domain.Cert;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertMapper {
    List<Cert> getAllCert(@Param("name") String name);

    @Delete("delete from t_cert where id = #{id}")
    void doDel(int id);

    @Insert("insert into t_cert (name) values(#{name})")
    void doAdd(Cert cert);

    @Select("select * from t_cert where id = #{id}")
    Cert getCertById(int id);

    @Update("update t_cert set name = #{name} where id = #{id}")
    void doEdit(Cert cert);
}
