package com.example.project2;

public class ModelClass {
    private int img;
    private String title;
    private String session;
    private String  decs;



    ModelClass(int img,String title,String session,String decs){
        this.img=img;
        this.title=title;
        this.session=session;
        this.decs=decs;

    }

    public int getImg(){
        return img;
    }

    public String getTitle(){
        return title;
    }
    public String getSession(){
        return session;
    }
    public String getDecs(){
        return decs;
    }




//    public void setImg(int newImg){
//        this.img=newImg;
//    }


}