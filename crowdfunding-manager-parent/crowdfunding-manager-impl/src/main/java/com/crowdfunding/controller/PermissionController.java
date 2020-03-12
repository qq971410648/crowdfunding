package com.crowdfunding.controller;

import com.crowdfunding.ajax.AjaxResult;
import com.crowdfunding.domain.Permission;
import com.crowdfunding.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 查询许可维护
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/doPermission")
    public AjaxResult doPermission() {
        AjaxResult result = new AjaxResult();
        try {
            //1. 获取根节点
            Permission rootPermission = permissionService.getPNode();
            rootPermission.setOpen(true);//对根节点的open设置true

            //2. 根据根节点的id 获取其子节点
            List<Permission> childrenPermission = permissionService.getCNode(rootPermission.getId());

            //3. 设置父子关系
            rootPermission.setChildren(childrenPermission);

            //4. 根据子节点的id 获取其子节点
            //4.1 遍历子节点
            for (Permission child : childrenPermission) {
                //4.2 根据子节点的id获取其子节点
                List<Permission> childPermission = permissionService.getCNode(child.getId());

                //4.3 设置每个节点的父子关系
                child.setChildren(childPermission);

                //4.4 对每个子节点的open设置true
                child.setOpen(true);
            }

            //5. 将根节点放入集合
            ArrayList<Permission> permissionArrayList = new ArrayList<Permission>();
            permissionArrayList.add(rootPermission);

            //6. 返回json
            result.setSuccess(true);
            result.setData(permissionArrayList);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "查询许可信息失败");
        }
    }
}
