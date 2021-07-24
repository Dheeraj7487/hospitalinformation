package com.example.hospitalinformationsystem;

public class Hospital {
    int logo;
    String HospName;
    String Area;
    String Address;
    int amtbed;
    int amtoxy;
    int amtamb;
    int doc;
    int nur;

    public Hospital() {
    }

    public Hospital(int logo, String hospName, String area, String address, int amtbed, int amtoxy, int amtamb, int doc, int nur) {
        this.logo = logo;
        HospName = hospName;
        Area = area;
        Address = address;
        this.amtbed = amtbed;
        this.amtoxy = amtoxy;
        this.amtamb = amtamb;
        this.doc = doc;
        this.nur = nur;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public String getHospName() {
        return HospName;
    }

    public void setHospName(String hospName) {
        HospName = hospName;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getAmtbed() {
        return amtbed;
    }

    public void setAmtbed(int amtbed) {
        this.amtbed = amtbed;
    }

    public int getAmtoxy() {
        return amtoxy;
    }

    public void setAmtoxy(int amtoxy) {
        this.amtoxy = amtoxy;
    }

    public int getAmtamb() {
        return amtamb;
    }

    public void setAmtamb(int amtamb) {
        this.amtamb = amtamb;
    }

    public int getDoc() {
        return doc;
    }

    public void setDoc(int doc) {
        this.doc = doc;
    }

    public int getNur() {
        return nur;
    }

    public void setNur(int nur) {
        this.nur = nur;
    }
}

