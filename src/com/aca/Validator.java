package com.aca;

public class Validator {
    public static boolean isMail(String mail){
        boolean flag=false;
        for (int i = 0; i <mail.length() ; i++) {
            if (mail.charAt(i)=='@'){
                flag=true;
            }
            if(i+1<mail.length() && flag && (mail.charAt(i) == '.')){
                return true;
            }
        }
        return false;
    }
    public static boolean isPhoneNumber(String phoneNumber){
        if(phoneNumber.strip().length()==0){
            return false;
        }
        for (int i = 0; i <phoneNumber.length() ; i++) {
            if(phoneNumber.charAt(i)=='+'&& i>0){
                return false;
            }
            else if((phoneNumber.charAt(i)<'0' || phoneNumber.charAt(i)>'9') && phoneNumber.charAt(i)!='+' && phoneNumber.charAt(i)!=' '){
                return false;
            }
        }
        return true;
    }
}
