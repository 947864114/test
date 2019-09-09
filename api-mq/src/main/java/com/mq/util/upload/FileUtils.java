package com.mq.util.upload;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class FileUtils extends FileBase {
    /**
     * 获得imgs图片地址(所有图片地址在一个字段里面)
     * @Param  imgName          图片名称
     * @Param  photoDimension   图片尺寸
     * @return
     */
    public static String[] getImgsUrl(String imgs, HttpServletRequest request) {
        if(StringUtils.isNotEmpty(imgs)) {
            String[] imgsArray = imgs.split(",");
            String[] urls = new String[imgsArray.length];
            for(int i = 0; i < imgsArray.length; i++){
                String img = imgsArray[i];
                urls[i] = FileUtils.transFilePath(img, request);
            }
            return urls;
        }else{
            return null;
        }
    }

    /**
     * 转换图片地址
     *
     * @param imgs
     * @return
     */
    private List<String> getImgsNames(String imgs, HttpServletRequest request) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(imgs)) {
            return null;
        }
        List<String> imgNames = new ArrayList<String>();
        String[] imgName = imgs.split(",");
        for (String fileName : imgName) {
            String path = transFilePath(fileName, request);
            imgNames.add(path);
        }
        return imgNames;
    }

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

    public static String getBasePathOfFile(String webContext, String fileId, String fileName) {
        StringBuffer sb = new StringBuffer();
        sb.append(webContext);
        sb.append("/");

        String basePath;
        if (fileId != null && fileId.length() > 0) {
            basePath = generateFileURIPath(fileId);
            sb.append(basePath);
            sb.append("/");
        }

        if (fileName != null && fileName.length() > 0) {
            sb.append(fileName);
            sb.append("/");
        }

        basePath = null;
        if (sb.length() > 0) {
            basePath = sb.substring(0, sb.length() - 1);
        }

        return basePath;
    }

}
