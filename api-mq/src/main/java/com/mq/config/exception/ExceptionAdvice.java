package com.mq.config.exception;

import com.mq.util.AppJson;
import com.mq.util.KexAppJson;
import com.mq.util.log.LogUtil;
import com.mq.util.exception.ExceptionStatus;
import com.mq.util.exception.BlException;
import org.slf4j.Logger;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * Description:
 * <p>
 * Created by Lqz
 * DATE: 2018/7/26 0026
 */
@ControllerAdvice
public class ExceptionAdvice implements EnvironmentAware {
    Logger logger = LogUtil.getPlatformLogger();
    private Environment environment;
    private static final List<String> urlList = Arrays.asList(
            "/api-bl/customer/login",
            "/api-bl/customer/update",
            "/api-bl/order/add",
            "/api-bl/order/updateInfo",
            "/api-bl/order/updateStatus",
            "/api-bl/calculate/findHashrate"
            );

    public ExceptionAdvice() {
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @ExceptionHandler
    @ResponseBody
    public Object handleException(Exception ex, HttpServletRequest request) {
        String url = request.getRequestURI();
        logger.info("url========>  " + url);
        ex.printStackTrace();
        if(urlList.contains(url)) {
            KexAppJson appJson = new KexAppJson(ExceptionStatus.AUTHOR_ERROR);
            if (ex != null) {
                String desc;
                if (ex instanceof BlException) {
                    BlException mapper = (BlException) ex;
                    int status = mapper.getStatus();
                    desc = mapper.getMsg();
                    appJson.setCode(status);
                    appJson.setMsg(desc);
                    logger.info("kdErrorCode=" + status + ";kdErrorMsg=" + desc);
                } else {
                    desc = ExceptionStatus.getDesc(ExceptionStatus.SERVER_ERROR);
                    appJson.setCode(ExceptionStatus.SERVER_ERROR);
                    appJson.setMsg(desc);
                    logger.info("kdErrorCode=" + ExceptionStatus.SERVER_ERROR + ";kdErrorMsg=" + desc );
                }
                return appJson;
            }
            return appJson;
        }else{
            AppJson appJson = new AppJson(AppJson.CODESTATUSFAIL);
            String tokenStr = request.getHeader("auth_token");
            if (ex != null) {
                String desc;
                if (ex instanceof BlException) {
                    BlException mapper = (BlException) ex;
                    int status = mapper.getStatus();
                    desc = mapper.getMsg();
                    appJson.setCode(status);
                    appJson.setMessage(desc);
                    logger.info("kdErrorCode=" + status + ";kdErrorMsg=" + desc + ";auth_token=" + tokenStr);
                } else {
                    desc = ExceptionStatus.getDesc(ExceptionStatus.SERVER_ERROR);
                    appJson.setCode(ExceptionStatus.SERVER_ERROR);
                    appJson.setMessage(desc);
                    logger.info("kdErrorCode=" + ExceptionStatus.SERVER_ERROR + ";kdErrorMsg=" + desc + ";auth_token=" + tokenStr);
                }
                return appJson;
            }
            return appJson;
        }
    }

}
