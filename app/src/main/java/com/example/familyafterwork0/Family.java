package com.example.familyafterwork0;

public class Family {
    private int _id;
    private String _familyName;
    private String _familyImg;
    private String _familyRole;


    public Family(){
    }
    public Family(int id, String familyName,String familyRole, String familyImg){
        this._id = id;
        this._familyName = familyName;
        this._familyImg = familyImg;
        this._familyRole = familyRole;

    }
    public Family(String familyName, String familyImg,
                  String familyRole){
        this._familyName = familyName;
        this._familyImg = familyImg;
        this._familyRole = familyRole;
    }

    public void setID(int id){
        this._id = id;
    }
    public int getID(){
        return this._id;
    }

    public void setName(String familyName){
        this._familyName = familyName;
    }
    public String getName(){
        return this._familyName;
    }

    public void setImg(String familyImg){
        this._familyImg = familyImg;
    }
    public String getImg(){
        return this._familyImg;
    }

    public void setRole(String familyRole){
        this._familyRole = familyRole;
    }
    public String getRole(){
        return this._familyRole;
    }



}
