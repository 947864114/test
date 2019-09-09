package com.mq.util;



import com.mq.util.upload.FileUtils;

import javax.servlet.http.HttpServletRequest;

public class AjaxFileUpLoadUtils {
    public static String transFilePath(String fileName, HttpServletRequest request){
        //获取服务器名称
        String contextPath = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath + "/";
        //获取文件夹路径
        String[] fileId = fileName.split("\\.");
        String orgUrl = FileUtils.getBasePathOfFile("img", fileId[0], fileName);
        String url = basePath + orgUrl;

        return url;
    }
}
