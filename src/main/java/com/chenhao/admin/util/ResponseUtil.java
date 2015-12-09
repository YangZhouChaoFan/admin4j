package com.chenhao.admin.util;

import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenhao on 2015/12/8.
 */
public class ResponseUtil {

    /**
     * 解析错误信息
     * @param binding
     * @return res

     */
    public static Map<String, Object> analyzeError(BindingResult binding) {
        Map<String, Object> res = new HashMap();
        String msg = "";
        List<ObjectError> objErrorList = binding.getAllErrors();
        for (ObjectError error : objErrorList) {
            msg += "<p>" + error.getDefaultMessage() + "</p>";
        }
        res.put("msg", msg);
        res.put("status", "error");
        return res;
    }

}
