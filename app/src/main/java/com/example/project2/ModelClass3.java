package com.example.project2;

public class ModelClass3 {
    private int img1;
    private String name;
    private int img;
    private String desc;


    ModelClass3(int img1, String name, int img, String desc) {
        this.img1 = img1;
        this.name = name;
        this.img = img;
        this.desc = desc;
    }

    public int getVid() {
        return img1;
    }

    public String getName() {
        return name;
    }

    public int getImg() {
        return img;
    }

    public String getDesc() {
        return desc;
    }

}