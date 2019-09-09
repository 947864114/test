package com.mq.vo.kex;

public class RegisterVO {

    private String name;
    private String password;
    //用户唯一标志
    private String token;
    private String phone;
    private String mail;
    //身份证号
    private String idnumber;
    //邀请人UID：邀请人在kex交易所的uid
    private String introuid;
    //认证照片1 （手持身份证）地址
    private String ident1;
    //认证照片2 （身份证正面）地址
    private String ident2;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getIntrouid() {
        return introuid;
    }

    public void setIntrouid(String introuid) {
        this.introuid = introuid;
    }

    public String getIdent1() {
        return ident1;
    }

    public void setIdent1(String ident1) {
        this.ident1 = ident1;
    }

    public String getIdent2() {
        return ident2;
    }

    public void setIdent2(String ident2) {
        this.ident2 = ident2;
    }
}
