package com.aca;

import java.util.Map;

public class Validator {

    public static String deleteSpace(String str){
        StringBuilder ret= new StringBuilder();
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)!=' '){
                ret.append(str.charAt(i));
            }
        }
        return ret.toString();
    }
    public static String replaceSpace(String str){
        StringBuilder ret= new StringBuilder();
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)!=' '){
                ret.append(str.charAt(i));
            }else{
                ret.append('_');
            }
        }
        return ret.toString();
    }
    public static  boolean contains(String str, Map<String,String> map){
        for(String s: map.keySet()){
            if(deleteSpace(str).equals( deleteSpace(s))){
                return true;
            }
        }
        return false;
    }
    public static boolean isMail(String mail) {
        boolean flag = false;
        for (int i = 0; i < mail.length(); i++) {
            if (mail.charAt(i) == '@'&& i!=0) {
                i++;
                flag = true;
            }
            if (flag && mail.charAt(i)=='@'){
                return false;
            }
            if (i + 1 < mail.length() && flag && (mail.charAt(i) == '.')) {
                return true;
            }
        }
        return false;
    }
    public static boolean isPhoneNumber(String phoneNumber) {
        if (phoneNumber.strip().length() == 0|| phoneNumber.substring(1,phoneNumber.length()).contains("+")) {
            return false;
        }
        for (int i = 0; i < phoneNumber.length(); i++) {
            if ((phoneNumber.charAt(i) < '0' || phoneNumber.charAt(i) > '9') && phoneNumber.charAt(i) != '+' && phoneNumber.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }
}
