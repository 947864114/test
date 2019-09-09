package com.mq.util.upload;

import com.mq.util.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Test {
    public static final String YYYYMMDD = "yyyy-MM-dd";
    public static  void main(String[] args){
       Date date= DateUtils.getAfterSomeDays(20);
        SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD);
        String date2=sdf.format(date);
       System.out.println(date2);
    }


}
