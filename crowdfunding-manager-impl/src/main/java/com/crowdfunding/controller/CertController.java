package com.crowdfunding.controller;

import com.crowdfunding.ajax.AjaxResult;
import com.crowdfunding.domain.Cert;
import com.crowdfunding.service.CertService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cert")
public class CertController {

    @Autowired
    private CertService certService;


    /**
     * 分页查询资质
     *
     * @param page
     * @param size
     * @param
     * @return
     */
    @RequestMapping("/getAllCert")
    public AjaxResult getAllCert(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String condition) {

        try {
            PageHelper.startPage(page, size);
            List<Cert> certList = certService.getAllCert(condition);
            PageInfo pageInfo = new PageInfo(certList);
            AjaxResult ajaxResult = new AjaxResult();
            ajaxResult.setPageInfo(pageInfo);
            ajaxResult.setSuccess(true);
            return ajaxResult;
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "查询资质失败");
        }
    }

    /**
     * 根据id删除资质
     *
     * @param id
     * @return
     */
    @RequestMapping("/doDel")
    public AjaxResult doDel(int id) {
        try {
            certService.doDel(id);
            return new AjaxResult(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "删除失败");
        }
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/doDelForIds")
    public AjaxResult doDelForIds(int[] ids) {
        try {
            certService.doDelForIds(ids);
            return new AjaxResult(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "删除失败");
        }
    }

    /**
     * 添加资质
     *
     * @param cert
     * @return
     */
    @RequestMapping("/doAdd")
    public AjaxResult doAdd(Cert cert) {
        try {
            certService.doAdd(cert);
            return new AjaxResult(true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "添加失败");
        }
    }

    /**
     * 根据id查询资质
     * @param id
     * @return
     */
    @RequestMapping("/getCertById")
    public AjaxResult getCertById(int id){
        try {
            Cert cert = certService.getCertById(id);
            return new AjaxResult(true,null);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false,"查询失败");
        }
    }

    /**
     * 更新资质
     * @param cert
     * @return
     */
    @RequestMapping("/doEdit")
    public AjaxResult doEdit(Cert cert){
        try {
            certService.doEdit(cert);
            return new AjaxResult(true,"更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false,"更新失败");
        }
    }
}
