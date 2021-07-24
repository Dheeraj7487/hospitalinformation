package com.example.hospitalinformationsystem;

public class feedbackdisplaymodel {
    String name;
    String email;
    String mobile;
    String writefeedback;

    public feedbackdisplaymodel() {
    }

    public feedbackdisplaymodel(String name, String email, String mobile, String writefeedback) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.writefeedback = writefeedback;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWritefeedback() {
        return writefeedback;
    }

    public void setWritefeedback(String writefeedback) {
        this.writefeedback = writefeedback;
    }
}
