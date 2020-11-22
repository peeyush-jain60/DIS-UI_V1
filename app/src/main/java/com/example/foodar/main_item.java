package com.example.foodar;
public class main_item {
    private String title;
    private String info;
    private final int imageResource;
    private final int ratingResource;
    public main_item(String t,String i,int img,int rating){
        this.title=t;
        this.info=i;
        this.imageResource=img;
        this.ratingResource=rating;
    }
    public String gettitle(){return title;}
    public String getinfo(){return info;}
    public int getimgae(){
        return imageResource;
    }
    public int getRatingResource(){return ratingResource;}
}