package com.chenhao.admin.controller;

import com.chenhao.admin.model.Response;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by chenhao on 2015/12/3.
 */

@Controller
@RequestMapping("/uploadController")
public class UploadController {

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @RequestMapping(value = "/upload")
    @ResponseBody
    public Response upload(@RequestParam MultipartFile file, String userName, HttpServletRequest request) throws IOException {
        if (!file.isEmpty()) {
            String realPath = request.getSession().getServletContext().getRealPath("/upload");
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, file.getOriginalFilename()));
        }
        return new Response().success("上传成功");
    }

    @RequestMapping(value = "/multiUpload")
    @ResponseBody
    public Response multiUpload(@RequestParam MultipartFile[] files, String userName, HttpServletRequest request) throws IOException {
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String realPath = request.getSession().getServletContext().getRealPath("/upload");
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, file.getOriginalFilename()));
            }
        }
        return new Response().success("上传成功");
    }

}
