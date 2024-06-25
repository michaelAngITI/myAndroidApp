package com.example.familyafterwork0;

public class Event {
    private int _id;
    private String _eventTitle;
    private String _eventDate;
    private String _eventTime;
    private String _eventDescription;
    private String _eventMember;
    private String _eventKid;

    public Event(){
    }
    public Event(int id, String eventTitle, String eventDate,
                 String eventTime, String eventDescription,
                 String eventMember, String eventKid){
        this._id = id;
        this._eventTitle = eventTitle;
        this._eventDate = eventDate;
        this._eventTime = eventTime;
        this._eventDescription = eventDescription;
        this._eventMember = eventMember;
        this._eventKid = eventKid;
    }
    public Event(String eventTitle, String eventDate,
                 String eventTime, String eventDescription,
                 String eventMember, String eventKid){
        this._eventTitle = eventTitle;
        this._eventDate = eventDate;
        this._eventTime = eventTime;
        this._eventDescription = eventDescription;
        this._eventMember = eventMember;
        this._eventKid = eventKid;
    }

    public void setID(int id){
        this._id = id;
    }
    public int getID(){
        return this._id;
    }

    public void setTitle(String eventTitle){
        this._eventTitle = eventTitle;
    }
    public String getTitle(){
        return this._eventTitle;
    }

    public void setDate(String eventDate){
        this._eventDate = eventDate;
    }
    public String getDate(){
        return this._eventDate;
    }

    public void setTime(String eventTime){
        this._eventTime = eventTime;
    }
    public String getTime(){
        return this._eventTime;
    }

    public void setDescr(String eventDescription){
        this._eventDescription = eventDescription;
    }
    public String getDescr(){
        return this._eventDescription;
    }

    public void setMember(String eventMember){
        this._eventMember = eventMember;
    }
    public String getMember(){
        return this._eventMember;
    }

    public void setKid(String eventKid){
        this._eventKid = eventKid;
    }
    public String getKid(){
        return this._eventKid;
    }

}
