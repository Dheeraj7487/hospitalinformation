package com.example.hospitalinformationsystem;

public class Request {
    int id;
    String hname;
    String date;
    String pname;
    String reqtype;
    String status;
    String area;

    public Request() {
    }

    public Request(String date, String pname, String reqtype, String status,String hname) {
        this.date = date;
        this.pname = pname;
        this.reqtype = reqtype;
        this.status = status;
        this.hname=hname;
    }

    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getReqtype() {
        return reqtype;
    }

    public void setReqtype(String reqtype) {
        this.reqtype = reqtype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}

