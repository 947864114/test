package com.mq.util.log;

/**
 * Description:
 * <p>
 * Created by Lqz
 * DATE: 2018/7/23 0023
 */
public enum LogEnum {
    BUSSINESS("bussiness"),

    PLATFORM("platform"),

    DB("db"),

    EXCEPTION("exception"),

    ;


    private String category;


    LogEnum(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
