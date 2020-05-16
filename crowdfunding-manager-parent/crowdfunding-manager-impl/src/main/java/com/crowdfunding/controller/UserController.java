package com.crowdfunding.controller;

import com.crowdfunding.ajax.AjaxResult;
import com.crowdfunding.ajax.RoleVo;
import com.crowdfunding.domain.Role;
import com.crowdfunding.domain.User;
import com.crowdfunding.service.UserService;
import com.crowdfunding.util.MD5Util;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    static Logger logger = Logger.getLogger(UserController.class);

    /**
     * 用户登录
     *
     * @param user
     * @param session
     * @return
     */
    @RequestMapping("/doLogin")
    public AjaxResult doLogin(User user, HttpSession session) {
        try {
            //根据用户输入的用户名判断是否存在该用户
            User userByLogincct = userService.getUserByLogincct(user);
            logger.info(userByLogincct);
            if (userByLogincct == null) {
                return new AjaxResult(false, "用户名或密码错误");
            }

            //MD5加密
            String digest = MD5Util.digest(user.getUserpswd());
            //用MD5进行加密后的密码比对数据库里面该用户的密码
            if (!digest.equals(userByLogincct.getUserpswd())) {
                return new AjaxResult(false, "用户名或密码错误");
            }
            //将该用户的用户名存入session
            session.setAttribute("username", userByLogincct);
            return new AjaxResult(true, "");
        } catch (Exception e) {
            /**
             * 1. 返回异常发生时的详细信息
             * public string getMessage();
             *
             * 2. 返回异常发生时的简要描述
             * public string toString();
             *
             * 3. 返回异常对象的本地化信息。使用Throwable的子类覆盖这个方法，可以声称本地化信息。如果子类没有覆盖该方法，则该方法返回的信息与getMessage（）返回的结果相同
             * public string getLocalizedMessage();
             *
             * 4. 在控制台上打印Throwable对象封装的异常信息
             * public void printStackTrace();
             */
            e.printStackTrace();
            System.out.println(e.toString());
            return new AjaxResult(false, "登陆失败，请联系管理员");
        }
    }

    /**
     * 用户退出
     *
     * @param session
     * @return
     */
    @RequestMapping("/doLogout")
    public AjaxResult doLogout(HttpSession session) {
        try {
            //销毁session  更多关于session的方法请前往session源码查看
            session.invalidate();
            return new AjaxResult(true, "");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(true, "退出失败请联系管理员");
        }
    }

    /**
     * 分页查询所有用户信息     加/不加条件
     *
     * @return
     */
    @RequestMapping("/doGet")
    public AjaxResult doGet(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            AjaxResult ajaxResult = new AjaxResult();

            //使用了pageHelper
            PageHelper.startPage(page, size);
            List<User> users = userService.getAllUserByCondition();    //加条件
            //List<User> users = userService.getAllUser();                      //不加条件

            //PageInfo对象由PageHelper提供，PageHelper的list属性：该页数据结果集
            //将PageInfo封装到AjaxResult，前端页面取数据通过pageInfo.list 就阔以取出该页的数据
            PageInfo pageInfo = new PageInfo(users);
            ajaxResult.setPageInfo(pageInfo);
            ajaxResult.setSuccess(true);
            return ajaxResult;
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "查询用户信息失败" + e.toString());
        }
    }

    /**
     * 添加用户信息
     *
     * @param user
     * @return
     */
    @RequestMapping("/doAdd")
    public AjaxResult doAdd(@RequestBody User user) {
        try {
            //后台做一个判断，判断用户是否把所有信息都填写完毕。这个功能没有做后台是否已注册的校验
            if (user.getLoginacct().equals("") || user.getUserpswd().equals("") || user.getUsername().equals("") || user.getEmail().equals("")) {
                return new AjaxResult(false, "请填写完所有信息");
            }
            userService.doAdd(user);
            return new AjaxResult(true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "添加失败");
        }
    }

    /**
     * 根据用户id删除用户信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/doDel")
    public AjaxResult doDel(@RequestParam(required = true) int id) {
        try {
            //根据用户id判断该用户是否具有某些角色，如果有说明他不能被删除，告知用户权限不够。
            List<Integer> getUserRole = userService.getUser_Role(id);
            if (getUserRole.size() != 0) {
                return new AjaxResult(false, "对不起您的权限不够");
            }
            userService.doDel(id);
            return new AjaxResult(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "删除失败");
        }
    }

    /**
     * 根据用户id查询用户信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/getUserById")
    public AjaxResult getUserById(@RequestParam(required = true) int id) {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            User userById = userService.getUserById(id);
            ajaxResult.setSuccess(true);
            //用于做回显  将查询出来的用户信息封装到AjaxResult里面的Data   前端页面从AjaxRelut.data得到回显数据
            ajaxResult.setData(userById);
            return ajaxResult;
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "查询该用户信息失败");
        }
    }

    /**
     * 更新用户数据
     *
     * @param user
     * @return
     */
    @RequestMapping("/doUpdate")
    public AjaxResult doUpdate(User user) {
        try {
            //更新数据
            userService.doUpdate(user);
            return new AjaxResult(true, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "更新失败");
        }
    }

    /**
     * 根据用户id查询出其所拥有的角色集合信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/checkAssignedRole")
    public AjaxResult checkAssignedRole(int id) {
        try {
            //1. 根据用户id查询已分配的角色信息
            List<Role> roleList = userService.checkAssignedRole(id);
            AjaxResult ajaxResult = new AjaxResult();
            ajaxResult.setList(roleList);
            ajaxResult.setSuccess(true);
            return ajaxResult;
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "查询该用户角色信息失败");
        }
    }

    /**
     * 根据用户id查询出其未拥有的角色集合信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/checkNoAssignedRole")
    public AjaxResult checkNoAssignedRole(int id) {
        try {
            //1. 查询所有角色信息
//            List<Role> allRoleList = userService.checkAllRole();

            //2. 根据用户id查询已分配的角色信息
//            List<Role> assignedRoleList = userService.checkAssignedRole(id);

            //3. 用所有角色信息  - 根据用户id查询已分配的角色信息 = 该用户的未分配的角色    此种方法逻辑肯定是对的
            //allRoleList.removeAll(assignedRoleList);      这个方法不得行  原本以为removeAll是移除该List中相同的元素

            //通过sql   查询出其没有被分配的角色信息
            List<Role> checkNoAssignedRole = userService.checkNoAssignedRole(id);
            AjaxResult ajaxResult = new AjaxResult();
            ajaxResult.setSuccess(true);
            ajaxResult.setList(checkNoAssignedRole);
            return ajaxResult;
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "查询该用户角色信息失败");
        }
    }

    /**
     * leftToRight    实现按  ->  的功能
     * 特别注意：前端给了我用户id 和一个将被分配的角色id数组   springmvc不支持直接接收集合类型  需要将其封装到实体类
     *
     * PS：                jsonObj["assignedRoleId[" + i + "]"] = this.value;         ——————前端是这样传参的
     * 其实我可以用int类型的数组来接受，不需要用包装类
     * @param userid
     * @return
     */
    @RequestMapping("/leftToRight")
    public AjaxResult leftToRight(int userid, RoleVo roleVo) {
        try {
            userService.savaUserRole(userid, roleVo);
            return new AjaxResult(true, "");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "分配角色失败");
        }
    }

    /**
     * rightToLeftBtn   实现按  <-  的功能
     *
     * @param userid
     * @param roleVo
     * @return
     */
    @RequestMapping("/rightToLeft")
    public AjaxResult rightToLeftBtn(int userid, RoleVo roleVo) {
        try {
            userService.delUserRole(userid, roleVo);
            return new AjaxResult(true, "");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "分配角色失败");
        }
    }

    /**
     * 批量删除
     * PS：批量删除我用的是把勾选的id遍历出来使用根据单个id一条一条执行删除语句。可能涉及效率问题
     * 正确的做法：应该在mapper.xml中使用collection动态sql来执行删除
     *
     * @param id
     * @return
     */
    @RequestMapping("/batchDel")
    public AjaxResult batchDel(int[] id) {
        try {
            userService.doDelForIds(id);
            return new AjaxResult(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "删除失败");
        }
    }
}

