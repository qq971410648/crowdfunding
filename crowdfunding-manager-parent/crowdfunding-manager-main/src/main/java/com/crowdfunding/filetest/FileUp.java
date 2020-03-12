package com.crowdfunding.filetest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/file")
public class FileUp {

    @RequestMapping("/upfile")
    public void  upfile( @RequestParam("file") List<MultipartFile> uploadfiles, HttpServletRequest request) throws IOException {

        //1. 先判断上传文件是否存在
        if (!uploadfiles.isEmpty()) {
            for (MultipartFile file : uploadfiles) {
                //2. 获取上传的文件名
                String name = file.getName();

                //3. 设置一个保存文件的路径
                //3.1 获取当前项目的虚拟目录
                String contextPath = request.getContextPath();
                System.out.println(contextPath);//crowdfunding_manager_main_war_exploded
                //3.2 设置上传文件的保存地址目录
                file.transferTo(new File("C:\\Users\\root\\Desktop\\crowdfunding\\crowdfunding_manager\\crowdfunding-manager-parent\\crowdfunding-manager-main\\src\\main\\webapp\\file\\123.png"));
            }

        }

    }
}
