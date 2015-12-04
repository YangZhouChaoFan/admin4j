package com.chenhao.admin.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenhao on 2015/12/3.
 */

@Controller
public class UploadController {

    @RequestMapping(value = "/upload")
    @ResponseBody
    public Map<String, Object> upload(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException {

        Map<String, Object> res = new HashMap();
        if (!file.isEmpty()) {
            String realPath = request.getSession().getServletContext().getRealPath("/upload");
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, file.getOriginalFilename()));
        }
        res.put("status", "success");
        return res;
    }

    @RequestMapping(value = "/multiUpload")
    @ResponseBody
    public Map<String, Object> multiUpload(@RequestParam MultipartFile[] files, HttpServletRequest request) throws IOException {
        Map<String, Object> res = new HashMap();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String realPath = request.getSession().getServletContext().getRealPath("/upload");
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, file.getOriginalFilename()));
            }
        }
        res.put("status", "success");
        return res;

    }

}
