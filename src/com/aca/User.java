package com.aca;

public class User {

    private String name;
    private String phoneNumber;
    private String email;
    private PhoneType phoneType;
    private EmailType emailType;

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public PhoneType getPhoneType() {
        return phoneType;
    }

    public EmailType getEmailType() {
        return emailType;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPhoneType(PhoneType phoneType) {
        this.phoneType = phoneType;
    }

    public void setEmailType(EmailType emailType) {
        this.emailType = emailType;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        if(email==null){
            return "User{" +
                    "Name='" + name + '\'' +
                    ", Phone Number=" + phoneNumber +
                    ", Phone Type=" + phoneType +
                    '}';
        }
        return "User{" +
                "Name='" + name + '\'' +
                ", Phone Number=" + phoneNumber +
                ", Phone Type=" + phoneType +
                ", Email='" + email + '\''  +
                ", Email Type=" + emailType +
                '}';
    }
}
