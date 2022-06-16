package com.example.project2;

public class ModelClass {
    private int img;
    private String title;
    private String date;
    private String age;
    private String session;
    private String  decs;





    ModelClass(int img,String title,String date,String age,String session,String decs){
        this.img=img;
        this.title=title;
        this.date=date;
        this.age=age;
        this.session=session;
        this.decs=decs;

    }

    public int getImg(){
        return img;
    }
    public String getTitle(){
        return title;
    }
    public String getDate(){
        return date;
    }
    public String getAge(){
        return age;
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