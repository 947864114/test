package com.mq.vo.publish;

/**
 * Description: PublishEnum
 * Created by LQZ
 * DATE: 2019/7/17 16:47
 */
public enum PublicEnum {
    KEX("KEX", "dK3uNDIUhM5juu5+lGOp8w=="),
    KG("KG", "+DrhvcQeaRuUNydOiXV4Yw=="),
    ;


    private String platFlag;
    private String platApiKey;

    PublicEnum(String platFlag, String platApiKey) {
        this.platFlag = platFlag;
        this.platApiKey = platApiKey;
    }

    public String getPlatFlag() {
        return platFlag;
    }

    public void setPlatFlag(String platFlag) {
        this.platFlag = platFlag;
    }

    public String getPlatApiKey() {
        return platApiKey;
    }

    public void setPlatApiKey(String platApiKey) {
        this.platApiKey = platApiKey;
    }

    public static String getFlagByApiKey(String apiKey){
        for (PublicEnum it : PublicEnum.values()) {
            if(it.platApiKey.equals(apiKey)){
                return it.getPlatFlag();
            }
        }
        return null;
    }
}
