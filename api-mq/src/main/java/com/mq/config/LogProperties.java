package com.mq.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 * <p>
 * Created by Lqz
 * DATE: 2018/6/14 0014
 */

@Configuration
public class LogProperties {

    public static String execute;

    public static String getExecute() {
        return execute;
    }

    @Value("${logging.execute}")
    public void setExecute(String execute) {
        LogProperties.execute = execute;
    }

    public static void signData(String test) {
        System.out.println(execute);
    }

}
