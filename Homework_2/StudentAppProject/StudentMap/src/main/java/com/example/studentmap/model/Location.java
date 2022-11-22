package com.example.studentmap.model;

public class Location{
    private float x;
    private float y;
    private String type;
    private String name;
    private String address;
    private String phone;
    private String website;
    private String openingHours;

    public Location(float x, float y, String type, String name, String address, String phone,
                    String website, String openingHours){
        this.x = x;
        this.y = y;
        this.type = type;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.openingHours = openingHours;
    }

    public float getX(){
        return x;
    }

    public void setX(float x){
        this.x = x;
    }

    public float getY(){
        return y;
    }

    public void setY(float y){
        this.y = y;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getWebsite(){
        return website;
    }

    public void setWebsite(String website){
        this.website = website;
    }

    public String getOpeningHours(){
        return openingHours;
    }

    public void setOpeningHours(String openingHours){
        this.openingHours = openingHours;
    }

}

