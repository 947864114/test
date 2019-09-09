package com.mq.util.log;

import com.mq.config.LogProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Description: 打印
 * <p>  如果是打印基础数据变量 用 k，v模式，如：PT.printParam(k1, v1, k2, v2...)
 * 如果打印对象用 可以直接 PT.printParam(Object)
 * Object: map, list, object
 * <p>
 * Created by Lqz
 * DATE: 2018/7/19 0019
 */
public class LogUtil {
    @Autowired
    private LogProperties log;

    /**
     * 获取业务日志logger
     *
     * @return
     */
    public static Logger getBussinessLogger() {
        if ("false".equals(LogProperties.execute)) {
            return null;
        }
        return LoggerFactory.getLogger(LogEnum.BUSSINESS.getCategory());
    }

    /**
     * 获取平台日志logger
     *
     * @return
     */
    public static Logger getPlatformLogger() {
        if ("false".equals(LogProperties.execute)) {
            return null;
        }
        return LoggerFactory.getLogger(LogEnum.PLATFORM.getCategory());
    }

    /**
     * 获取数据库日志logger
     *
     * @return
     */
    public static Logger getDBLogger() {
        if ("false".equals(LogProperties.execute)) {
            return null;
        }
        return LoggerFactory.getLogger(LogEnum.DB.getCategory());
    }


    /**
     * 获取异常日志logger
     *
     * @return
     */
    public static Logger getExceptionLogger() {
        if ("false".equals(LogProperties.execute)) {
            return null;
        }
        return LoggerFactory.getLogger(LogEnum.EXCEPTION.getCategory());
    }


    /**
     * 打印 平台日志 用 k，v模式，如：PT.printParam(k1, v1, k2, v2...)
     *
     * @param params
     */
    public static void printBizLogger(Object... params) {
        Logger logger = getBussinessLogger();
        if (logger != null) {
            if (params != null) {
                logger.info("=============平台日志============");
                for (int j = 0; j < params.length; ) {
                    Object oTitle = params[j];
                    if (j + 1 < params.length) {
                        Object oValue = params[++j];
                        if (checkkPrimitive(oValue)) {
                            logger.info(oTitle.toString() + " : " + oValue.toString());
                        }
                        ++j;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    /**
     * 打印 平台日志 方法开始
     *
     * @param funName 方法名称
     */
    public static void printStartFunctionLogger(String funName) {
        Logger logger = getPlatformLogger();
        if (logger != null) {
            logger.info("方法: " + funName + " 开始调用");
        }
    }

    /**
     * 打印 平台日志 方法结束
     *
     * @param funName 方法名称
     */
    public static void printEndFunctionLogger(String funName) {
        Logger logger = getPlatformLogger();
        if (logger != null) {
            logger.info("方法: " + funName + " 结束调用");
        }
    }

    /**
     * 打印 平台日志 打印一句话
     *
     * @param words 标记语
     */
    public static void printFlagLogger(String words) {
        Logger logger = getPlatformLogger();
        if (logger != null) {
            logger.info("=============方法 " + words + " 结束调用============");
        }
    }

    /**
     * 打印 平台对象，如VO,MODEL,MAP,LIST等日志
     *
     * @param params
     */
    public static void printBizObjectLogger(Object... params) {
        Logger logger = getPlatformLogger();
        if (logger != null) {
            if (params != null) {
                for (int j = 0; j < params.length; ) {
                    Object oTitle = params[j];
                    if (oTitle != null) logger.info(oTitle.toString());
                    if (j + 1 < params.length) {
                        Object oValue = params[++j];
                        if (oValue instanceof Map) {//MAP打印
                            Map map = (Map) oValue;
                            map.forEach((k, v) -> {
                                if (v != null) logger.info(k + "-->" + v.toString());
                            });
                        } else if (oValue instanceof List) {//List打印
                            List list = (List) oValue;
                            list.forEach((v) -> {
                                if (v != null) logger.info(v.toString());
                            });
                        }
                        ++j;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    private static boolean checkkPrimitive(Object oValue) {
        if (oValue != null && oValue instanceof String) {
            return true;
        }
        if (oValue != null && oValue instanceof Integer) {
            return true;
        }
        if (oValue != null && oValue instanceof Double) {
            return true;
        }
        if (oValue != null && oValue instanceof Long) {
            return true;
        }
        if (oValue != null && oValue instanceof Float) {
            return true;
        } else if (oValue == null) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
//        Map<String, Object> map1 = new HashMap<>();
//        AlarmDataVO vo = new AlarmDataVO();
//        vo.setBattery(1);
//        vo.setDig_temperature(2f);
//        vo.setSn(123);
//        List<AlarmDataVO> vos = new ArrayList<>();
//        vos.add(vo);
//        map1.put("k1", "v1");
//        map1.put("k2", 1);
//        map1.put("k3", 0d);
//        map1.put("k4", new AlarmDataVO());
//        map1.put("k5", vos);
//
//        printBizLogger("vos", vos);
        String test = null;
        System.out.println(test.trim());
    }
}
