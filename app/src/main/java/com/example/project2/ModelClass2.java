package com.example.project2;

public class ModelClass2 {
    private String vid;
    private String name;
    private int img;
    private String desc;


    ModelClass2(String vid,String name,int img,String desc){
        this.vid=vid;
        this.name=name;
        this.img=img;
        this.desc=desc;
    }

    public String getVid(){
        return vid;
    }
    public String getName(){
        return name;
    }
    public int getImg(){
        return img;
    }
    public String getDesc(){
        return desc;
    }





}
