/**
 * @program: bbs
 * @description: 文件上传controller
 * @author: Wu
 * @create: 2019-12-24 08:52
 **/
package com.wu.bbs.controller;

import com.wu.bbs.dto.FileDTO;
import com.wu.bbs.provider.TenCosProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class FileController {

    @Autowired
    private TenCosProvider tenCosProvider;

    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO upload(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("editormd-image-file");
        String imageURL = null;
        try {
            imageURL = tenCosProvider.upload(file.getInputStream(), file.getContentType(), file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setUrl(imageURL);
        return fileDTO;
    }
}
