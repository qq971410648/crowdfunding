package com.crowdfunding.controller;

import com.crowdfunding.ajax.AjaxResult;
import com.crowdfunding.domain.Role;
import com.crowdfunding.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private AjaxResult ajaxResult;

    /**
     * 查询角色信息   分页 + 条件是的
     *
     * @param page
     * @param size
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAllRole")
    public AjaxResult getAllRole(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "6") int size) {
        try {
            PageHelper.startPage(page, size);
            List<Role> roleList = roleService.getAllRole(name);
            PageInfo pageInfo = new PageInfo(roleList);

            ajaxResult.setPageInfo(pageInfo);
            ajaxResult.setSuccess(true);
            return ajaxResult;
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "查询角色信息失败");
        }
    }

    /**
     * 添加角色信息
     *
     * @param role
     * @return
     */
    @ResponseBody
    @RequestMapping("/doAdd")
    public AjaxResult doAdd(Role role) {
        try {
            roleService.doAdd(role);
            return new AjaxResult(true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "添加角色信息失败");
        }
    }

    /**
     * 根据id删除角色信息
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/doDel")
    public AjaxResult doDel(int id) {
        try {
            roleService.doDel(id);
            return new AjaxResult(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "删除角色信息失败");
        }
    }

    /**
     * 根据id查询角色信息
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/getRoleById")
    public AjaxResult getRoleById(int id) {
        try {
            Role roleById = roleService.getRoleById(id);

            AjaxResult ajaxResult = new AjaxResult();
            //数据回显
            ajaxResult.setData(roleById);
            ajaxResult.setSuccess(true);
            return ajaxResult;
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "查询角色信息失败");
        }
    }

    /**
     * 更新角色信息
     *
     * @param role
     * @return
     */
    @ResponseBody
    @RequestMapping("/doUpdate")
    public AjaxResult doUpdate(Role role) {
        try {
            roleService.doUpdate(role);
            return new AjaxResult(true, "");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "更新角色信息失败");
        }
    }
}
