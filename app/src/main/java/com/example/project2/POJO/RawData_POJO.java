package com.example.project2.POJO;

public class RawData_POJO {

    String access_token;
    String code;
    String id_token;


    public RawData_POJO(String access_token, String code, String id_token) {
        this.access_token = access_token;
        this.code = code;
        this.id_token = id_token;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getId_token() {
        return id_token;
    }

    public void setId_token(String id_token) {
        this.id_token = id_token;
    }
}
