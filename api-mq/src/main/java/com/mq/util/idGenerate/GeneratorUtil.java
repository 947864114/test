package com.mq.util.idGenerate;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>Description: </p>
 * <p>
 * <p>Company: 中景恒基 www.mwteck.com</p>
 *
 * @author LiangQuanZhong
 * @version 1.0
 * @date 2016/12/6
 */
public class GeneratorUtil {
    private static final String str62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final AtomicInteger ATOM_INT = new AtomicInteger(0);

    public GeneratorUtil() {
    }

    public static final String create15() {
        StringBuilder sb = new StringBuilder(15);
        sb.append(Long.toHexString(System.currentTimeMillis()));
        String str = longTo36((long)ATOM_INT.incrementAndGet());
        if(str.length() == 1) {
            sb.append("000").append(str);
        } else if(str.length() == 2) {
            sb.append("00").append(str);
        } else if(str.length() == 3) {
            sb.append("0").append(str);
        } else {
            sb.append(str);
        }

        return sb.toString();
    }

    public static final String create14() {
        StringBuilder sb = new StringBuilder(14);
        sb.append(Long.toHexString(System.currentTimeMillis()));
        String str = longTo36((long)ATOM_INT.incrementAndGet());
        if(str.length() == 1) {
            sb.append("00").append(str);
        } else if(str.length() == 2) {
            sb.append("0").append(str);
        } else {
            sb.append(str);
        }

        return sb.toString();
    }

    public static final String longTo36(long num) {
        return ten2Any(num, 36);
    }

    private static final String ten2Any(long num, int base) {
        StringBuilder sb;
        for(sb = new StringBuilder(7); num != 0L; num /= (long)base) {
            sb.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt((int)(num % (long)base)));
        }

        return sb.reverse().toString();
    }
}
