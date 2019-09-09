package com.mq.util.idGenerate;

import org.bson.types.ObjectId;

/**
 * <p>Description: </p>
 * <p>
 * <p>Company: 中景恒基 www.mwteck.com</p>
 *
 * @author LiangQuanZhong
 * @version 1.0
 * @date 2016/12/6
 */
public class IdGenerator {
    public IdGenerator() {
    }

    public static String getID() {
        return (new ObjectId()).toString();
    }

    public static String getID14() {
        return GeneratorUtil.create14();
    }

    public static String getID15() {
        return GeneratorUtil.create15();
    }

    public static void main(String[] args) {


        for(int i = 0; i < 100; ++i) {
            System.out.println(getID());
        }

    }
}
