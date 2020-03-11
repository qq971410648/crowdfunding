package com.crowdfunding.controller;

import com.crowdfunding.ajax.AjaxResult;
import com.crowdfunding.domain.Advertisement;
import com.crowdfunding.service.AdvertisementService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/advertisement")
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    /**
     * 查询所有广告信息
     *
     * @param page
     * @param size
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAllAdvertisement")
    public AjaxResult getAllAdvertisement(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "") String name) {
        try {
            AjaxResult ajaxResult = new AjaxResult();
            PageHelper.startPage(page, size);
            List<Advertisement> advertisementList = advertisementService.getAllAdvertisement(name);
            for (Advertisement advertisement : advertisementList) {
                if (advertisement.getStatus().equals("1")) {
                    advertisement.setStatus("未审核");
                }
            }

            PageInfo pageInfo = new PageInfo(advertisementList);
            ajaxResult.setPageInfo(pageInfo);
            ajaxResult.setSuccess(true);
            return ajaxResult;
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "查询广告信息失败");
        }
    }

    /**
     * 删除广告
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delAdvertisementById")
    public AjaxResult delAdvertisementById(int id) {
        try {
            advertisementService.delAdvertisementById(id);
            return new AjaxResult(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "删除失败");
        }
    }

//    @ResponseBody
//    @RequestMapping("/doAdd")
//    public AjaxResult doAdd(HttpServletRequest request, Advertisement advert, HttpSession session) {
//        AjaxResult result = new AjaxResult();
//        try {
//            //强转，用于接收File
//            MultipartHttpServletRequest mreq = (MultipartHttpServletRequest) request;
//
//            MultipartFile mfile = mreq.getFile("advpic");
//
//            String name = mfile.getOriginalFilename();//java.jpg
//            String extname = name.substring(name.lastIndexOf(".")); // .jpg
//
//            String iconpath = UUID.randomUUID().toString() + extname; //232243343.jpg
//
//            ServletContext servletContext = session.getServletContext();
//            String realpath = servletContext.getRealPath("/pic");
//
//            String path = realpath + "\\adv\\" + iconpath;
//
//            mfile.transferTo(new File(path));
//
//            User user = (User) session.getAttribute("username");
//            advert.setUserid(user.getId());
//            advert.setStatus("1");
//            advert.setIconpath(iconpath);
//
//            int count = advertisementService.insertAdvert(advert);
//            result.setSuccess(count == 1);
//        } catch (Exception e) {
//            e.printStackTrace();
//            result.setSuccess(false);
//        }
//
//        return result;
//    }


    /**
     * 上传广告
     * @param selectedFile
     * @param advertisement
     * @param session
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("/doAdd")
    public AjaxResult doAdd(MultipartFile selectedFile,Advertisement advertisement, HttpSession session) throws IOException {
        System.out.println(selectedFile);
        System.out.println(advertisement);

        String originalFilename = selectedFile.getOriginalFilename();
        String endName = originalFilename.substring(originalFilename.lastIndexOf("."));//.jpg
        String fileName = UUID.randomUUID().toString() + endName;//随机数.jpg
        ServletContext servletContext = session.getServletContext();
        String realPath = servletContext.getRealPath("/pic");
        //C:\Users\root\Desktop\crowdfunding\crowdfunding_manager\crowdfunding-manager-parent\crowdfunding-manager-main\target\crowdfunding-manager-main\pic
        String path =realPath+ "\\adv\\"+fileName;
        selectedFile.transferTo(new File(path));

        //再将相关信息存入数据库即可



        return null;
    }
}
